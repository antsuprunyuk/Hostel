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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPass == null) ? 0 : userPass.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (clientId != other.clientId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPass == null) {
			if (other.userPass != null)
				return false;
		} else if (!userPass.equals(other.userPass))
			return false;
		if (userRole != other.userRole)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPass=" + userPass + ", userRole=" + userRole + ", clientId="
				+ clientId + "]";
	}
	
	
}
