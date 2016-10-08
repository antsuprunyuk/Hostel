package by.suprunyuk.hostel.command;

import javax.servlet.http.HttpServletRequest;

import by.suprunyuk.hostel.resource.ConfigurationManager;

public class PricesPageJumpCommand implements ActionCommand {

	private static final String PRICES_PAGE_PATH = "path.page.prices";
	
	@Override
	public String execute(HttpServletRequest request) {
		String lastPage = ConfigurationManager.getProperty(PRICES_PAGE_PATH);
		return lastPage;
	}

}
