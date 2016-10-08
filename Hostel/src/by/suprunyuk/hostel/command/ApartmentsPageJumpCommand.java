package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class ApartmentsPageJumpCommand implements ActionCommand {
	
	private static final String APARTMENTS_PAGE_PATH = "path.page.apartments";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(APARTMENTS_PAGE_PATH);
		return page;
	}

}
