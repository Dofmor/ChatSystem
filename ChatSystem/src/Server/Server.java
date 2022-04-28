package Server;

import Shared.Conversation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	
	private int port;
	private ArrayList<ServerThread> serverThreads = new ArrayList<>();
	private ArrayList<Person> users = new ArrayList<>();
	private ArrayList<Conversation> conversations = new ArrayList<>();
	private String profilesFile = "profiles.txt";
	private String conversationsFile =  "conversationFile";

	public Server(int port) {
		this.port = port;
	}

	public ArrayList<Person> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Person> users) {
		this.users = users;
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

	/**
	 * Reads profiles from txt file
	 * @throws FileNotFoundException if file is not found
	 */
	public void readProfiles() throws FileNotFoundException {
		File file = new File(profilesFile);
		
		if (!file.exists()) {
			System.out.println("File not found");
		}
		else {
			String userName = "", password = "", profileType = "";
			Scanner read = new Scanner(file);
			Person personProfile = null;
			IT ITProfile = null;

			while (read.hasNext()) {
				userName = read.next();
				password = read.next();
				profileType = read.nextLine();
				
				
				if(profileType.equalsIgnoreCase("IT")) {
					ITProfile = new IT(userName, password, profileType);
					users.add(ITProfile);
				}
				else
					personProfile = new Person(userName, password, profileType);
					users.add(personProfile);
			}
			
			
			
		}
		
	}
	
	public void run() {
		// TODO Auto-generated method stub

		ServerSocket server;
		
		//FAKE USERS FOR TESTING
		users.add(new Person("user1","pass1","it"));
		users.add(new Person("user2","pass2","regular"));
		users.add(new Person("","","testuser"));

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
