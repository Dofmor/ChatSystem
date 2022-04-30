import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

import Server.IT;
import Server.Server;

public class ITtest {
	//Test to see if something is returned when the get convo data is called to retrieve log
	@Test
	public void getChatLogTest() {
		IT techPerson = new IT("Temp", "Temp Pass", "it");
		assertNotNull(techPerson.getChatLog());
	}
	
	//Test to create user and check if the user is in the profiles
	@Test
	public void createUserTest() throws FileNotFoundException {
		Server server = null;
		server.readProfiles();
		IT techPerson = new IT("Temp", "Temp Pass", "it");
		boolean isInProfiles = false;
		techPerson.createUser("jacobTest", "jacobTestPassword", "IT");
		for(int i = 0; i < server.getProfiles().size(); i++) {
			if(server.getProfiles().get(i).getUsername().equals("jacobTest")) {
				isInProfiles = true;
			}
		}
		assertTrue(isInProfiles);
		techPerson.deleteUser("jacobTest");
	}
	
	//Test to create user and delete user then check if the user is in the profile
	@Test
	public void deleteUserTest() throws FileNotFoundException {
		Server server = null;
		server.readProfiles();
		IT techPerson = new IT("Temp", "Temp Pass", "it");
		boolean isInProfiles = false;
		techPerson.createUser("jacobTest", "jacobTestPassword", "IT");
		techPerson.deleteUser("jacobTest");
		for(int i = 0; i < server.getProfiles().size(); i++) {
			if(server.getProfiles().get(i).getUsername().equals("jacobTest")) {
				isInProfiles = true;
			}
		}
		assertFalse(isInProfiles);
	}
}
