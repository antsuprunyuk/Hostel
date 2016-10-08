package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class MainPageJumpCommand implements ActionCommand {

	private static final String MAIN_PAGE_PATH = "path.page.main";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
		return page;
	}

}
