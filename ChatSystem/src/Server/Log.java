package Server;

import Shared.*;


public class Log {
	private Message m;
	/** this is to call from message /
	 * 
	 * @return
	 */
	private String[] ConversationData;
	private String[] UserData;
	
	




	public Log(Message m, String[] conversationData, String[] userData) {
		this.m = m;
		ConversationData = conversationData;
		UserData = userData;
	}






	/*
	 * *this is to log message
	 */
	public void LogMessage() {
		if(m.getDate().isEmpty()) {
			
			while(m.getDate().contains(m.getFromClient())) {
				System.out.println(m.getToServer());
				
			}
			
			
		}
			/** 
			 * null is a place holder for message array
			 * message will be organized by date of publish
			 *  and if if message date list is empty then the array do nothing
			 *  then if message date is full then 
			 *  it go in a tranversal loop to reveal all the message said in that date
			 *
			 */
			
		}
		
		
		
	
	
	
	public void sendLogs() {
		LogMessage();
		m.getToServer();
		
		/**
		 *  this is to send message logs to the server
		 */
		
		
	}






	public Message getM() {
		return m;
	}






	public void setM(Message m) {
		this.m = m;
	}






	public String[] getConversationData() {
		return ConversationData;
	}






	public void setConversationData(String[] conversationData) {
		ConversationData = conversationData;
	}






	public String[] getUserData() {
		return UserData;
	}






	public void setUserData(String[] userData) {
		UserData = userData;
	}

	

}
