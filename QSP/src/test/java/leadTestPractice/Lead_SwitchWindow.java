package leadTestPractice;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
	public class Lead_SwitchWindow {	
		public static void main(String[] args) throws Throwable {		
			ChromeOptions settings = new ChromeOptions();		
			Map<String, Object> prefs = new HashMap<>(); 		
			prefs.put("profile.password_manager_leak_detection", false); 		
			settings.setExperimentalOption("prefs", prefs);						
			//Properties File	
			// Get the java representation object of physical file		
			FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properities");		
			Properties pf = new Properties();		
			pf.load(fis);						
			String BROWSER = pf.getProperty("browser");		
			String URL = pf.getProperty("url");		
			String USERNAME = pf.getProperty("username");		
			String PASSWORD = pf.getProperty("password");					    	
			//launch the broswer as per depend on the properties file    	    	    
			WebDriver driver=null;	    	    
			if(BROWSER.equals("edge")) {			 
				driver= new EdgeDriver();			
				}else if(BROWSER.equals("chrome")) {			 
					driver= new ChromeDriver();		
					}else if (BROWSER.equals("firefox")){			 
						driver= new FirefoxDriver();		
						}	
			//Navigate to NinzaCRM 	    		
			driver.manage().window().maximize();		
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));		
			driver.get("http://49.249.28.218:8098/");			
			//Login to NinzaCRM by using Valid Credentials				
			driver.findElement(By.id("username")).sendKeys(USERNAME);		
			driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);			
			driver.findElement(By.xpath("//button[@type='submit']")).click();			
			//Click on Leads Link			
			driver.findElement(By.linkText("Leads")).click();  				
			//Click on add lead button		
			driver.findElement(By.xpath("//span[text()='Create Lead']")).click();				
			//Generate the random number					
			Random rNum = new Random();			
			//Use the nextInt method to generate random number and specify the value/length		
			int ranNum = rNum.nextInt();							
			//Read the data from the Excel File								
			FileInputStream fis1 = new FileInputStream("./resources/TestData.xlsx");				    		
			Workbook wb = WorkbookFactory.create(fis1);				
			String leadname = wb.getSheet("Leads").getRow(1).getCell(1).getStringCellValue();		
			String leadstatus = wb.getSheet("Leads").getRow(1).getCell(2).getStringCellValue();		
			String campany = wb.getSheet("Leads").getRow(1).getCell(3).getStringCellValue();		
			String leadsource= wb.getSheet("Leads").getRow(1).getCell(4).getStringCellValue();		
			String Industry = wb.getSheet("Leads").getRow(1).getCell(5).getStringCellValue();		
			String phone = wb.getSheet("Leads").getRow(1).getCell(6).getStringCellValue();		
			//String campaign = wb.getSheet("Leads").getRow(1).getCell(7).getStringCellValue();				    	
			//Create Leads		    	    
			driver.findElement(By.name("name")).sendKeys(leadname); 	    
			driver.findElement(By.name("company")).sendKeys(campany); 	    
			driver.findElement(By.name("leadSource")).sendKeys(leadsource); 		
			driver.findElement(By.name("industry")).sendKeys(Industry); 		
			driver.findElement(By.name("phone")).sendKeys(phone); 		
			driver.findElement(By.name("leadStatus")).sendKeys(leadstatus);  		
			driver.findElement(By.xpath("//label[text()='Campaign']/..//button[@type='button']")).click();
			// xpath by sorrounding				
			//switching to child and adding campaign		
			String parentId = driver.getWindowHandle();		
			Set<String> allids = driver.getWindowHandles();		
			allids.remove(parentId);		
			for(String string: allids) {			
				driver.switchTo().window(string);		
				}					
			driver.findElement(By.xpath("//td[text()='CAM00002']/..//button[@class='select-btn']")).click();		
			Thread.sleep(2000);				
			//swithcing control to parent			
			driver.switchTo().window(parentId);				
			//clicking on createlead			
			driver.findElement(By.xpath("//button[@type='submit']")).click();			
			Thread.sleep(1000);			
			//driver.findElement(By.xpath("//button[@aria-label=close']")).click();				
			//delete the button						
			driver.findElement(By.xpath("//td[text()='Manaswini']/..//i[@title='Delete']")).click();			
			Thread.sleep(1000);						
			driver.findElement(By.xpath("//input[@value='Delete']")).click();			
			driver.close();	
			}
		 
		}
		
		
	