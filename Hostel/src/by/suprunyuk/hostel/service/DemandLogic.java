package by.suprunyuk.hostel.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.dao.AvailabilityDAO;
import by.suprunyuk.hostel.dao.DemandDAO;
import by.suprunyuk.hostel.dao.HostelDAO;
import by.suprunyuk.hostel.entity.Availability;
import by.suprunyuk.hostel.entity.Decision;
import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.entity.Hostel;
import by.suprunyuk.hostel.entity.Purpose;

public class DemandLogic {
	
	private static final String DATE_IN_PARAMETER = "dateIn";
	private static final String DATE_OUT_PARAMETER = "dateOut";
	private static final String NO_DATES_ERROR_ATTRIBUTE = "noDatesError";
	private static final String NO_NUMBERS_ERROR_ATTRIBUTE = "noNumbersError";
	private static final String NUMBER_PARAMETER = "number";
	private static final int NON_EXIST_ROW_NUMBER = -1;
	private static final long ONE_DAY = 1;

	public static AvailabilityResult checkPossibility(LocalDate dateIn, LocalDate dateOut, int number, long hostelId) {
		if (dateIn.isAfter(dateOut)) {
			return AvailabilityResult.INCORRECT_DATES;
		} else if (!checkPlacesForDates(dateIn, dateOut, number, hostelId)) {
			return AvailabilityResult.NOT_ENOUGH_PLACES;
		}
		return AvailabilityResult.AVAILABLE;
	}

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
	
	private static void updateAvailability(Availability availability) {
		AvailabilityDAO availabilityDAO = new AvailabilityDAO();
		availabilityDAO.update(availability);
	}

	private static void createAvailabilityRow(LocalDate date, long hostelId) {
		HostelDAO hostelDAO = new HostelDAO(); 
		AvailabilityDAO availabilityDAO = new AvailabilityDAO();
		Hostel hostel = hostelDAO.read(hostelId);
		int freePlace = hostel.getPlace();
		Availability availability = new Availability(date, hostelId, freePlace);
		availabilityDAO.create(availability);
	}
	
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

	public static List<Demand> readAllDemandsByClient(long clientId) {
		DemandDAO demandDAO = new DemandDAO();
		List<Demand> demands = demandDAO.readDemandsByClient(clientId);
		return demands;
		
	}


	public static List<Demand> excludeDemand(long demandId, long clientId) {
		DemandDAO demandDAO = new DemandDAO();
		demandDAO.delete(demandId);
		List<Demand> demands = demandDAO.readDemandsByClient(clientId);
		return demands;
	}

	public static List<Demand> readAllDemands() {
		DemandDAO demandDAO = new DemandDAO();
		List<Demand> demands = demandDAO.readAll();
		return demands;
	}

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
