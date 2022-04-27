package Server;

import Shared.*;


public class Log {
		private Messages m;
	/** this is to call from message /
	 * 
	 * @return
	 */
	private String[] ConversationData;
	/**conversation data
	 * 
	 */
	private String[] UserData;
	
	private String [][] ConversationDate= { (String[])  ConversationData};
	
	/**
	 * 
	 * This is the user data
	 */
	List<String> log;
	String filename;
	File file;

	





public Log(Messages m, String[] conversationData, String[] userData, String[][] conversationDate) {
		this.m = m;
		ConversationData = conversationData;
		UserData = userData;
		ConversationDate = conversationDate;
	}
/**
void loadlog() {
//open read file
//store data from file to log
}

void addMessage() {
//format message
String formattedMsg = ""; //formatted message should be date, time, sender, receivers, message
Messages m =new Messages(formattedMsg, formattedMsg, formattedMsg, formattedMsg, formattedMsg, formattedMsg);
log.add(formattedMsg);
m.getToServer();
} 


void saveLog() {
	
} //save everything in the log list to a file

*/

/**this is to log message
 
 */
	public void LogMessage() {
		Messages m1= new Messages("", "", "", "", "", "");
		Messages m2 = new Messages("", "", "", "", "", "");
		String start = m1.getTime();
        String end = m2.getTime();
        String begin = m1.getDate();
        String finale = m2.getDate();
        
		while(!m.getData().isEmpty()) {
						
	        for (int j = 0; j < ConversationData.length - 1; j++) {
	 
	            if (start.compareTo(end) < 0) {
	 
	                String temp = ConversationData[j];
	                ConversationData[j] = ConversationData[j + 1];
	                ConversationData[j + 1] = temp;
	                j = -1;
	            }
	            
			}
			
	        for (int j = 0; j < ConversationDate.length - 1; j++) {
	       	 
	            if (begin.compareTo(finale) < 0) {
	 
	                String[] temp = ConversationDate[j];
	                ConversationDate[j] = ConversationDate[j + 1];
	                ConversationDate[j + 1] = temp;
	                j = -1;
	            }
		}
	        
	        
	        
	        }
		
			/**addMessage();
			 * 
			 * 
			 * */

		
			/** 
			 * message will be organized by date of publish
			 *  and if if message date list is empty then the array do nothing
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






	public Messages getM() {
		return m;
	}






	public void setM(Messages m) {
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
	
	

	public String[][] getConversationDate() {
		return ConversationDate;
	}

	public void setConversationDate(String[][] conversationDate) {
		ConversationDate = conversationDate;
	}
	

	}
