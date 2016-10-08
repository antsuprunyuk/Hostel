package by.suprunyuk.hostel.service;

import org.apache.log4j.Logger;

import by.suprunyuk.hostel.dao.UserDAO;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.exception.PasswordEncodingException;
import by.suprunyuk.hostel.utils.PasswordEncoder;

public class LoginLogic {
	private static final Logger LOGGER = Logger.getLogger(LoginLogic.class);
	// admin1   abc111
	// admin2   abc222
	// admin3   abc333
	public static boolean checkLogin(String enteredLogin, String enteredPassword) {
		UserDAO userDAO = new UserDAO();
		String encodedLogin = null;
		try {
			encodedLogin = PasswordEncoder.encode(enteredPassword);
		} catch (PasswordEncodingException e) {
			LOGGER.error("error during encoding password", e);
		}
		User user = userDAO.readUserByLogin(enteredLogin);
		boolean valid = false;
		if (user != null) {
			valid = encodedLogin.equals(user.getUserPass());
		}
		return valid;
	}
}