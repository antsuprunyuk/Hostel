package by.suprunyuk.hostel.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import by.suprunyuk.hostel.dao.AvailabilityDAO;
import by.suprunyuk.hostel.dao.DemandDAO;
import by.suprunyuk.hostel.dao.HostelDAO;
import by.suprunyuk.hostel.entity.Availability;
import by.suprunyuk.hostel.entity.Decision;
import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.entity.Hostel;
import by.suprunyuk.hostel.entity.Purpose;

/**
 * represents all demand logic for the application.
 * 
 * @author Anton Suprunyuk
 *
 */
public class DemandLogic {
	
	/**
	 * integer identifier of situation when no row was found in a table 
	 */
	private static final int NON_EXIST_ROW_NUMBER = -1;
	private static final long ONE_DAY = 1;

	/**
	 * checks if there are free places in a hostel and make a validation of dates order
	 * 
	 * @param dateIn date of incoming to hostel
	 * @param dateOut date of outcoming from hostel
	 * @param number number of guests
	 * @param hostelId id of the hostel
	 * @return one of the enum constant that represent a kind of possibility 
	 */
	public static AvailabilityResult checkPossibility(LocalDate dateIn, LocalDate dateOut, int number, long hostelId) {
		if (dateIn.isAfter(dateOut)) {
			return AvailabilityResult.INCORRECT_DATES;
		} else if (!checkPlacesForDates(dateIn, dateOut, number, hostelId)) {
			return AvailabilityResult.NOT_ENOUGH_PLACES;
		}
		return AvailabilityResult.AVAILABLE;
	}

	/**
	 * checks availability of ordering for specific number of places for distinct dates 
	 * 
	 * @param dateIn date of incoming to hostel
	 * @param dateOut date of outcoming from hostel
	 * @param number number of guests
	 * @param hostelId id of the hostel
	 * @return boolean representation of avalability of ordering 
	 */
	private static boolean checkPlacesForDates(LocalDate dateIn, LocalDate dateOut, int number, long hostelId) {
		LocalDate current = LocalDate.of(dateIn.getYear(), dateIn.getMonthValue(), dateIn.getDayOfMonth());
		AvailabilityDAO availabilityDAO = new AvailabilityDAO();
		while (!current.isAfter(dateOut)) {
			int freePlace = availabilityDAO.readPlacesForHostel(current, hostelId);
			if (freePlace == NON_EXIST_ROW_NUMBER) {
				createAvailabilityRow(current, hostelId);
				freePlace = availabilityDAO.readPlacesForHostel(current, hostelId);
			}
			if (freePlace < number || number < 1) {
				return false;
			}
			int remainder = freePlace - number;
			Availability newAvailability = new Availability(current, hostelId, remainder);
			updateAvailability(newAvailability);
			current = current.plusDays(ONE_DAY);
		}
		return true;
	}
	
	/**
	 * updates availability after ordering places in a hostel
	 * 
	 * @param availability new availability to be read into database 
	 */
	private static void updateAvailability(Availability availability) {
		AvailabilityDAO availabilityDAO = new AvailabilityDAO();
		availabilityDAO.update(availability);
	}

	/**
	 * creates new availability row in a table for specific date, invoked during the first reference to this date
	 * 
	 * @param date specific date for ordering
	 * @param hostelId id of the hostel
	 */
	private static void createAvailabilityRow(LocalDate date, long hostelId) {
		HostelDAO hostelDAO = new HostelDAO(); 
		AvailabilityDAO availabilityDAO = new AvailabilityDAO();
		Hostel hostel = hostelDAO.read(hostelId);
		int freePlace = hostel.getPlace();
		Availability availability = new Availability(date, hostelId, freePlace);
		availabilityDAO.create(availability);
	}
	/**
	 * creates a demand for booking in a database
	 * 
	 * @param dateIn date of incoming to hostel
	 * @param dateOut date of outcoming from hostel
	 * @param number number of guests
	 * @param hostelId id of the hostel
	 * @param clientId id of the client
	 */
	public static void makeBooking(LocalDate dateIn, LocalDate dateOut, int placeNumber, long hostelId, long clientId) {
		DemandDAO demandDAO = new DemandDAO();
		Demand demand = new Demand();
		demand.setHostelId(hostelId);
		demand.setClientId(clientId);
		demand.setDateIn(dateIn);
		demand.setDateOut(dateOut);
		demand.setPlaceNumber(placeNumber);
		demand.setPurpose(Purpose.BOOKING);
		demandDAO.create(demand);
	}

	/**
	 * creates a demand for payment in a database
	 * 
	 * @param dateIn date of incoming to hostel
	 * @param dateOut date of outcoming from hostel
	 * @param number number of guests
	 * @param hostelId id of the hostel
	 * @param clientId id of the client
	 */
	public static void makePayment(LocalDate dateIn, LocalDate dateOut, int placeNumber, long hostelId, long clientId) {
		DemandDAO demandDAO = new DemandDAO();
		Demand demand = new Demand();
		demand.setHostelId(hostelId);
		demand.setClientId(clientId);
		demand.setDateIn(dateIn);
		demand.setDateOut(dateOut);
		demand.setPlaceNumber(placeNumber);
		demand.setPurpose(Purpose.PAYMENT);
		demandDAO.create(demand);		
	}

	/**
	 * returns an availability for specific dates and places after refusing demand
	 * 
	 * @param dateIn date of incoming to hostel
	 * @param dateOut date of outcoming from hostel
	 * @param number number of guests
	 * @param hostelId id of the hostel
	 * @param clientId id of the client
	 */
	public static void refuseDemand(LocalDate dateIn, LocalDate dateOut, int placeNumber, long hostelId, long clientId) {
		LocalDate current = LocalDate.of(dateIn.getYear(), dateIn.getMonthValue(), dateIn.getDayOfMonth());
		AvailabilityDAO availabilityDAO = new AvailabilityDAO();
		while (!current.isAfter(dateOut)) {
			int freePlace = availabilityDAO.readPlacesForHostel(current, hostelId);
			int summ = freePlace + placeNumber;
			Availability newAvailability = new Availability(current, hostelId, summ);
			updateAvailability(newAvailability);
			current = current.plusDays(ONE_DAY);
		}
	}

	/**
	 * counts amount of money for renting getting price from the database using dao layer
	 *  
	 * @param dateIn date of incoming to hostel
	 * @param dateOut date of outcoming from hostel
	 * @param number number of guests
	 * @param hostelId id of the hostel
	 * @return
	 */
	public static BigDecimal countTotalRent(LocalDate dateIn, LocalDate dateOut, int number, long hostelId) {
		BigDecimal total = new BigDecimal(0);
		HostelDAO hostelDAO = new HostelDAO();
		Hostel hostel = hostelDAO.read(hostelId);
		int days = dateOut.compareTo(dateIn);
		total = hostel.getDayPrice().multiply(new BigDecimal(days * number));
		return total;
	}

	/**
	 * stub method for bank connection and payment, it`s not realized in this study version
	 * 
	 */
	public static void performBankOperation(String cardType, String cardNumber, LocalDate expiration, String cvv,
			BigDecimal moneyAmount) {
	}

	/**
	 * reads all demands made by specific client
	 * 
	 * @param clientId id for the client 
	 * @return full list of demands
	 */
	public static List<Demand> readAllDemandsByClient(long clientId) {
		DemandDAO demandDAO = new DemandDAO();
		List<Demand> demands = demandDAO.readDemandsByClient(clientId);
		return demands;
	}

	/**
	 * deletes a specific demand by its id and then reads new list of demands for the specific client
	 * 
	 * @param demandId id for the demand
	 * @param clientId id for the client 
	 * @return
	 */
	public static List<Demand> excludeDemand(long demandId, long clientId) {
		DemandDAO demandDAO = new DemandDAO();
		demandDAO.delete(demandId);
		List<Demand> demands = demandDAO.readDemandsByClient(clientId);
		return demands;
	}

	/**
	 * reads all demands for all clients
	 * 
	 * @return list of demands for all clients
	 */
	public static List<Demand> readAllDemands() {
		DemandDAO demandDAO = new DemandDAO();
		List<Demand> demands = demandDAO.readAll();
		return demands;
	}

	/**
	 * writes administrator decision of approving or disapproving demand to database
	 * 
	 * @param demandId id of the demand
	 * @param toApprove boolean identifier of approving or disapproving administrator decision
	 */
	public static void approve(long demandId, boolean toApprove) {
		DemandDAO demandDAO = new DemandDAO();
		Demand demand = demandDAO.read(demandId);
		if (toApprove) {
			demand.setDecision(Decision.APPROVED);
		} else {
			demand.setDecision(Decision.DISAPPROVED);
		}
		demandDAO.update(demand);
	}
}
