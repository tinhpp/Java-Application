package org.htm.tools.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	private static final String FILE_CONFIG = "\\resources\\config.properties";
	
	public String getProperty(String nameKey) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		String currentDir = System.getProperty("user.dir");
		String result = null;
		try {
			inputStream = new FileInputStream(currentDir + FILE_CONFIG);
			// load properties from file
			properties.load(inputStream);
			result = properties.getProperty(nameKey);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
