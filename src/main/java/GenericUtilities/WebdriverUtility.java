package GenericUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author B.Nandini This class contains all the reusable methods from
 *         seleniumlibrary
 */
public class WebdriverUtility {
	WebDriver driver = UtilityClassObject.getDriver();

	/**
	 * This method is used navigate to an application
	 * 
	 * @param url
	 */
	public void navigateToAnAppln(String url, WebDriver driver) {
		driver.get(url);
	}

	public String fetchTheTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String fetchTheUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String fetchTheSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void minimizeTheWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public void windowFullScreen(WebDriver driver) {
		driver.manage().window().fullscreen();
	}

	public Dimension fetchWindowSize(WebDriver driver) {
		return driver.manage().window().getSize();
	}

	public void setWindowSize(WebDriver driver, int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public Point fetchWindowPosition(WebDriver driver) {
		return driver.manage().window().getPosition();
	}

	public void setWindowPosition(WebDriver driver, int x, int y) {
		driver.manage().window().setPosition(new Point(x, y));
	}

	public void navigateUsingStringUrl(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	public void navigateUsingURLurl(WebDriver driver, String url) throws MalformedURLException {
		driver.navigate().to(new URL(url));
	}

	public void navigateToNextWebpage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void navigateToPreviousWebpage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshTheWebpage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void closeTheBrowser(WebDriver driver) {
		driver.close();
	}

	public void quitTheBrowser(WebDriver driver) {
		driver.quit();
	}

	public String fetchCurrentWindowID(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public Set<String> fetchAllWindowIDS(WebDriver driver) {
		return driver.getWindowHandles();
	}

	public void switchToWindow(WebDriver driver, String id) {
		driver.switchTo().window(id);
	}

	public void switchToFrameUsingIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrameUsingIDNAME(WebDriver driver, String ID_NAME) {
		driver.switchTo().frame(ID_NAME);
	}

	public void switchToFrameUsingWebElement(WebDriver driver, WebElement frameele) {
		driver.switchTo().frame(frameele);
	}

	public void switchToAlert_ClickOK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlert_ClickCANCEL(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void switchToAlert_EnterText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public String switchToAlert_FetchtheText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitForAnElement(WebDriver driver, String timeouts) {
		long time = Long.parseLong(timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void waitUntilEleIsVisible(WebDriver driver, String timeouts, WebElement ele) {
		long time = Long.parseLong(timeouts);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitUntilEleIsClickable(WebDriver driver, String timeouts, WebElement ele) {
		long time = Long.parseLong(timeouts);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitUntilTitleIsVisible(WebDriver driver, String timeouts, String title) {
		long time = Long.parseLong(timeouts);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void handleDDUsing_SelectByValue(WebDriver driver, WebElement dropdown, String value) {
		Select s = new Select(dropdown);
		s.selectByValue(value);
	}

	public void handleDDUsing_SelectByIndex(WebDriver driver, WebElement dropdown, int index) {
		Select s = new Select(dropdown);
		s.selectByIndex(index);
	}

	public void mouseHoverOnAnEle(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}

	public void switchToChildWindow_Title(WebDriver driver, String exptitle) {
		Set<String> wids = driver.getWindowHandles();

		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(exptitle)) {
				break;
			}
		}
	}

	public void switchToChildWindow_URL(WebDriver driver, String expUrl) {
		Set<String> wids = driver.getWindowHandles();

		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getCurrentUrl().contains(expUrl)) {
				break;
			}
		}
	}

	public void switchToParentWindow(WebDriver driver, String pwid) {
		driver.switchTo().window(pwid);
	}
}
