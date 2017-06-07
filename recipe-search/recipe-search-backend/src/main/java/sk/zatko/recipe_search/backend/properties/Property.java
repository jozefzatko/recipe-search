package sk.zatko.recipe_search.backend.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

	public static String getProperty(String key) throws FileNotFoundException, IOException {
		
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream("src//main//resources//settings.properties");
		properties.load(stream);
		
		String result = properties.getProperty(key);
		
		properties = null;
		stream.close();	
				
		return result;
	}
}
