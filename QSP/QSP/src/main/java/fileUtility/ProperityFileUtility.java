package fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProperityFileUtility{
	
	public String toGetDataFromProperitiesFile(String key) throws IOException {
		
		FileInputStream fs =new FileInputStream("./src/test/resources/commonData.properities");
		
		Properties pp = new Properties();
		pp.load(fs);
		
		String value = pp.getProperty(key);
		return value;
		
	
	}
}
