package test.by.suprunyuk.hostel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class JUnitTest {

	public static final String CONFIG_FILE = "src/properties/config.properties";
	public static final String LOG4J_PROPERTIES_FILE = "src/log4j.properties";

    @Test
    public void testFileExist() {
    	File file = new File(CONFIG_FILE);
        assertTrue(file.exists()&&file.isFile());
    }
    
    @Test
    public void log4jPropertiesFileExest() {
    	File file = new File(LOG4J_PROPERTIES_FILE);
    		assertTrue(file.exists()&&file.isFile());
    	
    }

}
