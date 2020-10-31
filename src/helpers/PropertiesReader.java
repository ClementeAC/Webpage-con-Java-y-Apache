package helpers;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	Properties properties = new Properties();

	public PropertiesReader() {
		try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getQuery(String query) {
		return properties.getProperty(query);
	}

	public static PropertiesReader getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
