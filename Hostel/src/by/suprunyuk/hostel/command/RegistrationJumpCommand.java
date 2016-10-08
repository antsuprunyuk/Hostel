package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class RegistrationJumpCommand implements ActionCommand {

	private static final String REGISTRATION_PAGE_PATH = "path.page.registration";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
		return page;
	}

}
