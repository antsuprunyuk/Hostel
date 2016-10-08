package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class CoordinatesPageJumpCommand implements ActionCommand {

	private static final String COORDINATES_PAGE_PATH = "path.page.coordinates";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(COORDINATES_PAGE_PATH);
		return page;
	}

}
