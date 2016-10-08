package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class SwitchLanguageCommand implements ActionCommand {

	private static final String LANG_ATTRIBUTE = "lang";
	private static final String LAST_PAGE_ATTRIBUTE = "lastPage";
	private static final String PROJECT_PREFIX = "/Hostel";
	private static final String MAIN_PAGE_PATH = "path.page.main";
	
	@Override
	public String execute(HttpServletRequest request) {
		String lang = request.getParameter(LANG_ATTRIBUTE);
		if (lang != null) {
			request.getSession().removeAttribute(LANG_ATTRIBUTE);
			request.getSession().setAttribute(LANG_ATTRIBUTE, lang);
		}	
		String lastPage =  (String) request.getSession().getAttribute(LAST_PAGE_ATTRIBUTE);
		if (lastPage == null) {
			lastPage = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
		}
		lastPage = lastPage.replace(PROJECT_PREFIX, "");
		return lastPage;
	}
}
