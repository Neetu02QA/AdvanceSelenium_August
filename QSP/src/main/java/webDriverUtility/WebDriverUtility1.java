package webDriverUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility1 {
	public void waitforPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	public void waitForVisibilityofElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		
		
		public void switchToFrame(WebDriver driver, int index) {
			driver.switchTo().frame(index);
			
		}
		
		public void switchToFrame1(WebDriver driver, String nameorid) {
			driver.switchTo().frame(nameorid);
			
		}
		
		public void switchToFrame(WebDriver driver, String Framelement) {
			driver.switchTo().frame(Framelement);
			
		}
		public void select(WebElement element, int index) {
			Select sel = new Select(element);
			sel.selectByIndex(index);
		}
		public void select(WebElement element, String value) {
			Select sel = new Select(element);
			sel.selectByValue(value);
			
		}
		public void select(String text, WebElement element) {
			Select sel = new Select(element);
			sel.selectByContainsVisibleText(text);
		}
		public void switchToalertAndAccept(WebDriver driver) {
			driver.switchTo().alert().accept();
			
		}
		public void switchToalertAnddismiss(WebDriver driver) {
			driver.switchTo().alert().dismiss();
			
		}
		public String switchToAlertAndGetText(WebDriver driver) {
			String text = driver.switchTo().alert().getText();
			return text;
		}
		
		public void switchToAlertAndSendKeys(WebDriver driver, String text) {
			driver.switchTo().alert().sendKeys(text);
		}
		
		public void mouseHoverOnWebelement(WebDriver driver, WebElement element) {
			Actions act = new Actions(driver);
			act.moveToElement(element).perform();
			
		}
		
		public void ClickOnWebElement(WebDriver driver,WebElement element) {
			Actions act = new Actions(driver);
			act.moveToElement(element).click().perform();
			
		}
		public void switchToWindow(WebDriver driver) {
			String parent = driver.getWindowHandle();
			Set<String> allwindowId = driver.getWindowHandles();
			allwindowId.remove(parent);
			for(String id:allwindowId) {
				driver.switchTo().window(id);
			}	
			}
		public void DoubleClickOnWebElement(WebDriver driver, WebElement element) {
			Actions act = new Actions(driver);
			act.doubleClick(element).perform();
		}
		
		public void RightClickOnWebElement(WebDriver driver, WebElement element) {
			Actions act = new Actions(driver);
			act.contextClick(element).perform();
		}
		public void passInput(WebDriver driver,WebElement element,String text) {
			Actions act = new Actions(driver);
			act.click(element).sendKeys(text).perform();
					
		}
		public void takesScreenshot(WebDriver driver, String filename) throws IOException{
			TakesScreenshot ts =(TakesScreenshot)driver;//screenshot is sn interface
			File temp =ts.getScreenshotAs(OutputType.FILE);//i can store scrrenenshots in file, base 64 etc
			File perm = new File("./errorshot/"+filename+".png");
			FileHandler.copy(temp, perm);
			
		}
		
		public void toScrollby(WebDriver driver, int x, int y) {
			JavascriptExecutor jse =(JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy("+x+","+y+")");
		}
		
}
//all import//double click also
