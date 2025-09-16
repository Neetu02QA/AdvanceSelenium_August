package productestPractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class Product_ReadtWrite {

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
//				EdgeOptions settings1 = new EdgeOptions();
				Map<String, Object> prefs = new HashMap<>();
			    prefs.put("profile.password_manager_leak_detection", false);
			    settings.setExperimentalOption("prefs", prefs);      //preferences- key - value pair//org.openqa.selenium.NoSuchElementException
				//Launch the browser
//				WebDriver driver = new ChromeDriver(settings);
				
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
		
		//Login into the ninja CRM
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);

		//click on product link
		driver.findElement(By.linkText("Products")).click();
		//click add product button
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		
		
		//generate Random number
		//Step 1. Create the object of random class
		Random rn = new Random();
		
		//Step 2.
		int ranNum = rn.nextInt(1000);
//		String str = String.valueOf(ranNum);
		
		
		//Read data from excel sheet
		
		FileInputStream fs1 = new FileInputStream("./resources/td.xlsx");//write message
		Workbook wb = WorkbookFactory.create(fs1);//we cannt use new keyword bc create key word is static
		
		String productName = wb.getSheet("Product").getRow(1).getCell(1).getStringCellValue();
		
//		String pName = productName+str;
		
		String qty = wb.getSheet("Product").getRow(1).getCell(2).getStringCellValue();
		String pric = wb.getSheet("Product").getRow(1).getCell(3).getStringCellValue();
		
		
		//illegal State Exception - datatype in excel
		
		//copy n paste path again n again excel
		
		
		
		//Create product
		driver.findElement(By.name("productName")).sendKeys(productName);
		WebElement quantity = driver.findElement(By.name("quantity"));
		//input tag then move to element x n y axis 
		Thread.sleep(4000);
		quantity.clear();
		quantity.sendKeys(qty);
		WebElement pricepp = driver.findElement(By.name("price"));
		Thread.sleep(4000);
		pricepp.clear();
		pricepp.sendKeys(pric);
		
		WebElement category = driver.findElement(By.name("productCategory"));
		Select sel1 = new Select(category);
		sel1.selectByIndex(3);
		
		Thread.sleep(4000);
		WebElement vendor = driver.findElement(By.name("vendorId"));//use index bc in mac it vendor id is nt showing
		
		Select sel2 = new Select(vendor);
		sel2.selectByIndex(2);
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(4000);
		
//		WebElement sucessMsg = driver.findElement(By.xpath("//div[text()='Product "+productName+" Successfully Added']"));//dynamic xpath - Product iphoneNeetu1 Successfully Added
		WebElement sucessMsg = driver.findElement(By.xpath("//div[text()='Product "+productName+" Successfully Added']"));//dynamic xpath - Product iphoneNeetu1 Successfully Added
		
		String msg = sucessMsg.getText();
		System.out.println(msg);
		
		
		//write data back to excel 
		Cell c = wb.getSheet(productName).getRow(1).createCell(4);
	
		c.setCellType(CellType.STRING);
		c.setCellValue(msg);
		
		FileOutputStream fso = new FileOutputStream("./resources/td.xlsx");
		wb.write(fso);
		wb.close();
		
	}

}

//network setup, server issues, or firewall problems.
//socket exception using dynamic xpath n we are using ren no also n server is nt working



//dynamic xpath - Product iphoneNeetu1 Successfully Added
//for toast message we have to use source n pause the screen n find xpath/locator
		
