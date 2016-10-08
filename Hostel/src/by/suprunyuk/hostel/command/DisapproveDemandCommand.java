package by.suprunyuk.hostel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.DemandLogic;

public class DisapproveDemandCommand implements ActionCommand {

	private static final String  CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String ADMIN_PAGE_PATH = "path.page.admin";
	private static final String DEMANDS_MANAGE_PAGE_PATH = "path.page.demandsmanage";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
		long clientId;
		if (request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE) != null) {
			clientId = (long) request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE);
			if (clientId == 0) {
				long demandId = Long.parseLong(request.getParameter("demandId"));
				DemandLogic.approve(demandId, false);							
				List<Demand> demands = DemandLogic.readAllDemands();
/*				for (Demand demand : demands) {
					System.out.println(demand);
				}*/
				request.getSession().setAttribute("demands", demands);
		/*		LocalDate dateIn = (LocalDate) request.getSession().getAttribute(DATE_IN_ATTRIBUTE);
				LocalDate dateOut = (LocalDate) request.getSession().getAttribute(DATE_OUT_ATTRIBUTE);
				int number = (int) request.getSession().getAttribute(NUMBER_ATTRIBUTE);
				long hostelId = (long) request.getSession().getAttribute(HOSTEL_ID_ATTRIBUTE);
				DemandLogic.makeBooking(dateIn, dateOut, number, hostelId, clientId);*/
				page = ConfigurationManager.getProperty(DEMANDS_MANAGE_PAGE_PATH);
			}
		} 
		return page;
	}
}
