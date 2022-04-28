package Server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

public class ITtest {
//	@Test
//	public void testAvgRoll() {
//		int sides = 17;
//		Die die1 = new Die(sides);
//		int total = 0;
//		for (int i=0;i<500;i++) {
//			total += die1.roll();
//		}
//		int average = total/500;
//		
//		System.out.println("Average: " + average);
//		
//		assertTrue(average > (sides/2)-1 && average < (sides/2)+1);
//	}
	@Test
	public void getChatLogTest() {
		assertNotNull(getConversationData());
	}
	
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
	}
	
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
