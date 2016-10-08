package by.suprunyuk.hostel.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.suprunyuk.hostel.dao.ClientDAO;
import by.suprunyuk.hostel.dao.UserDAO;
import by.suprunyuk.hostel.entity.Client;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.entity.UserRole;
import by.suprunyuk.hostel.exception.PasswordEncodingException;
import by.suprunyuk.hostel.utils.PasswordEncoder;

public class ClientRegistration {
	private static final Logger LOGGER = Logger.getLogger(ClientRegistration.class);
	private static final String PARAM_LOGIN = "login";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_SURNAME = "surname";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_EMAIL = "email";

	public static void register(HttpServletRequest request) {
		String login = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		String surname = request.getParameter(PARAM_SURNAME);
		String name = request.getParameter(PARAM_NAME);
		String email = request.getParameter(PARAM_EMAIL);
		
		Client client = new Client(surname, name, email);
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.create(client);
		client = clientDAO.readLastRegistredClient();
		String encodedPassword = null;
		try {
			encodedPassword = PasswordEncoder.encode(password);
			User user = new User(login, encodedPassword, UserRole.CLIENT, client.getClientId());
			UserDAO userDAO = new UserDAO();
			userDAO.create(user);
		} catch (PasswordEncodingException e) {
			LOGGER.error("error during encoding password", e);
		}
	}
}
