package campaignTestPractice;

import java.io.FileInputStream;
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
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createCampaignWithMandatoryFieldsTest {

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
		//Click on campaign link
		driver.findElement(By.linkText("Campaigns")).click();

		//Create campaign with Mandatory fields
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Avengers");
		WebElement targSize = driver.findElement(By.name("targetSize"));
		targSize.clear();
		targSize.sendKeys("959466");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		//Verify the successful message
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();

		if (msg.contains("Avengers")) {
		    System.out.println("Campaign created successfully");
		} else {
		    System.out.println("Campaign is not created");
		}


	}

}
