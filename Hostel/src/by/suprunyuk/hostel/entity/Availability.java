package by.suprunyuk.hostel.entity;

import java.time.LocalDate;

public class Availability extends Entity {
	
	private LocalDate date;
	private long hostelId;
	private int freePlace;
	
	public Availability() {
		super();
	}

	public Availability(LocalDate date, long hostelId, int freePlace) {
		super();
		this.date = date;
		this.hostelId = hostelId;
		this.freePlace = freePlace;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getHostelId() {
		return hostelId;
	}

	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}

	public int getFreePlace() {
		return freePlace;
	}

	public void setFreePlace(int freePlace) {
		this.freePlace = freePlace;
	}
	
	
	
}
