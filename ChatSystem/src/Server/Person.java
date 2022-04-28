package Server;

public class Person {
	private String username;
	private String password;
	private String userType;
	private boolean loggedIn;
	
	public Person(String username, String password, String userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.loggedIn = false;
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	private void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean login(String username, String password) {
		if(this.username.equals(username) && this.password.equals(password)) {
			setLoggedIn(true);
			return true;
		}
		return false;
	}
	
	public void logout() {
		setLoggedIn(false);
	}
	
	public String toString() {
		String formatted = getUsername() + " " + getPassword() + " " + getUserType() + "\n";
		return formatted;
	}

}
