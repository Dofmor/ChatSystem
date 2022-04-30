package Server;

package Server;

import java.io.*;

import java.util.*;
import Shared.*;


public class log  {
	private Message m;
	



	/** this is to call from message /
	 * 
	 * @return
	 */
	private String ConversationData;
	/**conversation data
	 * 
	 */
	private String UserData;
	/**
	 * 
	 * This is the user data
	 */
	
	public log(Message m, String conversationData, String userData) {
		this.m = m;
		ConversationData = conversationData;
		UserData = userData;
	}
	




	public Message getM() {
		return m;
	}
	public void setM(Message m) {
		this.m = m;
	}


	public String getConversationData() {
		return ConversationData;
	}
	public void setConversationData(String conversationData) {
		ConversationData = conversationData;
	}
	public String getUserData() {
		return UserData;
	}
	public void setUserData(String userData) {
		UserData = userData;
	}


	
	



/**
 * Log functions for logging a new message to the conversations arraylist 
 *  Returning the entire arraylist of conversations as a single string 
 * so the server can send it back to the client. 
 * Thanks 
 * @return 
 * 
 **/
	



	public void LogMessage() {
		List<String> Members=new ArrayList<String>();
		ArrayList<Conversation> con = new ArrayList<>();
		String ID="";
		String Username = "";
		ArrayList<String[]> chat=new ArrayList<String[]>();
		Conversation c= new Conversation(Username, ID, Members, chat);
			m= new Message("", "", "", "", "", "");	
			for (int x=0; x<con.size();x++) {
			con.add(c);
			
			}
	        }
	            
	

	public void sendLogs() throws FileNotFoundException {
		m= new Message("", "", "", "", "", "");
		Server s=new Server(0);
		LogMessage();
		m.getToServer();
		s.saveConversations();
		
	}


	}

