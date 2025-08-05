package campaignTestPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleSetOfDataFromExcelTestPractice {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		
		FileInputStream fs = new FileInputStream("./resources/testDataforloop.xlsx");
		
		Workbook wb = WorkbookFactory.create(fs);
		
		int lastRowNum =wb.getSheet("Campaigns").getLastRowNum();
		System.out.println(lastRowNum);
		
		for(int i = 1; i <lastRowNum; i++) {
//		for(int i = 1; i <=14; i++) {
			
			String campaignName = wb.getSheet("Campaigns").getRow(i).getCell(1).getStringCellValue();
			System.out.println(campaignName);
			
		}
		
	}

}
