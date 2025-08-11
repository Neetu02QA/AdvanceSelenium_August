package leadTestPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateLead {

	public static void main(String[] args) throws Throwable {
		
		
		//Properties file
		//1.Get the java representation object of physical file
		FileInputStream fs = new FileInputStream("./src/test/resources/commonData.properities");
		
		//2.Create object of properties file 
		Properties pf = new Properties();
		
		//3.Load all the keys 
		pf.load(fs);
		
		//4.Read the data from it with the help of keys 
		String BROWSER = pf.getProperty("browser");
		String URL = pf.getProperty("url");
		String USERNAME = pf.getProperty("username");
		String PASSWORD = pf.getProperty("password");
		
		//launch the browser

		
		ChromeOptions settings = new ChromeOptions();//when ever notifications popups
		//		EdgeOptions settings1 = new EdgeOptions();
		Map<String, Object> prefs = new HashMap<>();
	    prefs.put("profile.password_manager_leak_detection", false);
	    settings.setExperimentalOption("prefs", prefs);      //preferences- key - value pair//org.openqa.selenium.NoSuchElementException
		//Launch the browser
//		WebDriver driver = new ChromeDriver(settings);
		
		WebDriver driver = null;                         //I don knw which browser to open so I gave WebDriver
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver(settings);
		}else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		}else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
			
		
		
		
			//give conditions
			//launch the browser
			//WebDriver driver = new ChromeDriver();//constructor call
			
			//Maximize the window
			driver.manage().window().maximize();
			
			//implicit wait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			
			//Navigate to ninja CRM
			driver.get(URL);
			
			//Login into the ninza CRM
			driver.findElement(By.id("username")).sendKeys(USERNAME);
			driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			
			//Click on product link
			driver.findElement(By.linkText("Leads")).click();
			Thread.sleep(4000);
			//click add product button
			driver.findElement(By.xpath("//span[text()='Create Lead']")).click();
			
			// generate Random number
			//Step 1.Create the object of random class
			Random rn = new Random();

			//step 2.Use nextInt method to generate random number and specify the value/length
			int ranNum = rn.nextInt(1000);
			//String str = String.valueOf(ranNum);


			//Read the data from excel sheet
		
			FileInputStream fs1 =  new FileInputStream("./resources/tdl.xlsx");
			
			Workbook wb = WorkbookFactory.create(fs1);

			String leadName = wb.getSheet("Lead").getRow(1).getCell(1).getStringCellValue()+ranNum;
			String  company= wb.getSheet("Lead").getRow(1).getCell(2).getStringCellValue();
			String industry = wb.getSheet("Lead").getRow(1).getCell(3).getStringCellValue();
			String phoneNo = wb.getSheet("Lead").getRow(1).getCell(4).getStringCellValue();
			
			
			//Create product
			driver.findElement(By.name("name")).sendKeys(leadName);
			driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(company);
			driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys(industry);
			driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(phoneNo);
		
			Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@type='text' and @readonly and @required and @value='']/../..//button[@type='button']")).click();
			
			
			Thread.sleep(4000);
			driver.findElement(By.xpath("(//td[text()='CAM00001'])[1]/..//button[text()='Select']")).click();
		
	}

}

//--(//td[text()='CAM00001'])[1]/..//button[text()='Select']