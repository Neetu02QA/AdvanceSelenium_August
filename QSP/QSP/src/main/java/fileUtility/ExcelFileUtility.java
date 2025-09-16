package fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String toReadTheDatafromExcel(String sheet, int rowNum,int cellNum) throws IOException {
		
		FileInputStream fs = new FileInputStream("./resources/tdl.xlsx");
		
		Workbook wb = WorkbookFactory.create(fs);
		
		String data = wb.getSheet(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
		
		wb.close();
		
		
		return data;
	}
	
	public int toGetTheRowcount(String sheet) throws EncryptedDocumentException, IOException {
		
		FileInputStream fs = new FileInputStream("./resources/tdl.xlsx");
				
		Workbook wb = WorkbookFactory.create(fs);
		int lastRowNum = wb.getSheet(sheet).getLastRowNum();
				
		System.out.println(lastRowNum);
		return lastRowNum;
				
			}
	
	public List<String> toReadMultipleSetOfData(int lastRowNum, String sheet, int CellNum) throws EncryptedDocumentException, IOException {
		
		
		List<String> dataList = new ArrayList<>();
		
		FileInputStream fs = new FileInputStream("./resources/tdl.xlsx");
		
		Workbook wb = WorkbookFactory.create(fs);

		//pass the data in method int lastRowNum
		
		for(int i =1; i<=lastRowNum; i++){
			String data = wb.getSheet(sheet).getRow(i).getCell(1).getStringCellValue();
			
			dataList.add(data);
			
		}
		
		return dataList;
		
		
		
	}
}



//int lastRowNum = wb.getSheet("Campaigns").getLastRowNum();
//
//System.out.println(lastRowNum);
//


	
