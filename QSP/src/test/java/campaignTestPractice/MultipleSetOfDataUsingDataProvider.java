package campaignTestPractice;

import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import fileUtility.ExcelFileUtility;


public class MultipleSetOfDataUsingDataProvider {
	
	@DataProvider
	public Object[][] loginDetails() throws EncryptedDocumentException, IOException {
		
		ExcelFileUtility ex = new ExcelFileUtility();
		int lastRow = ex.toGetTheRowcount("Campaigns");
		
		Object[][] obj = new Object[lastRow][2];
		
		for (int i = 1; i < lastRow; i++) {
			obj[i][0]=ex.toReadTheDatafromExcel("Campaigns", i+1, 1);
			obj[i][1]=ex.toReadTheDatafromExcel("Campaigns", i+1, 2);
		}
		return obj;
	}
	
	@Test(dataProvider = "loginDetails")
	
	public void login(String un, String pwd) {
		System.out.println(un+"=========="+pwd);
	}
}
//we are data provider when login with different credentials 