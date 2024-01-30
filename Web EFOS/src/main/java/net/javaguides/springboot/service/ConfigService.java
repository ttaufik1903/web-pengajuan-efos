package net.javaguides.springboot.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {

	Properties prop = this.readPropertiesFile("config.properties");

	public final String url_servis_paramater = prop.getProperty("url_servis_parameter");
	  
	public final String url_servis_prospek = prop.getProperty("url_servis_prospek");

	
	public Properties readPropertiesFile(String fileName) {
		// FileInputStream fis = null;
		Properties prop = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(fileName);
			// fis = new FileInputStream(classLoader.getSystemResourceAsStream(fileName));
			prop = new Properties();
			prop.load(inputStream);
			inputStream.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {

		}
		return prop;
	}

	public String stackTraceToString(Throwable e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.toString());
		for (StackTraceElement element : e.getStackTrace()) {
			sb.append(element.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
