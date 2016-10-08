package by.suprunyuk.hostel.entity;

import java.time.LocalDate;

public class Demand extends Entity {

	private long demandId;
	private long hostelId;
	private long clientId;
	private LocalDate dateIn;
	private LocalDate dateOut;
	private int placeNumber;
	private Purpose purpose;
	private Decision decision;
	
	public Demand() {
		super();
	}

	public Demand(long demandId, long hostelId, long clientId, LocalDate dateIn, LocalDate dateOut, int placeNumber,
			Purpose purpose, Decision decision) {
		super();
		this.demandId = demandId;
		this.hostelId = hostelId;
		this.clientId = clientId;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.placeNumber = placeNumber;
		this.purpose = purpose;
		this.decision = decision;
	}

	public long getDemandId() {
		return demandId;
	}

	public void setDemandId(long demandId) {
		this.demandId = demandId;
	}

	public long getHostelId() {
		return hostelId;
	}

	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public LocalDate getDateIn() {
		return dateIn;
	}

	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}

	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}

	public int getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(int placeNumber) {
		this.placeNumber = placeNumber;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	@Override
	public String toString() {
		return "Demand [demandId=" + demandId + ", hostelId=" + hostelId + ", clientId=" + clientId + ", dateIn="
				+ dateIn + ", dateOut=" + dateOut + ", placeNumber=" + placeNumber + ", purpose=" + purpose
				+ ", decision=" + decision + "]";
	}
	
	
	
}
