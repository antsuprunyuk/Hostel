package by.suprunyuk.hostel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.entity.Client;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.ClientManageLogic;

public class SetDiscountCommand implements ActionCommand {

	private static final String CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String ADMIN_PAGE_PATH = "path.page.admin";
	private static final String BONUS_BANS_PAGE_PATH = "path.page.bonusbans";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
		long clientId;
		if (request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE) != null) {
			clientId = (long) request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE);
			if (clientId == 0) {
				
				long currentClient = Long.parseLong(request.getParameter("clientId"));
				int newDiscount = Integer.parseInt(request.getParameter("newdiscount"));
				ClientManageLogic.updateDiscount(currentClient, newDiscount);
				
				List<Client> clients = ClientManageLogic.readAllClients();
/*				for (Client client: clients) {
					System.out.println(client);
				}*/
				request.getSession().setAttribute("clients", clients);
		/*		LocalDate dateIn = (LocalDate) request.getSession().getAttribute(DATE_IN_ATTRIBUTE);
				LocalDate dateOut = (LocalDate) request.getSession().getAttribute(DATE_OUT_ATTRIBUTE);
				int number = (int) request.getSession().getAttribute(NUMBER_ATTRIBUTE);
				long hostelId = (long) request.getSession().getAttribute(HOSTEL_ID_ATTRIBUTE);
				DemandLogic.makeBooking(dateIn, dateOut, number, hostelId, clientId);*/
				page = ConfigurationManager.getProperty(BONUS_BANS_PAGE_PATH);
			}
		} 
		return page;
	}	

}
