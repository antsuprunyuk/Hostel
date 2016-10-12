package test.by.suprunyuk.hostel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * JUnit Class for testing resources presence 
 *  
 * @author Anton
 *
 */
public class JUnitTest {

	/**
	 * String identifier for configuration file with path from the application root
	 */
	public static final String CONFIG_FILE = "src/properties/config.properties";
	
	/**
	 * String identifier for properties file with path from the application root
	 */
	public static final String LOG4J_PROPERTIES_FILE = "src/log4j.properties";

	/**
	 * tests if configuration file exists
	 */
    @Test
    public void testFileExist() {
    	File file = new File(CONFIG_FILE);
        assertTrue(file.exists()&&file.isFile());
    }
    
	/**
	 * tests if properties file exists
	 */
    @Test
    public void log4jPropertiesFileExest() {
    	File file = new File(LOG4J_PROPERTIES_FILE);
    		assertTrue(file.exists()&&file.isFile());
    	
    }

}
