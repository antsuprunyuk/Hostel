package by.suprunyuk.hostel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.entity.Client;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.ClientManageLogic;
/**
 * Command for ban and unban clients
 * 
 * @author Anton Suprunyuk
 */
public class BanClientCommand implements ActionCommand {

	private static final String CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String ADMIN_PAGE_PATH = "path.page.admin";
	private static final String BONUS_BANS_PAGE_PATH = "path.page.bonusbans";

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
		String page = ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
		long clientId;
		if (request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE) != null) {
			clientId = (long) request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE);
			if (clientId == 0) {
				long currentClient = Long.parseLong(request.getParameter("clientId"));
				ClientManageLogic.ban(currentClient, true);
				List<Client> clients = ClientManageLogic.readAllClients();
				request.getSession().setAttribute("clients", clients);
				page = ConfigurationManager.getProperty(BONUS_BANS_PAGE_PATH);
			}
		} 
		return page;
	}	
}
