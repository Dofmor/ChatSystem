package Server;

//This class should extend server to be able to access logs and users
public class IT extends Person{

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
	public String getChatLog() {
		//gets the Log
		return getLog();
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
	 */
  void createUser(String username, String password, String userType){
		Person userTemp = new Person(username, password, userType);
	  	ArrayList<Persons> profile = getProfiles();
		profile.add(userTemp);
	  	saveProfiles(profile);
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
	 */
  void deleteUser(String username){
	  	ArrayList<Persons> profile = getProfiles();
		for(int i = 0; i < profile.size(); i++){
			if(profile[i].getUsername().equals(username)){
				profile.remove(i);
			}
		}
	  	saveProfiles(profile);
	}
}
