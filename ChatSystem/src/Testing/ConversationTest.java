package Testing;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import Shared.*;

public class ConversationTest {
	//Test to see if something is returned when the get convo data is called to retrieve log
	@Test
	public void test_constructor1() {
		List<String> Members = new ArrayList<String>();;
		ArrayList<String[]> Chats = new ArrayList<String[]>();;
		Members.add("Harry");
		Members.add("Daniel");
		Members.add("Nick");
		{
			String[] Array = new String[3];
			Array[0] = "Harry";
			Array[1] = "10:00AM";
			Array[2] = "Some Message 1";
			Chats.add(Array);
		}
		{
			String[] Array = new String[3];
			Array[0] = "Daniel";
			Array[1] = "11:00AM";
			Array[2] = "Some Message 2";
			Chats.add(Array);
		}
		{
			String[] Array = new String[3];
			Array[0] = "Nick";
			Array[1] = "12:00PM";
			Array[2] = "Some Message 3";
			Chats.add(Array);
		}
		
		Conversation convo = new Conversation("ChatName","4",Members,Chats);
		assertEquals(convo.Name, "ChatName");
		assertEquals(convo.ID, "4");
		assertEquals(convo.Members.get(0), "Harry");
		assertEquals(convo.Members.get(1), "Daniel");
		assertEquals(convo.Members.get(2), "Nick");
		assertEquals(convo.Chats.get(0)[0], "Harry");
		assertEquals(convo.Chats.get(1)[0], "Daniel");
		assertEquals(convo.Chats.get(2)[0], "Nick");
		assertEquals(convo.Chats.get(0)[1], "10:00AM");
		assertEquals(convo.Chats.get(1)[1], "11:00AM");
		assertEquals(convo.Chats.get(2)[1], "12:00PM");
		assertEquals(convo.Chats.get(0)[2], "Some Message 1");
		assertEquals(convo.Chats.get(1)[2], "Some Message 2");
		assertEquals(convo.Chats.get(2)[2], "Some Message 3");
	}
	
	public void test_constructor2() {

		String str = "";
		str = str + "ChatName\n";
		str = str + "4\n";
		str = str + "Harry Daniel Nick\n";
		str = str + "Harry 10:00AM Some Message 1\n";
		str = str + "Daniel 11:00AM Some Message 2\n";
		str = str + "Nick 12:00PM Some Message 3\n";
		str = str + "END\n";
		
		Conversation convo = new Conversation(str);
		assertEquals(convo.Name, "ChatName");
		assertEquals(convo.ID, "4");
		assertEquals(convo.Members.get(0), "Harry");
		assertEquals(convo.Members.get(1), "Daniel");
		assertEquals(convo.Members.get(2), "Nick");
		assertEquals(convo.Chats.get(0)[0], "Harry");
		assertEquals(convo.Chats.get(1)[0], "Daniel");
		assertEquals(convo.Chats.get(2)[0], "Nick");
		assertEquals(convo.Chats.get(0)[1], "10:00AM");
		assertEquals(convo.Chats.get(1)[1], "11:00AM");
		assertEquals(convo.Chats.get(2)[1], "12:00PM");
		assertEquals(convo.Chats.get(0)[2], "Some Message 1");
		assertEquals(convo.Chats.get(1)[2], "Some Message 2");
		assertEquals(convo.Chats.get(2)[2], "Some Message 3");
	}
	
	public void test_convertToMessage() {

		String str = "";
		str = str + "ChatName\n";
		str = str + "4\n";
		str = str + "Harry Daniel Nick\n";
		str = str + "Harry 10:00AM Some Message 1\n";
		str = str + "Daniel 11:00AM Some Message 2\n";
		str = str + "Nick 12:00PM Some Message 3\n";
		str = str + "END\n";
		
		Conversation convo = new Conversation(str);
		String str2 = convo.convertToMessage().getData();
		assertEquals(str, str2);	
	}
	
	
	@Test
	public void test_ToString() {
		List<String> Members = new ArrayList<String>();;
		ArrayList<String[]> Chats = new ArrayList<String[]>();;
		
		Members.add("Harry");
		Members.add("Daniel");
		Members.add("Nick");

		{
			String[] Array = new String[3];
			Array[0] = "Harry";
			Array[1] = "10:00AM";
			Array[2] = "Some Message 1";
			Chats.add(Array);
		}

		{
			String[] Array = new String[3];
			Array[0] = "Daniel";
			Array[1] = "11:00AM";
			Array[2] = "Some Message 2";
			Chats.add(Array);
		}
		
		{
			String[] Array = new String[3];
			Array[0] = "Nick";
			Array[1] = "12:00PM";
			Array[2] = "Some Message 3";
			Chats.add(Array);
		}
		
		Conversation convo = new Conversation("ChatName","4",Members,Chats);
		
		String str = "";
		str = str + "ChatName\n";
		str = str + "4\n";
		str = str + "Harry Daniel Nick\n";
		str = str + "Harry 10:00AM Some Message 1\n";
		str = str + "Daniel 11:00AM Some Message 2\n";
		str = str + "Nick 12:00PM Some Message 3\n";
		str = str + "END\n";

		assertEquals(convo.toString(), str);

		
//		assertEquals(server.getProfiles().size(), 9);

	}
	
	@Test
	public void test_chatsToText() {
		String str = "";
		str = str + "ChatName\n";
		str = str + "4\n";
		str = str + "Harry Daniel Nick\n";
		str = str + "Harry 10:00AM Some Message 1\n";
		str = str + "Daniel 11:00AM Some Message 2\n";
		str = str + "Nick 12:00PM Some Message 3\n";
		str = str + "END\n";
	
		str =  new Conversation(str).chatsToText();
					
		String str2 = "";
		str2 = str2 + "Harry:  Some Message 1\n";
		str2 = str2 + "Daniel:  Some Message 2\n";
		str2 = str2 + "Nick:  Some Message 3\n";
		
		assertEquals(str, str2);

	}
	
	@Test
	public void test_membersToText() {
		String str = "";
		str = str + "ChatName\n";
		str = str + "4\n";
		str = str + "Harry Daniel Nick\n";
		str = str + "Harry 10:00AM Some Message 1\n";
		str = str + "Daniel 11:00AM Some Message 2\n";
		str = str + "Nick 12:00PM Some Message 3\n";
		str = str + "END\n";
	
		str =  new Conversation(str).membersToText();
					
		String str2 = "";
		str2 = str2 + "Harry, ";
		str2 = str2 + "Daniel, ";
		str2 = str2 + "Nick";
		
		assertEquals(str, str2);
	}
	
	@Test
	public void test_addMember() {
		String str = "";
		str = str + "ChatName\n";
		str = str + "4\n";
		str = str + "Harry Daniel Nick\n";
		str = str + "Harry 10:00AM Some Message 1\n";
		str = str + "Daniel 11:00AM Some Message 2\n";
		str = str + "Nick 12:00PM Some Message 3\n";
		str = str + "END\n";
		Conversation convo =  new Conversation(str);
		convo.addMember("Jacob");
		
		String str2 = "Harry, Daniel, Nick, Jacob";
		assertEquals(convo.membersToText(),str2);
	}
	
	
	@Test
	public void test_isMember() {
		String str = "";
		str = str + "ChatName\n";
		str = str + "4\n";
		str = str + "Harry Daniel Nick Jacob\n";
		str = str + "Harry 10:00AM Some Message 1\n";
		str = str + "Daniel 11:00AM Some Message 2\n";
		str = str + "Nick 12:00PM Some Message 3\n";
		str = str + "END\n";
		Conversation convo =  new Conversation(str);

		assertEquals(convo.isMember("Jacob"), true);
		assertEquals(convo.isMember("Brian"), false);

	}
	
	
}
