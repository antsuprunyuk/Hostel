package by.suprunyuk.hostel.command;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.DemandLogic;

/**
 * Command for refusing user payment by administrator
 * 
 * @author Anton Suprunyuk
 */
public class RefusePaymentCommand implements ActionCommand {
	private static final String DATE_IN_ATTRIBUTE = "dateIn";
	private static final String DATE_OUT_ATTRIBUTE = "dateOut";
	private static final String NUMBER_ATTRIBUTE = "number";
	private static final String  HOSTEL_ID_ATTRIBUTE = "hostelId";
	private static final String  CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String MAIN_PAGE_PATH = "path.page.main";

	/**
	 * returns String interpretation of the page user will be redirected to after doing business logic obtaining information
	 * from the request object
	 * 
	 * @param request an object implementing HttpServletRequest interface
	 * @return String interpretation of the page user will be redirected to
	 * @see javax.servlet.http.HttpServletRequest
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
		long clientId;
		if (request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE) != null) {
			clientId = (long) request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE);
			if (clientId != 0) {
				LocalDate dateIn = (LocalDate) request.getSession().getAttribute(DATE_IN_ATTRIBUTE);
				LocalDate dateOut = (LocalDate) request.getSession().getAttribute(DATE_OUT_ATTRIBUTE);
				int number = (int) request.getSession().getAttribute(NUMBER_ATTRIBUTE);
				long hostelId = (long) request.getSession().getAttribute(HOSTEL_ID_ATTRIBUTE);
				DemandLogic.refuseDemand(dateIn, dateOut, number, hostelId, clientId);
				request.getSession().setAttribute(NUMBER_ATTRIBUTE, 0);
			}
		} 
		return page;
	}

}
