package Server;

import Shared.*;


public class Log {
import java.io.*;
import java.util.*;

public class log  {
	private Message[] m;
	private Message m1;






	private Server s;
	/** this is to call from message /
	 * 
	 * @return
	 */
	private String[] ConversationData;
	/**conversation data
	 * 
	 */
	private String[] UserData;
	/**
	 * 
	 * This is the user data
	 */

	
	List<String> log;
	public log(Message[] m, Message m1, Server s, String[] conversationData, String[] userData, List<String> log) {
		super();
		this.m = m;
		this.m1 = m1;
		this.s = s;
		ConversationData = conversationData;
		UserData = userData;
		this.log = log;
	}
	public Message[] getM() {
		return m;
	}
	public void setM(Message[] m) {
		this.m = m;
	}
	public Message getM1() {
		return m1;
	}
	public void setM1(Message m1) {
		this.m1 = m1;
	}
	public Server getS() {
		return s;
	}
	public void setS(Server s) {
		this.s = s;
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
	public List<String> getLog() {
		return log;
	}
	public void setLog(List<String> log) {
		this.log = log;
	}
	


	void loadlog() {
		 String line = "";

	       boolean found = false;
	       Scanner in = null;

		try {
          Scanner scanner= new Scanner(line);
          while (scanner.hasNextLine()) {
          	String data = scanner.nextLine();
          	System.out.println(data);
          }
          scanner.close();
      } catch (Exception e) {
      System.out.println(e);
      }
		
		 if (found) {
	           while (in.hasNext()) {
	               line = in.nextLine();
	               StringTokenizer st = new StringTokenizer(line, ",");
	               addMessage();
	         
	           }
	       } else {
	           new Message(line, line, line, line, line, line);
	       }
		}

	void addMessage() {
		//format message
		String formattedMsg = ""; //formatted message should be date, time, sender, receivers, message
		Message m =new Message(formattedMsg, formattedMsg, formattedMsg, formattedMsg, formattedMsg, formattedMsg);
		log.add(formattedMsg);
		m.getToServer();
		} 
	

	void saveLog() {
		String chatname = "";
		String chat = new String();
	    String throwable = "";


				//write a new string
				try {
		    		if(chat.isBlank()) { 
		    			  StringWriter sw = new StringWriter();
		    		        PrintWriter pw = new PrintWriter(sw);
		    		        pw.println();
		    		        pw.close();
		    		        throwable = sw.toString(); 
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		} 
	
	public void LogMessage() {
		m1.getFromServer();
		m1.getFromClient();
		loadlog();
		addMessage();
		saveLog();
	
		}
	
	public void sendLogs() {
		LogMessage();
		saveLog();
		m1.getToServer();
		
		/**
		 *  this is to send message logs to the server
		 */
		
		
	



	



	}
	
}

	

}
