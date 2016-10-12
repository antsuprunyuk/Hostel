package by.suprunyuk.hostel.resource;

import java.util.ResourceBundle;

/**
 *  Manager for searching configuration messages for application. Actual data are page paths
 * 
 * @author Anton Suprunyuk
 */
public class ConfigurationManager {
	/**
	 * ResourceBundle instance for keeping bundle address and searching data in the bundle
	 */
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.config");
	
	/**
	 * default constructor
	 */
	private ConfigurationManager() {
	}
	
	/**
	 * returns message from the ResourceBundle found by key
	 * 
	 * @param key key for search specific message
	 * @return message from the ResourceBundle
	 */
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
