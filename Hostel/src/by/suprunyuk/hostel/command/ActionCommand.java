package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Defines an interface to employ it as the base of Command pattern which is used in this application.
 *  
 * @author Anton Suprunyuk
 */
public interface ActionCommand {
	
	/**
	 * returns String interpretation of the page user will be redirected to after doing business logic obtaining information
	 * from the request object
	 * 
	 * @param request an object implementing HttpServletRequest interface
	 * @return String interpretation of the page user will be redirected to
	 * @see javax.servlet.http.HttpServletRequest
	 */
	public String execute(HttpServletRequest request);
}
