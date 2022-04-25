package Shared;
/*
 * Message class for sending various types of messages in chat system
 */

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String data;
	private String status;
	private LocalDateTime dateTime;
	private String fromClient;
	private String toClient;
	private String fromServer;
	private String toServer;
	
	public Message(String type, String data, String fromClient, String toClient, 
			String fromServer, String toServer) {
		this.type = type;
		this.data = data;
		
		setDate();
		
		this.fromClient = fromClient;
		this.toClient = toClient;
		this.fromServer = fromClient;
		this.toServer = toServer;
		
	}
	
	
	/**
	 * Mutator method for date. Creates a date within PST 
	 */
	private void setDate() {
		
		ZoneId timeZone = ZoneId.of("America/Los_Angeles");
		dateTime = LocalDateTime.now(timeZone);
	}
	
	/**
	 * Mutator method for the status variable.
	 * @param stat - new status to be assigned
	 */
	public void setStatus(String stat) {
		status = stat;
	}
	/**
	 * Getter method that returns the type of message 
	 * @return the type class attribute
	 */
	public String getType() {
		
		return type;
	}
	
	/**
	 * Getter method that returns the data in the object
	 * @return that data class attribute
	 */
	public String getData() {
		
		return data;
	}
	
	/**
	 * Getter method that returns the status in the object
	 * @return that data class attribute
	 */
	public String getStatus() {
		
		return status;
	}
	
	
	/**
	 * Getter method that returns the date the object was created
	 * @return the date formatted in American date notation
	 */
	public String getDate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String formattedDate = dateTime.format(formatter);
		
		return formattedDate;
	}
	
	/**
	 * Getter method that returns the time the object was created
	 * @return the time formatted in hours and minutes
	 */
	public String getTime() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String formattedTime = dateTime.format(formatter);
		
		return formattedTime;		
	}
	
	
	/**
	 * Getter method that returns source client
	 * @return the fromClient class attribute
	 */
	public String getFromClient() {
		
		return fromClient;
	}
	
	/**
	 * Getter method that returns destination client
	 * @return the toClient class attribute
	 */
	public String getToClient() {
		
		return toClient;
	}
	
	/**
	 * Getter method that returns source server
	 * @return the fromServer class attribute
	 */
	public String getFromServer() {
		
		return fromServer;
	}
	
	/**
	 * Getter method that returns destination server
	 * @return the toServer class attribute
	 */
	public String getToServer() {
		
		return toServer;
	}
	
	/**
	 * Overridden toString method. 
	 */
	@Override
	public String toString() {
		
		String str = getType() + ", " + getData() + ", " + getDate() + "," + getTime() + "," + getFromClient() + 
				"," + getToClient() + "," + getFromServer() + "," + getToServer();
		 
		return str;
	}
	
	
}
