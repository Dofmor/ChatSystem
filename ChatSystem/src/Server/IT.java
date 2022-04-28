package Server;

//This class should extend server to be able to access logs and users
public class IT extends Person{
	
	

	public IT(String username, String password, String userType) {
		super(username, password, userType);
		// TODO Auto-generated constructor stub
	}

	//Calls the log function so we are able to read previous convo data
//	public String getChatLog() {
//		//gets the convo data so we are able to read the log
//		return log.getConversationData();
//	}
	



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
  void createUser(String name, String username, String password, String userType){
		Person userTemp = new Person(username, password, userType);
		profiles.add(userTemp);
	  	saveProfiles(profiles);
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
  void deleteUser(String name, String username, String password, String userType){
		Person userTemp = new Person(username, password, userType);
		for(int i = 0; i < profiles.size(); i++){
			if(profile[i].getUsername().equals(userTemp.getUsername())){
				profiles.remove(i);
			}
		}
	  	saveProfiles(profiles);
	}
}
