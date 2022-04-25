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
		Message m1= new Message(null, null, null, null, null, null);
		Message m2 = new Message(null, null, null, null, null, null);
		String start = m1.getDate();
        String end = m2.getDate();
		while(!m.getData().isEmpty()) {
			
	        for (int j = 0; j < ConversationData.length - 1; j++) {
	 
	            if (start.compareTo(end) < 0) {
	 
	                String temp = ConversationData[j];
	                ConversationData[j] = ConversationData[j + 1];
	                ConversationData[j + 1] = temp;
	                j = -1;
	            }
			}
			
			
		}	
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
