package Server;

import Shared.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerThread implements Runnable {
	private Boolean SocketOpen = true;

	protected Person person = null;

	private Socket socket;
	private static Server server;
	private OutputStream outputStream;
	private ObjectOutputStream objectOutputStream;
	private InputStream inputStream;
	private ObjectInputStream objectInputStream;

	ServerThread(Socket socket, Server server) {
		this.socket = socket;
		ServerThread.server = server;

		try {
			this.outputStream = socket.getOutputStream();
			this.objectOutputStream = new ObjectOutputStream(outputStream);
			this.inputStream = socket.getInputStream();
			this.objectInputStream = new ObjectInputStream(inputStream);

		} catch (Exception e) {
			SocketOpen = false;
			System.out.println("Error:" + socket);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Connected: " + socket);

		//server.listProfiles();

		try {

			// Wait for a login messages
			// Continue look if failed login
			while (this.person == null || this.person.isLoggedIn() == false) {
				Message NewMessage = (Message) objectInputStream.readObject();
				printFromClient(NewMessage);
				login(NewMessage);
			}

			// Wait for other client messages after a good login
			while (SocketOpen) {
				Message NewMessage = (Message) objectInputStream.readObject();
				printFromClient(NewMessage);
				// make the dataType in the new message all lower case
				NewMessage.setType(NewMessage.getType().toLowerCase());


				switch (NewMessage.getType()) {
				case "logout message":
					logout(NewMessage);
					break;

				case "text message":
					// Client is sending a text message
					textMessage(NewMessage);
					break;

				case "new chat":
					// Client is wanting to make a new chat
					newChat(NewMessage);
					break;

				case "new chat user":
					// Client is asking you to add a person to the chat
					newChatUser(NewMessage);
					break;

				case "refresh":
					// Client asking to send each conversation they are in to them
					refresh();
					break;

				case "create user":
					// IT creating new user
					createUser(NewMessage);
					break;

				case "delete user":
					// IT deleting user
					deleteUser(NewMessage);
					break;

				case "get chat log":
					// IT requesting chat logs
					getLog(NewMessage);
					break;
				default:
					// failed to handle message
					// sending failed message back to client
					NewMessage.setStatus("fail");
					send(NewMessage);
					break;
				}

			}

		} catch (IOException e) {
			SocketOpen = false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			SocketOpen = false;
			e.printStackTrace();
		}

		try {
			// Closing Socket
			SocketOpen = false;
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Closed: " + socket);

	}

	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
	}
	
	private void printFromClient(Message msg) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("Message received from client");
		PrintMessage(msg);
		System.out.println("-----------------------------------------------------------");
		
	}
	
	private void printToClient(Message msg) {
		System.out.println("-----------------------------------------------------------");
		if(this.person == null) {
			System.out.println("Message sent client (" + "user not logged in)");
		} else {
			System.out.println("Message sent client " + this.person.getUsername());
		}
		PrintMessage(msg);
		System.out.println("-----------------------------------------------------------");
	}

	private void login(Message m) {
		// validate that login() is being used correctly
		if (m.getType().equals("login message") == false)
			return;
		List<String> data = Arrays.asList(m.getData().split("\n"));
		try {
			String username = data.get(0);
			String password = data.get(1);
			if (person == null || person.isLoggedIn() != false) {
				for (Person user : server.getProfiles()) {
					// check to see if login information matches any user currently registered.
					if (user.login(username, password) == true) {
						// assign this thread to be associated with a user if login information is
						// correct
						this.person = user;
						// send successful login information back to server
						m.setStatus("success");
						m.setData(user.getUserType());
						send(m);
						server.getActiveUsers().put(username, this);

						// send all the users conversations back to client
						refresh();
						return;
						
					}
				}
			}
			// user login information not found
			m.setStatus("failed");
			send(m);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		
	}

	private void sendToOther(Message m) {
		// this function takes the message in the current thread, and finds the correct
		// user or thread to send it to .

		// parse message data by newline
		List<String> data = Arrays.asList(m.getData().split("\n"));
		// get users from message
		List<String> members = null;
		try {
			members = Arrays.asList(data.get(2).split(" "));
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		// send a message to each active user
		for (String member : members) {
			ServerThread memberThread = server.getActiveUsers().get(member);
			if (memberThread != null) {
				memberThread.send(m);
			} else {
				System.out.println(member + " is not logged in");
			}
		}

	}

	private void send(Message m) {
		try {
			objectOutputStream.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printToClient(m);
	}

	// IT methods being used to create user
	private void createUser(Message m) {
		if (this.person.getUserType().toLowerCase().equals("it") == false
				|| m.getType().equals("create user") == false) {
			return;
		}
		List<String> data = Arrays.asList(m.getData().split("\n"));
		try {
			String username = data.get(0);
			username = username.toLowerCase();
			// data list contains username, password, userType
			// verify the user doesn't exist
			for (int i = 0; i < server.getProfiles().size(); i++) {
				if (server.getProfiles().get(i).getUsername().equals(username)) {
					System.out.println("User already exists ");
					m.setType("IT command return Info");
					m.setData("user already exists");
					send(m);
					return;
				}
			}
			
			//if the user does not exist, add them with correct user type
			String validIT = "IT";
			String validPerson = "Person";
			String userType  = data.get(2).toLowerCase();
			userType = userType.equals("it") ? validIT : validPerson;
			server.getProfiles().add(new Person(username, data.get(1), userType));
			
			// save the current state of profiles to a file
			try {
				server.saveProfiles();
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
			System.out.println("Created new user ");
			m.setType("IT command return Info");
			m.setData("new user created");
			send(m);
			//server.listProfiles();

		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			m.setType("IT command return Info");
			m.setData("Not enough data to create user");
			send(m);
		}

	}

	// IT methods being used to delete user
	private void deleteUser(Message m) {
		if (this.person.getUserType().toLowerCase().equals("it") == false
				|| m.getType().equals("delete user") == false) {
			return;
		}
		List<String> data = Arrays.asList(m.getData().split("\n"));
		try {
			String username = data.get(0);
			System.out.println("username is " + username);
			// find the username in current profiles list
			for (Person user : server.getProfiles()) {
				if (user.getUsername().equals(username)) {
					// delete user when found
					server.getProfiles().remove(user);
					System.out.println("Deleted user");
					m.setType("IT command return info");
					m.setData("deleted user");
					send(m);
					// save the current state of profiles to a file
					try {
						server.saveProfiles();
					} catch (FileNotFoundException fnf) {
						System.out.println(fnf);
					}
					return;
				}
			}

			System.out.println("Could not find user to delete");
			m.setType("IT command return info");
			m.setData("Could not find user to delete");
			send(m);
			//server.listProfiles();

		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	// IT methods being used to retrieve log to return to IT window
	private void getLog(Message m) {
		if (this.person.getUserType().toLowerCase().equals("it") == false
				|| m.getType().equals("get chat log") == false) {
			return;
		}

		String log = "";

		for (Conversation c : server.getConversations()) {
			log += c.toString();
		}
		m.setType("IT command return info");
		m.setData(log);
		send(m);

	}

	private void newChat(Message m) {
		List<String> data = Arrays.asList(m.getData().split("\n"));
		try {
			// get data variables for new conversation
			String chatName = data.get(0);
			String username = data.get(1);
			List<String> users = new ArrayList<>();
			users.add(this.person.getUsername());
			users.add(username);
			//ArrayList<String[]> chat = new ArrayList<String[]>();
			// chat.add(new String[] { "", "", "" });
			String id = "" + (server.getConversations().size() + 1);
			Conversation newConvo = new Conversation(chatName, id, users);

			// add the new conversation to the conversation list
			server.addConversation(newConvo);

			// send the message back to server
			send(newConvo.convertToMessage());

		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	private void textMessage(Message m) {
		try {
			List<String> data = Arrays.asList(m.getData().split("\n"));
			String chatID = data.get(0);
			String chatMessage = data.get(1);
			String[] chat = new String[] { this.person.getUsername(), m.getTime(), chatMessage };
			// find the conversation to load the text message in
			for (Conversation c : server.getConversations()) {
				if (c.ID.equals(chatID)) {
					c.addChat(chat);
					try {
						// save conversation to file
						server.saveConversations();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					// send the message to all other users
					sendToOther(c.convertToMessage());
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	private void refresh() {
		// send all the users conversations back to client
		for (Conversation c : server.getConversations()) {
			if (c.isMember(this.person.getUsername())) {
				Message conversationMsg = c.convertToMessage();
				send(conversationMsg);
			}
		}

	}

	private void newChatUser(Message m) {
		try {
			List<String> data = Arrays.asList(m.getData().split("\n"));
			String chatID = data.get(0);
			String username = data.get(1);
			
			//check to see if the user exists l
			int size = server.getProfiles().size();
			for (int i = 0; i < size; i++) {
				if (server.getProfiles().get(i).getUsername().equals(username)) {
					break;
				}
				if(i == size - 1) {
					System.out.println("Could not add " + username + "to chat because they are not registered.");
					return;
				}
			}

			for (Conversation c : server.getConversations()) {
				// find the correct conversation
				if (c.ID.equals(chatID)) {
					// If the correct conversation is found add the user to that conversation
					c.addMember(username);
					// save the current state of conversations to a file
					try {
						server.saveConversations();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					// send the conversation to all members
					Message conversationMsg = c.convertToMessage();
					sendToOther(conversationMsg);
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void logout(Message m) {
		m.setStatus("success");
		send(m);
		SocketOpen = false;
	}
	

}
