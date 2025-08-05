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

public class P_1 {

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
//						EdgeOptions settings1 = new EdgeOptions();
						Map<String, Object> prefs = new HashMap<>();
					    prefs.put("profile.password_manager_leak_detection", false);
					    settings.setExperimentalOption("prefs", prefs);      //preferences- key - value pair//org.openqa.selenium.NoSuchElementException
						//Launch the browser
//						WebDriver driver = new ChromeDriver(settings);
						
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
driver.findElement(By.linkText("Products")).click();
//click add product button
driver.findElement(By.xpath("//span[text()='Add Product']")).click();



// generate Random number
//Step 1.Create the object of random class
Random rn = new Random();

//step 2.Use nextInt method to generate random number and specify the value/length
int ranNum = rn.nextInt(1000);
//String str = String.valueOf(ranNum);


//Read the data from excel sheet

FileInputStream fs1 =  new FileInputStream("./resources/tdforWriteMsg.xlsx");

Workbook wb = WorkbookFactory.create(fs1);

String productName = wb.getSheet("Product").getRow(1).getCell(1).getStringCellValue()+ranNum;

//String pName = productName+str;

String qty = wb.getSheet("Product").getRow(1).getCell(2).getStringCellValue();

String price = wb.getSheet("Product").getRow(1).getCell(3).getStringCellValue();



//Create product
driver.findElement(By.name("productName")).sendKeys(productName);
WebElement quantity = driver.findElement(By.name("quantity"));
Thread.sleep(4000);
quantity.clear();
quantity.sendKeys(qty);
WebElement pricepp = driver.findElement(By.name("price"));
Thread.sleep(4000);
pricepp.clear();
pricepp.sendKeys(price);


WebElement category = driver.findElement(By.name("productCategory"));
Select sel1= new Select(category);
sel1.selectByIndex(3);

WebElement vendor = driver.findElement(By.name("vendorId"));
Select sel2 = new Select(vendor);
sel2.selectByIndex(2);

driver.findElement(By.xpath("//button[@type='submit']")).click();

Thread.sleep(1000);

WebElement successMsg = driver.findElement(By.xpath("//div[contains(@class,'Toastify')]"));
String msg = successMsg.getText();
System.out.println(msg);

//write data back to excel
Cell c = wb.getSheet("Product").getRow(1).createCell(4);
c.setCellType(CellType.STRING);
c.setCellValue(msg);

FileOutputStream fso = new FileOutputStream("./resources/tdforWriteMsg.xlsx");
wb.write(fso);
wb.close(); 

}
}

//file nt fid exception - if u hv more file s delete the file from resources folder n do maven clean - update project - force clean or realease

//see file in eclipse if message has been written or nt 
////div[contains(@class,'Toastify')]   //div[@class='Toastify_toast-body']