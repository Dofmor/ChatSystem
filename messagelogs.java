public class messagelogs {
	private Message m;
	private String[] GetConversationData() {
		return null;
	} 		//get the user conversation from the server
	private String[] GetUserData() {
		return null;
	}  		//	get the data for the conversation

	
	public messagelogs(Message m) {
		this.m = m;
	}


	public Message getM() {
		return m;
	}


	public void setM(Message m) {
		this.m = m;
	}
//this is to log message
	public void LogMessage() {
		if(m.getDate().isEmpty()) {
			
		}
			// message will be organized by date of publish
			
		}
		
		
		
	}
	
	// this is to send logs of message to the client
	public void sendLogs() {

	}
	
	
