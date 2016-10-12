package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.ClientRegistration;

/**
 * Command for client registration
 * 
 * @author Anton Suprunyuk
 */
public class RegisterClientCommand implements ActionCommand {

	private static final String REGISTRATION_SUCCED_PAGE_PATH = "path.page.registrationsucced";
	
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
		String page = ConfigurationManager.getProperty(REGISTRATION_SUCCED_PAGE_PATH);
		ClientRegistration.register(request);
		return page;
	}

}
