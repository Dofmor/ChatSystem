package Server;

import Shared.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
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

		try {
			
			// Wait for a login messages
			// Continue look if failed login
//			while (SocketOpen) {
//				Message NewMessage = (Message) objectInputStream.readObject();
//				
//				if (NewMessage.getType().equals(new String("login"))) {
//					
//					
//					if (true) { //if login success
//						NewMessage.setStatus("success");
//						objectOutputStream.writeObject(NewMessage);
//						PrintMessage(NewMessage);
//						
//						break;
////					} else {
////						NewMessage.setStatus("failed");
////						objectOutputStream.writeObject(NewMessage);
//					}
//				} else {
//					System.out.println("message ignored: "
//							+ socket.getInetAddress()
//									.getHostAddress());
//				}
//			}
			
			while(this.person == null || this.person.isLoggedIn() == false) {
				Message NewMessage = (Message) objectInputStream.readObject();
				login(NewMessage);
			}
			
////			//For Testing
//			Message m = new Message();
//			m.setType("conversation data");
//			m.appendToData("1234");	// Chat ID
//			m.appendToData("Fake Chat 1");	// Chat Name
//			m.appendToData("Harry,Daniel,Nick,Brian,Jacob"); // Members in chat
//			m.appendToData("Harry,532134,hello1");	// Chat Message
//			m.appendToData("Harry,532134,hello2");	// Chat Message
//			objectOutputStream.writeObject(m);	// Send Message
//
//			Message m2 = new Message();
//			m2.setType("conversation data");
//			m2.appendToData("5324");	// Chat ID
//			m2.appendToData("Fake Chat 2");	// Chat Name
//			m2.appendToData("Harry,Daniel,Nick,Brian,Jacob"); // Members in chat
//			m2.appendToData("Harry,532134,asdfnfaidsnjaflds");	// Chat Message
//			objectOutputStream.writeObject(m2);	// Send Message
//
//			
//			Message m3 = new Message();
//			m3.setType("conversation data");
//			m3.appendToData("1234");	// Chat ID
//			m3.appendToData("Fake Chat 1");	// Chat Name
//			m3.appendToData("Harry,Daniel,Nick,Brian,Jacob"); // Members in chat
//			m3.appendToData("Harry,532134,hello1");	// Chat Message
//			m3.appendToData("Harry,532134,hello2");	// Chat Message
//			m3.appendToData("Jacob,532134,hello");	// Chat Message
//			objectOutputStream.writeObject(m3);	// Send Message
			
			
			
			//Wait for other client messages after a good login
			while (SocketOpen) {
				Message NewMessage = (Message) objectInputStream.readObject();
				
				//sendToOther(NewMessage);
				
				PrintMessage(NewMessage);
				if (NewMessage.getType().equals(new String("logout message"))) {
					NewMessage.setStatus("success");
					objectOutputStream.writeObject(NewMessage);
					SocketOpen = false;
					break;
				} else if (NewMessage.getType().equals(new String("text message"))) {
					
					//Client is sending a text message
					try {
						List<String> data = Arrays.asList(NewMessage.getData().split("\n"));
						String ChatID = data.get(0);
						String ChatMessage = data.get(1);
			    		}catch(ArrayIndexOutOfBoundsException exception){
					}
					continue;
					
				} else if (NewMessage.getType().equals(new String("new chat"))) {
					
					//Client is wanting to make a new chat
					try {
						List<String> data = Arrays.asList(NewMessage.getData().split("\n"));
							String ChatName = data.get(0);
				    		String Username = data.get(1);
//				    		String msgData = server.getChatId() + "\n" + ChatName + "\n" + this.person.getUsername() + ", " + Username;
//				    		NewMessage.setData(msgData);
//				    		NewMessage.setType("conversation data");
//				    		send(NewMessage);
			    		}catch(ArrayIndexOutOfBoundsException exception){
					}
					continue;
					
				} else if (NewMessage.getType().equals(new String("new chat user"))) {
					
					//Client is asking you to add a person to the chat
			    		try {
						List<String> data = Arrays.asList(NewMessage.getData().split("\n"));
						String ChatID = data.get(0);
						String Username = data.get(1);
			    		}catch(ArrayIndexOutOfBoundsException exception){
					}
					continue;
					
				} else if (NewMessage.getType().equals(new String("refresh"))) {
					
					//Client asking to send each conversation they are in to them
					// NO DATA
					continue;
					
				} else if (NewMessage.getType().equals(new String("create user"))) {
					
					//IT creating new user
			    		try {
						List<String> data = Arrays.asList(NewMessage.getData().split("\n"));
						String Username = data.get(0);
						String Password = data.get(1);
						String UserType = data.get(2);
			    		}catch(ArrayIndexOutOfBoundsException exception){
					}
					continue;
					
				} else if (NewMessage.getType().equals(new String("delete user"))) {
					
					//IT deleting user
					try {
						List<String> data = Arrays.asList(NewMessage.getData().split("\n"));
						String Username = data.get(0);
					}catch(ArrayIndexOutOfBoundsException exception){
					}
					continue;
					
				} else if (NewMessage.getType().equals(new String("get chat log"))) {
					//IT requesting chat logs
					// NO DATA
					continue;
				}
				
				// failed to handle message
				// sending failed message back to client
				NewMessage.setStatus("fail");
				objectOutputStream.writeObject(NewMessage);
			}
			
		} catch (IOException e) {
			SocketOpen = false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			SocketOpen = false;
			e.printStackTrace();
		}
		
		try {
			//Closing Socket
			SocketOpen = false;
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Closed: " + socket);

	}
	
	private static void PrintMessage(Message msg) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
	}
	
	private void login(Message m) {
		// validate that login() is being used correctly
		if(m.getType().equals("login message") == false) return; 
		String[] parts = m.getData().split("\n");
		if(parts.length < 2) return;
		String username = parts[0];
		String password = parts[1];
		if (person == null || person.isLoggedIn() != false) {
			for (Person user : server.getProfiles()) {
				// check to see if login information matches any user currently registered.
				if (user.login(username, password) == true) {
					// assign this thread to be associated with a user if login information is
					// correct
					this.person = user;
					try {
						// send successful login information back to server
						m.setStatus("success");
						m.setData(user.getUserType());
						objectOutputStream.writeObject(m);
						System.out.println("{ \nUser : " + username + "\nPassword : " + password +  "\nlogged in successfully\n}");
						server.getActiveUsers().put(username, this);	
						return;
					} catch (IOException e) {
			
					}
				}
			}

			// user login information not found
			try {
				m.setStatus("failed");
				objectOutputStream.writeObject(m);
				System.out.println("{ \nUser : " + username + "\nPassword : " + password +  "\nfailed to login\n}");
			} catch (IOException e) {
				
			}
		}
	}
	
	private void sendToOther(Message m) {
		//this function takes the message in the current thread, and finds the correct
		//user or thread to send it to .
		
		//Data: "Chat-ID
//		Chat-Name
//		Username, Username, Username, ...
//		Username, Date, Text
//		Username, Date, Text
//		Username, Date, Text"
		PrintMessage(m);
		//parse message data by newline
		List<String> dataList = Arrays.asList(m.getData().split("\n"));
		// get users from message
		List<String> members = null;
		if(dataList.size() >= 2) {
			members = Arrays.asList(dataList.get(1).split(", ")); 
		}
		//send a message to each active user
		for(String member: members) {
			ServerThread memberThread = server.getActiveUsers().get(member);
			if(memberThread != null) {
				memberThread.send(m);
			} else {
				System.out.println(member + " is not logged in");
			}
		}
		
	}
	


	private void send(Message m) {
		if(this.person == null || this.person.isLoggedIn() == false) return;
		try {
			objectOutputStream.writeObject(m);
			System.out.println("Message sent to " + this.person.getUsername());
			PrintMessage(m);
		} catch (IOException e) {
			
		}
	}
	
	
	
	//IT methods
	private void createUser(Message m) {
		if(this.person.getUserType().toLowerCase().equals("it") == false || m.getType().equals("create user") == false) {
			return;
		}
		List<String> dataList = Arrays.asList(m.getData().split("\n"));
		if(dataList.size() < 3) {
			System.out.println("Not enough data to create user");
			return;
		}
		//data list contains username, password, userType
		//verify the user doesn't exist
		for(int i = 0; i < server.getProfiles().size(); i++) {
			if(server.getProfiles().get(i).getUsername().equals(dataList.get(0))) {
				System.out.println("User already exists ");
				m.setType("IT command return Info");
				m.setData("user already exists");
				send(m);
				return;
			}
		}
		
		
		server.getProfiles().add(new Person(dataList.get(0),dataList.get(0),dataList.get(0)));
		//save the current state of profiles to a file
		try {
			server.saveProfiles(server.getProfiles());
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		System.out.println("Created new user ");
		m.setType("IT command return Info");
		m.setData("new user created");
		send(m);
	}
	
	private void deleteUser(Message m) {
		if(this.person.getUserType().toLowerCase().equals("it") == false || m.getType().equals("delete user") == false) {
			return;
		}
		String username = m.getData();
		if(username.equals("")) return;
		//find the username in current profiles list
		for(int i = 0; i < server.getProfiles().size(); i++) {
			if(server.getProfiles().get(i).getUsername().equals(username)) {
				//delete user when found
				server.getProfiles().remove(i);
				System.out.println("Deleted user");
				m.setType("IT command return Info");
				m.setData("deleted user");
				send(m);
				//save the current state of profiles to a file
				try {
					server.saveProfiles(server.getProfiles());
				} catch (FileNotFoundException e) {
					System.out.println(e);
				}
				return;
			}
		}
		System.out.println("Could not find user to delete");
		m.setType("IT command return Info");
		m.setData("Could not find user to delete");
		send(m);
	}
	
	private void getLog(Message m) {
		if(this.person.getUserType().toLowerCase().equals("it") == false || m.getType().equals("get chat log") == false) {
			return;
		}
		
		//call server.log.getChatLog();
		//send chatlog as message back to server
	}
	
	
	

}
