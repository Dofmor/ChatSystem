package Server;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//This class should extend server to be able to access logs and users
public class IT extends Person{
	private Server server;
	private Log log;
	
	private String username;
	private String password;
	private String userType;
	
	public IT(String username, String password, String userType) {
		super(username,password,userType);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	//Calls the log function so we are able to read previous convo data
	public List<String> getChatLog() {
		//gets the Log
		return log.getLog();
	}
	



	/**
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param userType
	 * 
	 * This function is to create a user based on the information given by it
	 * then the user is added to the array of users within the server
	 * @throws FileNotFoundException 
	 */
  public void createUser(String username, String password, String userType) throws FileNotFoundException{
		Person userTemp = new Person(username, password, userType);
	  	ArrayList<Person> profile = server.getProfiles();
		profile.add(userTemp);
	  	server.saveProfiles();
	}
	
  
  
  
  
	/**
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param userType
	 * 
	 * This function is to delete a user based on the information given by it
	 * then the user is removed from the array of users within the server if they exist
	 * @throws FileNotFoundException 
	 */
  public void deleteUser(String username) throws FileNotFoundException{
	  	ArrayList<Person> profile = server.getProfiles();
		for(int i = 0; i < profile.size(); i++){
			if(profile.get(i).getUsername().equals(username)){
				profile.remove(i);
			}
		}
	  	server.saveProfiles();
	}
}
