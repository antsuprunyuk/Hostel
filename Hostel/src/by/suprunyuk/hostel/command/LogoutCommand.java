package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

/**
 * Command for logging out client and invalidate session
 * 
 * @author Anton Suprunyuk
 */
public class LogoutCommand implements ActionCommand {

	private static final String LAST_PAGE_ATTRIBUTE = "lastPage";
	private static final String LANG_ATTRIBUTE = "lang";
	private static final String PROJECT_PREFIX = "/Hostel";
	private static final String LOGIN_PAGE_PATH = "path.page.login";
	
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
		String lang = (String) request.getSession().getAttribute(LANG_ATTRIBUTE);
		String lastPage = (String) request.getSession().getAttribute(LAST_PAGE_ATTRIBUTE);
		request.getSession().invalidate();
		request.getSession().setAttribute(LANG_ATTRIBUTE, lang);
		if (lastPage == null) {
			lastPage = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
		}
		lastPage = lastPage.replace(PROJECT_PREFIX, "");
		return lastPage;
	}
}
