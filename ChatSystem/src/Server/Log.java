package Server;

package logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class log {
	private Messages[] m;
	private Messages m1;
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
	String fileName;
	File file;

	





public log(Messages[] m, String[] conversationData, String[] userData, String[][] conversationDate, Messages m1) {
		this.m = m;
		ConversationData = conversationData;
		UserData = userData;
		ConversationDate = conversationDate;
		this.m1=m1;
	}


		

	void loadlog() {
		 String line;

	       boolean found = false;
	       Scanner in = null;

		try {
          Scanner scanner= new Scanner(fileName);
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
	         
	           }
	       } else {
	           new Messages();
	       }
		}

	void addMessage() {
		//format message
		String formattedMsg = ""; //formatted message should be date, time, sender, receivers, message
		Messages m =new Messages(formattedMsg, formattedMsg, formattedMsg, formattedMsg, formattedMsg, formattedMsg);
		log.add(formattedMsg);
		m.getToServer();
		} 
	

	void saveLog() {
		String filename = fileName;
		File chat = new File(filename);

				//write a new file
				try {
		    		if(chat.createNewFile()) { 
						FileWriter myWriter = new FileWriter(filename); 
						myWriter.write(this.toString());
						myWriter.close(); 
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










	public Messages[] getM() {
		return m;
	}

	public void setM(Messages[] m) {
		this.m = m;
	}
	
	

	public Messages getM1() {
		return m1;
	}

	public void setM1(Messages m1) {
		this.m1 = m1;
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

