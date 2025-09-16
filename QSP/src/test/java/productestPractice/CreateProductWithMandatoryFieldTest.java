package productestPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateProductWithMandatoryFieldTest {

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
		
		//Create product
		driver.findElement(By.name("productName")).sendKeys("mobile");
		WebElement quantity = driver.findElement(By.name("quantity"));
		//input tag then move to element x n y axis 
		Thread.sleep(4000);
		quantity.clear();
		quantity.sendKeys("123456");
		WebElement pricepp = driver.findElement(By.name("price"));
		Thread.sleep(4000);
		pricepp.clear();
		pricepp.sendKeys("500");
		
		WebElement category = driver.findElement(By.name("productCategory"));
		Select sel1 = new Select(category);
		sel1.selectByIndex(3);
		
		WebElement vendor = driver.findElement(By.name("vendorId"));
		
		Select sel2 = new Select(vendor);
		sel2.selectByIndex(2);
		
	}

}
