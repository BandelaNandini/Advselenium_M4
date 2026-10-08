package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author B.Nandini This is a reusable class to work with JSON file
 */
public class JsonFileUtility {

	/**
	 * This is a reusable method to fetch data from json file
	 * 
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String fetchTheDataFromJson(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader("./src/test/resources/VtigerCMData.json"));
		JSONObject jsobj = (JSONObject) obj;
		String data = jsobj.get(key).toString();
		return data;
	}

}
