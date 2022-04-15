public class Log {
	private Message m;
	/** this is to call from message /
	 * 
	 * @return
	 */
	
	
	
	private String[] GetConversationData() {
		m.getData();
		return null;
		/**
		 * get the user conversation from the server/
		 */
	} 		
	
	

	private String[] GetUserData() {
		m.getData();
		return null;
		/**get the data for the conversation
		 * 
		 */
		
	}  		
	
	public Log(Message m) {
		this.m = m;
	}




/**this is to log message
 
 */
	public void LogMessage() {
		if(m.getDate().isEmpty()) {
			while(m.getDate().contains(null)) {
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
		/**
		 *  this is to send message logs to the server
		 */
	}
	
	

	

	}
© 2022 GitHub, Inc.
Terms
