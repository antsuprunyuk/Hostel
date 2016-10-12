package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

/**
 * Command for redirecting to main page
 * 
 * @author Anton Suprunyuk
 *
 */
public class MainPageJumpCommand implements ActionCommand {

	private static final String MAIN_PAGE_PATH = "path.page.main";
	
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
		return page;
	}

}
