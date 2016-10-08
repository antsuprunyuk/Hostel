package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.dao.ClientDAO;
import by.suprunyuk.hostel.dao.UserDAO;
import by.suprunyuk.hostel.entity.User;
import by.suprunyuk.hostel.entity.UserRole;
import by.suprunyuk.hostel.resource.ConfigurationManager;
import by.suprunyuk.hostel.service.LoginLogic;

public class LoginCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String MAIN_PAGE_PATH = "path.page.main";
	private static final String ADMIN_PAGE_PATH = "path.page.admin";
	private static final String LOGIN_ERROR_ATTRIBUTE = "loginError";
	private static final String USER_ATTRIBUTE = "user";
	private static final String CLIENT_ID_ATTRIBUTE = "clientId";
	private static final String USER_ROLE_ATTRIBUTE = "userRole";
	
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.login");
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		if (LoginLogic.checkLogin(login, pass)) {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.readUserByLogin(login);
			UserRole userRole = user.getUserRole(); 
			long clientId = user.getClientId();
			request.getSession().setAttribute(USER_ATTRIBUTE, login);
			request.getSession().setAttribute(USER_ROLE_ATTRIBUTE, userRole);
			request.getSession().setAttribute(CLIENT_ID_ATTRIBUTE, clientId);
			if (clientId != 0) {
				page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
			} else {
				page = ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
			}
		} else {
			request.getSession().setAttribute(LOGIN_ERROR_ATTRIBUTE, new Boolean(true));
		}
		return page;
	}
}
