package Server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

public class ITtest {
	//Test to see if something is returned when the get convo data is called to retrieve log
	@Test
	public void getChatLogTest() {
		assertNotNull(getConversationData());
	}
	
	//Test to create user and check if the user is in the profiles
	@Test
	public void createUserTest() {
		boolean isInProfiles = false;
		createUser("jacobTest", "jacobTestPassword", "IT");
		for(int i = 0; i < profiles.size(); i++) {
			if(profiles[i].getName().equals("jacobTest")) {
				isInProfiles = true;
			}
		}
		assertTrue(isInProfiles);
		deleteUser("jacobTest");
	}
	
	//Test to create user and delete user then check if the user is in the profile
	@Test
	public void deleteUserTest() {
		boolean isInProfiles = false;
		createUser("jacobTest", "jacobTestPassword", "IT");
		deleteUser("jacobTest");
		for(int i = 0; i < profiles.size(); i++) {
			if(profiles[i].getUsername().equals("jacobTest")) {
				isInProfiles = true;
			}
		}
		assertFalse(isInProfiles);
	}
}
