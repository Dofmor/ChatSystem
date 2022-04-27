package Server

//This class should extend server to be able to access logs and users
public class IT{
	
	IT(){
		
	}
	//Should be a log type not void since we have to return the log when the function is called on
	String getChatLog() {
		//gets the convo data so we are able to read the log
		return getConversationData();
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
	void createUser(String name, String username, String password, String userType){
		Person userTemp = new Person();
		userTemp.name = name;
		userTemp.username = username;
		userTemp.password = password;
		userTemp.userType = userType;
		
		//ADD WAY TO ADD USER TO THE USERS ARRAY IN THE SERVER
		//addUser(userTemp);
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
		Person userTemp = new Person();
		userTemp.name = name;
		userTemp.username = username;
		userTemp.password = password;
		userTemp.userType = userType;
		
		//ADD WAY TO Remove USER TO THE USERS ARRAY IN THE SERVER
		//removeUser(userTemp);
	}
}
