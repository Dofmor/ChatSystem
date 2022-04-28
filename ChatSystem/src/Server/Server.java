package Server;

import Shared.Conversation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	
	private int port;
	private ArrayList<ServerThread> serverThreads = new ArrayList<>();
	private ArrayList<Person> profiles = new ArrayList<>();
	private ArrayList<Conversation> conversations = new ArrayList<>();
	private String profilesFile = "profiles.txt";
	private String conversationsFile =  "conversationFile";
	private int chatId;

	public Server(int port) {
		this.port = port;
		chatId = 0;
	}

	public ArrayList<Person> getUsers() {
		return profiles;
	}

	public void setUsers(ArrayList<Person> users) {
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

	/**
	 * Reads profiles from txt file
	 * @throws FileNotFoundException if file is not found
	 */
	public void readProfiles() throws FileNotFoundException {
		File file = new File(profilesFile);
		Scanner read = null;
		
		if (!file.exists()) {
			System.out.println("File not found");
		}
		else {
			System.out.println("File found");
			String userName, password, profileType;
			read = new Scanner(file);
			Person personProfile = null;
			IT ITProfile = null;
			System.out.println(profiles.size());
			while (read.hasNext()) {
				userName = read.next();
				password = read.next();
				profileType = read.next();
				profileType = profileType.trim();
				
				
				if(profileType.equals("IT")) {
					ITProfile = new IT(userName, password, profileType);
					profiles.add(ITProfile);
				}
				else {
					personProfile = new Person(userName, password, profileType);
					profiles.add(personProfile);
				}
			}
			
			
			read.close();
		}
		
	}
	
	/**
	 * saves profiles into text file
	 * @param profiles - profiles to be saved
	 * @throws FileNotFoundException - if file not found
	 */
	public  void saveProfiles(ArrayList<Person> profiles ) throws FileNotFoundException {
		File file = new File(profilesFile);
		
		PrintWriter write = new PrintWriter(file);
		
		for (int i = 0; i < profiles.size(); i++) {
			write.print(profiles.get(i).toString());
			//System.out.print(profiles.get(i).toString());
		}

		write.close();
	}
	
	public void run() {
		// TODO Auto-generated method stub

		ServerSocket server;
		try {
			readProfiles();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
		//FAKE USERS FOR TESTING
		profiles.add(new Person("user1","pass1","it"));
		profiles.add(new Person("user2","pass2","regular"));
		profiles.add(new Person("","","testuser"));

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
