package by.suprunyuk.hostel.service;

import org.apache.log4j.Logger;

import by.suprunyuk.hostel.dao.UserDAO;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.exception.PasswordEncodingException;
import by.suprunyuk.hostel.utils.PasswordEncoder;

/**
 * implements login logic of the application
 * 
 * @author AntonSuprunyuk
 */
public class LoginLogic {
	
	/**
	 * log4j-logger instance for logging errors  
	 */
	private static final Logger LOGGER = Logger.getLogger(LoginLogic.class);

	/**
	 * checks login and password whether they are valid or not
	 * 
	 * @param enteredLogin user or admin login for authorization
	 * @param enteredPassword user or admin password for authorization
	 * @return boolean result of check whether login and pass are valid
	 */
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