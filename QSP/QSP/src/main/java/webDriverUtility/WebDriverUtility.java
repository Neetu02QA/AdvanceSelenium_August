package webDriverUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverUtility {
	
	public void mouseHoverOnWebElement(WebDriver driver, WebElement we) {
	Actions act = new Actions(driver);
	act.moveToElement(we).perform();;
	
	}
}
