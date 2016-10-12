package by.suprunyuk.hostel.command;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.DemandLogic;

/**
 * Command for making payment for renting apartment
 * 
 * @author Anton Suprunyuk
 */
public class PaymentDetailsCommand implements ActionCommand {
	private static final String DATE_IN_ATTRIBUTE = "dateIn";
	private static final String DATE_OUT_ATTRIBUTE = "dateOut";
	private static final String NUMBER_ATTRIBUTE = "number";
	private static final String  HOSTEL_ID_ATTRIBUTE = "hostelId";
	private static final String CARD_TYPE_PARAMETER = "cardType";
	private static final String CARD_NUMBER_PARAMETER = "cardNumber";
	private static final String CARD_EXPIRATION_DATE_MONTH_PARAMETER = "expirationDateMonth";
	private static final String CARD_EXPIRATION_DATE_YEAR_PARAMETER = "expirationDateYear";
	private static final String CVV_PARAMETER = "cvv";
	private static final String  CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String PAYMENT_SUCCED_PAGE_PATH = "path.page.paymentsucced";
	private static final String ORDER_PAGE_PATH = "path.page.order";
	private static final int FIRST_DAY_OF_MONTH = 1;
	
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
		String page = ConfigurationManager.getProperty(ORDER_PAGE_PATH);
		long clientId;
		if (request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE) != null) {
			clientId = (long) request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE);
			if (clientId != 0) {
				String cardType = request.getParameter(CARD_TYPE_PARAMETER);
				String cardNumber = request.getParameter(CARD_NUMBER_PARAMETER);
				String cvv = request.getParameter(CVV_PARAMETER);
				int month = Integer.parseInt(request.getParameter(CARD_EXPIRATION_DATE_MONTH_PARAMETER));
				int year = Integer.parseInt(request.getParameter(CARD_EXPIRATION_DATE_YEAR_PARAMETER));
				LocalDate expiration = LocalDate.of(year, month, FIRST_DAY_OF_MONTH);
				LocalDate dateIn = (LocalDate) request.getSession().getAttribute(DATE_IN_ATTRIBUTE);
				LocalDate dateOut = (LocalDate) request.getSession().getAttribute(DATE_OUT_ATTRIBUTE);
				int number = (int) request.getSession().getAttribute(NUMBER_ATTRIBUTE);
				long hostelId = (long) request.getSession().getAttribute(HOSTEL_ID_ATTRIBUTE);
				BigDecimal moneyAmount = DemandLogic.countTotalRent(dateIn, dateOut, number, hostelId);
				DemandLogic.performBankOperation(cardType, cardNumber, expiration, cvv, moneyAmount);
				page = ConfigurationManager.getProperty(PAYMENT_SUCCED_PAGE_PATH);
			}
		} 
		return page;
	}

}
