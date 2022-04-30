package Server;

import Shared.Conversation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server {

	private int port;
	private ArrayList<ServerThread> serverThreads = new ArrayList<>();
	private ArrayList<Person> profiles = new ArrayList<>();
	private ArrayList<Conversation> conversations = new ArrayList<>();
	private HashMap<String,ServerThread> activeUsers = new HashMap<>();
	private String profilesFile = "profiles.txt";
	private String conversationsFile = "conversations.txt";
	private int chatId = 0;

	public Server(int port) {
		this.port = port;
		chatId = 0;
	}
	
	public int getChatId() {
		this.chatId++;
		return chatId;
	}

	public ArrayList<Person> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Person> users) {
		this.profiles = users;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ArrayList<ServerThread> getserverThreads() {
		return serverThreads;
	}

	public void setserverThreads(ArrayList<ServerThread> serverThreads) {
		this.serverThreads = serverThreads;
	}

	public HashMap<String, ServerThread> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(HashMap<String, ServerThread> activeUsers) {
		this.activeUsers = activeUsers;
	}
	
	public void listProfiles() {
		System.out.println("Current profiles are: " );
		for(Person user: profiles) {
			System.out.print(user.toString());
		}
	}

	/**
	 * Reads profiles from txt file
	 * 
	 * @throws FileNotFoundException if file is not found
	 */
	public void readProfiles() throws FileNotFoundException {
		File file = new File(profilesFile);
		Scanner read = null;

		if (!file.exists()) {
			System.out.println("File not found");
		} else {
			System.out.println("File found");
			String userName, password, profileType;
			read = new Scanner(file);
			Person personProfile = null;
			//IT ITProfile = null;
			System.out.println(profiles.size());
			while (read.hasNext()) {
				userName = read.next();
				password = read.next();
				profileType = read.next();
				profileType = profileType.trim();
				personProfile = new Person(userName, password, profileType);
				profiles.add(personProfile);
				
			}

			read.close();
		}

	}

	/**
	 * saves profiles into text file
	 * 
	 * @param profiles - profiles to be saved
	 * @throws FileNotFoundException - if file not found
	 */
	public void saveProfiles() throws FileNotFoundException {
		File file = new File(profilesFile);

		PrintWriter write = new PrintWriter(file);

		for (int i = 0; i < profiles.size(); i++) {
			write.print(profiles.get(i).toString());
			// System.out.print(profiles.get(i).toString());
		}

		write.close();
	}

	/**
	 * reads from conversationsFile and saves into conversations
	 * @param conversations - arraylist where conversations will be stored
	 * @throws FileNotFoundException - if file not found
	 */
	public void readConversations() throws FileNotFoundException {

		File file = new File(conversationsFile);
		Scanner read = null;
		Scanner sc = null;
		if (!file.exists()) {
			System.out.println("No file found");
		} else {
			read = new Scanner(file);
			String chatName, chatId, member, userName;
			ArrayList<String[]> chat;
			List<String> members;
			Conversation temp;
			while (read.hasNextLine()) {
				chat = new ArrayList<>();
				chatName = read.nextLine();
				//System.out.println("chatName: " + chatName);

				chatId = read.nextLine();
				//System.out.println("chatid: " + chatId);

				member = read.nextLine();
				//System.out.println("member: " + member);

				// reading users
				sc = new Scanner(member);
				members = new ArrayList<String>();
				while (sc.hasNext()) {
					userName = sc.next();
					members.add(userName);
				//	System.out.println(userName);
				}

				// reading messages
				String message, date, str;

				String[] data;
				message = read.nextLine();
				while (!message.equals("END")) {
					sc = new Scanner(message);
					data = new String[3];
					userName = sc.next();
					data[0] = userName;
				//	System.out.println("usernames: " + userName);

					date = sc.next();
					data[1] = date;
				//	System.out.println("date: " + date);

					str = sc.nextLine();
					str = str.trim();
					data[2] = str;
				//	System.out.println("data: " + str);

					chat.add(data);

					message = read.nextLine();
				}

				for (int i = 0; i < chat.size(); i++) {
				//	System.out.println(chat.size());
					for (int j = 0; j < 3; j++) {
						//System.out.print(chat.get(i)[j] + " ");
					}
				//	System.out.print("\n");

				}

				temp = new Conversation(chatName, chatId, members, chat);
				conversations.add(temp);

			}

		}
		sc.close();
		read.close();

	}
	
	
	
	/**
	 * Writes all conversations from conversations arraylist onto conversatoinsFile 
	 * @throws FileNotFoundException - if file is not found
	 */
	public void saveConversations() throws FileNotFoundException {
		File file = new File(conversationsFile);
		PrintWriter write = new PrintWriter(file);

		for (int i = 0; i < conversations.size(); i++) {
			write.print(conversations.get(i).toString());
		}

		write.close();
		
	}

	public void run() {
		// TODO Auto-generated method stub

		ServerSocket server;
		try {
			readProfiles();
			readConversations();
			
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
		for(int i = 0; i < conversations.size(); i++) {
			System.out.print(conversations.get(i).toString());
		}

		// FAKE USERS FOR TESTING
		profiles.add(new Person("user1", "pass1", "it"));
		profiles.add(new Person("user2", "pass2", "regular"));
		
		
		try {

			// Setting the server to run on socket 7777
			server = new ServerSocket(port);
			// server.setReuseAddress(true);

			//
			System.out.println("Accepting new clients");
			while (true) {
				Socket client = server.accept();
				System.out.println("Accepted Client " + client);
				ServerThread newThread = new ServerThread(client, this);
				serverThreads.add(newThread);
				newThread.run();

			}
		} catch (IOException e) {
			System.out.println("could not create socket");
		}

	}
}
