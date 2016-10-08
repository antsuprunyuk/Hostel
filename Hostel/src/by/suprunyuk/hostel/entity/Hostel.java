package by.suprunyuk.hostel.entity;

import java.math.BigDecimal;

public class Hostel extends Entity {
	private long hostelId;
	private String address;
	private String city;
	private String country;
	private int place;
	private BigDecimal dayPrice;
	
	public Hostel() {
		super();
	}

	public Hostel(long hostelId, String address, String city, String country, int place, BigDecimal dayPrice) {
		super();
		this.hostelId = hostelId;
		this.address = address;
		this.city = city;
		this.country = country;
		this.place = place;
		this.dayPrice = dayPrice;
	}
	
	public long getHostelId() {
		return hostelId;
	}
	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public BigDecimal getDayPrice() {
		return dayPrice;
	}
	public void setDayPrice(BigDecimal dayPrice) {
		this.dayPrice = dayPrice;
	}
	
	
}
