package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

/**
 * Command that redirected user to apartment page
 * 
 * @author Anton Suprunyuk
 */
public class ApartmentsPageJumpCommand implements ActionCommand {
	
	/**
	 * String identifier of apartments page path
	 */
	private static final String APARTMENTS_PAGE_PATH = "path.page.apartments";
	
	/**
	 * returns String interpretation of the page user will be redirected to 
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(APARTMENTS_PAGE_PATH);
		return page;
	}

}
