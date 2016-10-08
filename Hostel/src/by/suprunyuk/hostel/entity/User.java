package by.suprunyuk.hostel.entity;

public class User extends Entity {
	private String userName;
	private String userPass;
	private UserRole userRole; 
	private long clientId;
	
	public User() {
	}

	public User(String userName, String userPass, UserRole userRole) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.userRole = userRole;
	}
	
	public User(String userName, String userPass, UserRole userRole, long clientID) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.userRole = userRole;
		this.clientId = clientID;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
}
