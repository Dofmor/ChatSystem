package Testing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Client.Client;
import Client.ITWindow;
import Server.Person;
import Server.Server;
import Server.ServerThread;
import Shared.Conversation;
import Shared.Message;
import Client.LoginWindow;

public class ITtest {
	//Test to see if something is returned when the get convo data is called to retrieve log
	@Test
	public void getChatLogTest() throws FileNotFoundException {
		//Creates Instance for the server
		Server server = new Server(7777, "localhost");
		//Reads all conversations into the storage list
		server.readConversations();
		String log = "";
		for(int i = 0; i < server.getConversations().size(); i++) {
			log += server.getConversations().get(i).toString();
		}
		assertNotNull(log);
	}
	
	//Test to create user and check if the user is in the profiles
	@Test
	public void createUserTest() throws IOException, ClassNotFoundException {
		//Creates Instance for the server
		Server server = new Server(7777, "localhost");
		//Reads all profiles into the storage list
		server.readProfiles();
		//Gets the profiles and adds to it which increases the size to 9
		server.getProfiles().add(new Person("zusernameTest", "zpasswordTest", "IT"));
		//Saves the change to the txt
		server.saveProfiles();
		//Checks if there are 9 profiles inside of the file
		assertEquals(server.getProfiles().size(), 9);
	}
	
	//Test to create user and delete user then check if the user is in the profile
	@Test
	public void deleteUserTest() throws FileNotFoundException {
		//Creates Instance for the server
		Server server = new Server(7777, "localhost");
		//Reads all profiles into the storage list
		server.readProfiles();
		//For loop to iterate over the storage list of profiles
		for(int i = 0; i < server.getProfiles().size(); i++) {
			//Sets the name of the index to the profile name
			String name = server.getProfiles().get(i).getUsername();
			//Checks if the name at that index is equal to the one wanting to be removed
			if(name.equals("zusernameTest")) {
				//If it is the same remove
				server.getProfiles().remove(i);
			}
		}
		//Then save the changed list into teh txt
		server.saveProfiles();
		//Checks if there is only 8 profiles left after the zusernameTest is removed
		assertEquals(server.getProfiles().size(), 8);
	}
}
