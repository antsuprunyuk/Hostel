package by.suprunyuk.hostel.entity;

public class Client extends Entity {
	
	private long clientId;
	private String surname;
	private String name;
	private String email;
	private int discount;
	private boolean banned;
	
	public Client() {
		
	}
	
	public Client(String surname, String name, String email) {
		super();
		this.surname = surname;
		this.name = name;
		this.email = email;
	}

	public Client(long clientId, String surname, String name, String email, int discount, boolean banned) {
		super();
		this.clientId = clientId;
		this.surname = surname;
		this.name = name;
		this.email = email;
		this.discount = discount;
		this.banned = banned;
	}


	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (banned ? 1231 : 1237);
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + discount;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Client other = (Client) obj;
		if (banned != other.banned)
			return false;
		if (clientId != other.clientId)
			return false;
		if (discount != other.discount)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", surname=" + surname + ", name=" + name + ", email=" + email
				+ ", discount=" + discount + ", banned=" + banned + "]";
	}
	
	
}
