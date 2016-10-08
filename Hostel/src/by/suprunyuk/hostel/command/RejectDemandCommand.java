package by.suprunyuk.hostel.command;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.DemandLogic;

public class RejectDemandCommand implements ActionCommand {
	
	private static final String DATE_IN_ATTRIBUTE = "dateIn";
	private static final String DATE_OUT_ATTRIBUTE = "dateOut";
	private static final String NUMBER_ATTRIBUTE = "number";
	private static final String  HOSTEL_ID_ATTRIBUTE = "hostelId";
	private static final String  CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String  DEMAND_ID_PARAMETER = "demandId";
	private static final String BOOKING_RESULT_PAGE_PATH = "path.page.bookingresult";
	private static final String ORDER_PAGE_PATH = "path.page.order";

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
