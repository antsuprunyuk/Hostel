package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory method to produce a command implementing ActionCommand interface from the request. If a command is not defined in 
 * request EmptyCommand will be returned. 
 * 
 * @author Anton Suprunyuk
 */
public class ActionFactory {

	/**
	 * String identifier for command parameter
	 */
	private static final String COMMAND_PARAMETER = "command";
	
	/**
	 * String identifier for wrong action attribute
	 */
	private static final String WRONG_ACTION_ATTRIBUTE = "wrongAction";
	
	/**
	 * String identifier for wrong action message
	 */
	private static final String WRONG_ACTION_MESSAGE =  "command not found or incorrect";
	
	/**
	 * returns a command object produced from the request
	 * @param request an object implementing HttpServletRequest interface
	 * @return distinct command that will be performed
	 */
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
			request.setAttribute(WRONG_ACTION_ATTRIBUTE, action + WRONG_ACTION_MESSAGE);
		}
		return current;
	}
}