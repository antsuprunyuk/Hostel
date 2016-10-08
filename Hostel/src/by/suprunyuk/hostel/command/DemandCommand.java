package by.suprunyuk.hostel.command;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.AvailabilityResult;
import by.suprunyuk.hostel.service.DemandLogic;

public class DemandCommand implements ActionCommand {

	private static final String DATE_IN_PARAMETER = "dateIn";
	private static final String DATE_OUT_PARAMETER = "dateOut";
	private static final String NUMBER_PARAMETER = "number";
	private static final String  HOSTEL_ID_PARAMETER = "hostelId";
	private static final String NO_DATES_ERROR_ATTRIBUTE = "noDatesError";
	private static final String NO_NUMBERS_ERROR_ATTRIBUTE = "noNumbersError";
	private static final String ORDER_PAGE_PATH = "path.page.order";
	private static final String MAIN_PAGE_PATH = "path.page.main";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = null; // page of booking and payment
		LocalDate dateIn = LocalDate.parse(request.getParameter(DATE_IN_PARAMETER));
		LocalDate dateOut = LocalDate.parse(request.getParameter(DATE_OUT_PARAMETER));
		int number = Integer.parseInt(request.getParameter(NUMBER_PARAMETER));
		long hostelId = Long.parseLong(request.getParameter(HOSTEL_ID_PARAMETER));
		request.getSession().setAttribute(NO_DATES_ERROR_ATTRIBUTE, new Boolean(false));
		request.getSession().setAttribute(NO_NUMBERS_ERROR_ATTRIBUTE, new Boolean(false));
		
		AvailabilityResult possibility = DemandLogic.checkPossibility(dateIn, dateOut, number, hostelId);
		if (possibility == AvailabilityResult.AVAILABLE) {
			request.getSession().setAttribute("dateIn", dateIn);
			request.getSession().setAttribute("dateOut", dateOut);
			request.getSession().setAttribute("number", number);
			request.getSession().setAttribute("hostelId", hostelId);
			page = ConfigurationManager.getProperty(ORDER_PAGE_PATH);
		} else if (possibility == AvailabilityResult.INCORRECT_DATES) {
			request.getSession().setAttribute(NO_DATES_ERROR_ATTRIBUTE, new Boolean(true));
			page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
		} else {
			request.getSession().setAttribute(NO_NUMBERS_ERROR_ATTRIBUTE, new Boolean(true));
			page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
		}
		return page;
	}
}