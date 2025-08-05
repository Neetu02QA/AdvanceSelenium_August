package campaignTestPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel_ReduceCode {

	public static void main(String[] args) throws Throwable {	
		
		//excel file 
				//Step 1. Create java representation object of physical file
				FileInputStream fs1 = new FileInputStream("./resources/testData.xlsx");
				
				//Step 2-Open excel in read mode
			
				Workbook wb = WorkbookFactory.create(fs1);
				
				String campaignName = wb.getSheet("Campaigns").getRow(1).getCell(1).getStringCellValue();
				System.out.println(campaignName);
				
				
				String targetSize = wb.getSheet("Campaigns").getRow(1).getCell(2).getStringCellValue();
				System.out.println(targetSize);
				
				

	}

}
