package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for returning to the previous page after application crash
 * 
 * @author Anton Suprunyuk
 */
public class CrashRecoveryCommand  implements ActionCommand {
	private static final String LAST_PAGE_ATTRIBUTE = "lastPage";
	private static final String PROJECT_PREFIX = "/Hostel";

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
		
		String lastPage =  (String) request.getSession().getAttribute(LAST_PAGE_ATTRIBUTE);
		lastPage = lastPage.replace(PROJECT_PREFIX, "");
		return lastPage;
	}
}