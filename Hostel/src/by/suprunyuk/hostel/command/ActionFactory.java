package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.MessageManager;

public class ActionFactory {

	private static final String COMMAND_PARAMETER = "command";
	private static final String WRONG_ACTION_ATTRIBUTE = "wrongAction"; 
	private static final String WRONG_ACTION_ATTRIBUTE_MESSAGE = "message.wrongAction";
	
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter(COMMAND_PARAMETER);
		if (action == null || action.isEmpty()) {
			return current;
		}

		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute(WRONG_ACTION_ATTRIBUTE, action + new MessageManager().getProperty(WRONG_ACTION_ATTRIBUTE_MESSAGE));
		}
		return current;
	}
}