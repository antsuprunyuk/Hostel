package by.suprunyuk.hostel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.entity.Demand;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.DemandLogic;

/**
 * Command reads all client demands and then redirects to the demand manage page
 * 
 * @author Anton Suprunyuk
 */
public class DemandsManageCommand implements ActionCommand {
	private static final String  CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String ADMIN_PAGE_PATH = "path.page.admin";
	private static final String DEMANDS_MANAGE_PAGE_PATH = "path.page.demandsmanage";

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
				List<Demand> demands = DemandLogic.readAllDemands();
				request.getSession().setAttribute("demands", demands);
				page = ConfigurationManager.getProperty(DEMANDS_MANAGE_PAGE_PATH);
			}
		} 
		return page;
	}
}
