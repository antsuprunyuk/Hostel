package by.suprunyuk.hostel.resource;

import java.util.ResourceBundle;

public class MessageManager {

	private final ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.messages");

	public  Object getProperty(String key) {
		return resourceBundle.getString(key);
	}
	

}
