package by.suprunyuk.hostel.entity;

import java.time.LocalDate;

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
	public String toString() {
		return "Client [clientId=" + clientId + ", surname=" + surname + ", name=" + name + ", email=" + email
				+ ", discount=" + discount + ", banned=" + banned + "]";
	}
	
	
}
