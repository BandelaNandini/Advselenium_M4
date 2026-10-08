package BaseclassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.DatabaseUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.UtilityClassObject;
import GenericUtilities.WebdriverUtility;
import POMUtilities.HomePage;
import POMUtilities.LoginPage;

public class Baseclass {
	public DatabaseUtility dutil = new DatabaseUtility();
	public WebdriverUtility wutil = new WebdriverUtility();
	public PropertyFileUtility putil = new PropertyFileUtility();

	@BeforeSuite(alwaysRun = true)
	public void connectToDB() throws SQLException {
		dutil.getDatabaseConnection();
		Reporter.log("Connected To DB", true);
	}

	@BeforeTest(alwaysRun = true)
	public void configParallelExe() {
		Reporter.log("Configuration of Parallel Execution", true);
	}

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void launchTheBrowser(String browser) throws IOException {
		WebDriver driver;
//		String browser = putil.fetchDataFromPropFile("browser");
		if (browser.equals("chrome"))
			driver = new ChromeDriver();
		else if (browser.equals("edge"))
			driver = new EdgeDriver();
		else if (browser.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();

		UtilityClassObject.setDriver(driver);
		Reporter.log("Launched browser", true);

	}

	@BeforeMethod(alwaysRun = true)
	public void login() throws IOException {
		WebDriver driver = UtilityClassObject.getDriver();

		String url = putil.fetchDataFromPropFile("url");
		String timeouts = putil.fetchDataFromPropFile("timeouts");
		String username = putil.fetchDataFromPropFile("username");
		String password = putil.fetchDataFromPropFile("password");

		// Maximize the window
		wutil.maximizeTheWindow(driver);

		// Implicit wait
		wutil.waitForAnElement(driver, timeouts);

		// Navigate to an appln
		wutil.navigateToAnAppln(url, driver);

		// Login
		LoginPage loginpp = new LoginPage(driver);
		loginpp.login(username, password);

		Reporter.log("Logged in to VTiger", true);

	}

	@AfterMethod(alwaysRun = true)
	public void logout() {
		WebDriver driver = UtilityClassObject.getDriver();

		HomePage homepp = new HomePage(driver);
		homepp.logout(driver);
		Reporter.log("Logged out of Vtiger", true);

	}

	@AfterClass(alwaysRun = true)
	public void quitTheBrowser() {
		WebDriver driver = UtilityClassObject.getDriver();

		wutil.quitTheBrowser(driver);
		Reporter.log("Closed Browser", true);

	}

	@AfterTest(alwaysRun = true)
	public void CloseParallelExe() {

		Reporter.log("close configuration of Parallel Execution", true);
	}

	@AfterSuite(alwaysRun = true)
	public void DisConnectToDB() throws SQLException {

		dutil.closeDatabaseConnection();
		Reporter.log("Disconnected with DB", true);
	}
}
