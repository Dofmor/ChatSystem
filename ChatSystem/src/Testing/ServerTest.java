package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.jupiter.api.RepeatedTest;

import Server.Person;
import Server.Server;
import Shared.Conversation;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

public class ServerTest {
	
	@Test
	public void testReadProfiles() {
		Server server = new Server(2222, "127.0.0.1");
		try {
			server.readProfiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Person> profiles = server.getProfiles();
		
		assertTrue(profiles.size() > 0);
	}
	
	@Test
	public void testSaveProfiles() {
		Server server = new Server(2222, "127.0.0.1");
		try {
			server.readProfiles();
			server.saveProfiles();
			server.readProfiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Person> profiles = server.getProfiles();
		
		assertTrue(profiles.size() > 0);
		}
	
	@Test
	public void testReadConversations() {
		Server server = new Server(2222, "127.0.0.1");
		
		try {
			server.readConversations();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Conversation> conversations = server.getConversations();
		assertTrue(conversations.size() > 0);
	}
	
	@Test
	public void testSaveConversations() {
		Server server = new Server(2222, "127.0.0.1");
		
		try {
			server.readConversations();
			server.saveConversations();
			server.readConversations();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Conversation> conversations = server.getConversations();
		assertTrue(conversations.size() > 0);
	}
	
	
	
}