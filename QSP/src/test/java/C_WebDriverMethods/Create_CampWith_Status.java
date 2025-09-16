package C_WebDriverMethods;

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

import fileUtility.ExcelFileUtility;
import fileUtility.ProperityFileUtility;
import webDriverUtility.WebDriverUtility;
import webDriverUtility.WebDriverUtility1;

public class Create_CampWith_Status {

	public static void main(String[] args) throws Throwable {

		ProperityFileUtility plib = new ProperityFileUtility();
		ExcelFileUtility elib = new ExcelFileUtility();
		WebDriverUtility1 wlib = new WebDriverUtility1();

		// 4.Read the data from it with the help of keys
		String BROWSER = plib.toGetDataFromProperitiesFile("browser");
		String URL = plib.toGetDataFromProperitiesFile("url");
		String USERNAME = plib.toGetDataFromProperitiesFile("username");
		String PASSWORD = plib.toGetDataFromProperitiesFile("password");

		// reading data from excel

		String CAMPAIGN_NAME = elib.toReadTheDatafromExcel("Campaigns", 1, 1);

		String TARGET_SIZE = elib.toReadTheDatafromExcel("Campaigns", 1, 2);

		// launch the browser

		ChromeOptions settings = new ChromeOptions();// when ever notifications popups
//				EdgeOptions settings1 = new EdgeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs); // preferences- key - value
														// pair//org.openqa.selenium.NoSuchElementException
		// Launch the browser
//				WebDriver driver = new ChromeDriver(settings);

		WebDriver driver = null; // I don knw which browser to open so I gave WebDriver
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver(settings);
		} else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		// give conditions
		// launch the browser
		// WebDriver driver = new ChromeDriver();//constructor call

		// Maximize the window
		driver.manage().window().maximize();

		// implicit wait
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		wlib.waitforPageToLoad(driver);

		// Navigate to ninja CRM
		driver.get(URL);

		// Login into the ninja CRM
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Click on campaign link
		driver.findElement(By.linkText("Campaigns")).click();

		// Create campaign with Mandatory fields
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		WebElement targSize = driver.findElement(By.name("targetSize"));
		targSize.clear();
		targSize.sendKeys(TARGET_SIZE);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Verify the successful message
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		wlib.waitForVisibilityofElement(driver, toastMsg);

		String msg = toastMsg.getText();

		if (msg.contains(CAMPAIGN_NAME)) {
			System.out.println("Campaign created successfully");
		} else {
			System.out.println("Campaign is not created");
		}
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		wlib.ClickOnWebElement(driver, icon);
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		driver.quit();
	}

}// command + shift+ F
