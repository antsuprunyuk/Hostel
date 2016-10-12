package by.suprunyuk.hostel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.DemandLogic;

/**
 * Command for reject demand by administrator
 * 
 * @author Anton Suprunyuk
 */
public class RejectDemandCommand implements ActionCommand {
	
	private static final String  CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String  DEMAND_ID_PARAMETER = "demandId";
	private static final String BOOKING_RESULT_PAGE_PATH = "path.page.bookingresult";

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
		String page = ConfigurationManager.getProperty(BOOKING_RESULT_PAGE_PATH);
		long demandId = Long.parseLong(request.getParameter(DEMAND_ID_PARAMETER));
		long clientId = (long) request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE);
		List<Demand> demands = DemandLogic.excludeDemand(demandId, clientId);
		request.getSession().setAttribute("demands", demands);
		return page;
	}
}
