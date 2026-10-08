package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author B.Nandini This is a reusable class to work with property file
 */
public class PropertyFileUtility {

	/**
	 * This method is used to fetch value from property file using key
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String fetchDataFromPropFile(String key) throws IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerCommondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}

	/**
	 * This method is used to update data to property file
	 * 
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void writeDataToPropFile(String key, String value) throws IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerCommondata.properties");
		Properties p = new Properties();
		p.load(fis);
		p.put(key, value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VtigerCommondata.properties");
		p.store(fos, "Updated");
	}

}
