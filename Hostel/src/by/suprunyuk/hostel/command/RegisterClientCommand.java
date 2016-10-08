package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.dao.ClientDAO;
import by.suprunyuk.hostel.dao.UserDAO;
import by.suprunyuk.hostel.entity.Client;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.entity.UserRole;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.ClientRegistration;

public class RegisterClientCommand implements ActionCommand {

	private static final String REGISTRATION_SUCCED_PAGE_PATH = "path.page.registrationsucced";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(REGISTRATION_SUCCED_PAGE_PATH);
		ClientRegistration.register(request);
		return page;
	}

}
