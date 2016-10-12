package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

/**
 * Command for redirecting client to user or admin page using information about their role. This command is invoked 
 * when application cannot distinguish initial command or it equals null.
 * 
 * @author Anton Suprunyuk
 */
public class EmptyCommand implements ActionCommand {

	private static final String MAIN_PAGE_PATH = "path.page.main";
	private static final String ADMIN_PAGE_PATH = "path.page.admin";
	private static final String CLIENT_ID_ATTRIBUTE = "clientId";
	
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
		if (request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE) != null 
				&& (long) request.getSession().getAttribute(CLIENT_ID_ATTRIBUTE) == 0) {
			page = ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
		}
		return page;
	}

}
