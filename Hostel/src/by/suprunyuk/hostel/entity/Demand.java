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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + ((decision == null) ? 0 : decision.hashCode());
		result = prime * result + (int) (demandId ^ (demandId >>> 32));
		result = prime * result + (int) (hostelId ^ (hostelId >>> 32));
		result = prime * result + placeNumber;
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
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
		Demand other = (Demand) obj;
		if (clientId != other.clientId)
			return false;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (decision != other.decision)
			return false;
		if (demandId != other.demandId)
			return false;
		if (hostelId != other.hostelId)
			return false;
		if (placeNumber != other.placeNumber)
			return false;
		if (purpose != other.purpose)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Demand [demandId=" + demandId + ", hostelId=" + hostelId + ", clientId=" + clientId + ", dateIn="
				+ dateIn + ", dateOut=" + dateOut + ", placeNumber=" + placeNumber + ", purpose=" + purpose
				+ ", decision=" + decision + "]";
	}
}
