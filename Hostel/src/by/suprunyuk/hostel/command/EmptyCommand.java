package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	private static final String MAIN_PAGE_PATH = "path.page.main";
	private static final String ADMIN_PAGE_PATH = "path.page.admin";
	private static final String CLIENT_ID_ATTRIBUTE = "clientId";
	
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
