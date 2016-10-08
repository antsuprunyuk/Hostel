package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

public class CrashRecoveryCommand  implements ActionCommand {
	private static final String LAST_PAGE_ATTRIBUTE = "lastPage";
	private static final String PROJECT_PREFIX = "/Hostel";

	@Override
	public String execute(HttpServletRequest request) {
		
		String lastPage =  (String) request.getSession().getAttribute(LAST_PAGE_ATTRIBUTE);
		lastPage = lastPage.replace(PROJECT_PREFIX, "");
		return lastPage;
	}
}