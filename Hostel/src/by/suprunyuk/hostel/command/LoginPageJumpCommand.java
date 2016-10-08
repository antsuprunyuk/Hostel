package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class LoginPageJumpCommand implements ActionCommand {

	private static final String LOGIN_PAGE_PATH = "path.page.login";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
		return page;
	}
}
