package ContactModuleScenarios;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.UtilityClassObject;
import GenericUtilities.WebdriverUtility;
import POMUtilities.ContactInfoPage;
import POMUtilities.ContactsPage;
import POMUtilities.CreateContactPage;
import POMUtilities.CreateOrgPage;
import POMUtilities.HomePage;
import POMUtilities.OrgInfoPage;
import POMUtilities.OrganizationPage;

public class ContactTest extends Baseclass {

	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)

	public void createconTest() throws InterruptedException, IOException {
		WebDriver driver = UtilityClassObject.getDriver();

		// Fetch the random number
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilityClassObject.getTest().log(Status.INFO, "Fetched random number");

		// Fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String lastname = exutil.fetchDataFromExcel("contact", 1, 3) + rnum;
		UtilityClassObject.getTest().log(Status.INFO, "Fetched data from excel file");

		WebdriverUtility wutil = new WebdriverUtility();

		// Validate the home page using soft assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getHomeheader();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilityClassObject.getTest().log(Status.INFO, "Validated home page using soft assert");

		// Identify contact tab and click on it
		homepp.getContTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// Identify contact plus icon and click on it
		ContactsPage contactpp = new ContactsPage(driver);
		contactpp.getConPlusIcon();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact plusicon");

		// waiting for create new contact header element
		CreateContactPage createConpp = new CreateContactPage(driver);
		WebElement cncHeader = createConpp.getCreateContHeader();

		String timeouts = putil.fetchDataFromPropFile("timeouts");
		wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);
		UtilityClassObject.getTest().log(Status.PASS, "Waiting for create new contact using Explicit wait");

		// Identify lastname TF and enter lastname in it
		createConpp.getLastnameTF(lastname);
		UtilityClassObject.getTest().log(Status.INFO, "Entered lastname");

		// Identify save btn and click on it
		createConpp.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "saved contact");

		// Validate lastname using Hard Assert
		ContactInfoPage conInfopp = new ContactInfoPage(driver);
		String verifyLastname = conInfopp.getVerifyLastname();

		Assert.assertEquals(verifyLastname, lastname, "Validating lastname in createConTest");
		UtilityClassObject.getTest().log(Status.PASS, "Validated Lastname using Hard Assert");

		// Identify contact tab and click on it
		homepp.getContTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// Delete the created contact
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(2000);

		// Handle Confirmation popup and click on ok
		wutil.switchToAlert_ClickOK(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Deleted contact");

		// Close Excel
		exutil.closeExcelFile();
		soft.assertAll();
		UtilityClassObject.getTest().log(Status.INFO, "Closed Excel File and handled soft assert");

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createcon_orgTest() throws InterruptedException, IOException {
		WebDriver driver = UtilityClassObject.getDriver();

		// Fetch the random number
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilityClassObject.getTest().log(Status.INFO, "Fetched random number");

		// Fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String lastname = exutil.fetchDataFromExcel("contact", 4, 3) + rnum;
		String orgname = exutil.fetchDataFromExcel("contact", 4, 4) + rnum;
		UtilityClassObject.getTest().log(Status.INFO, "Fetched data from excel file");

		WebdriverUtility wutil = new WebdriverUtility();

		// Validate the home page Using soft assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getHomeheader();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilityClassObject.getTest().log(Status.INFO, "Validated home page using soft assert");

		// Identify organization link and click on it
		homepp.getOrgTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on org tab");

		// Identify org plus icon and click on it
		OrganizationPage orgpp = new OrganizationPage(driver);
		orgpp.getOrgPlusIcon();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on org plus icon");

		// Identify orgname TF and pass org name in it
		CreateOrgPage createOrgpp = new CreateOrgPage(driver);
		createOrgpp.getOrgnameTF(orgname);
		UtilityClassObject.getTest().log(Status.INFO, "Entered orgname");

		// Identify save btn and click on it
		createOrgpp.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on save");

		// validate orgname using Hard assert
		OrgInfoPage orgInfopp = new OrgInfoPage(driver);
		String verifyOrgname = orgInfopp.getVerifyOrgname();

		Assert.assertEquals(verifyOrgname, orgname, "Validating orgname in createconwithOrg");
		UtilityClassObject.getTest().log(Status.PASS, "verified orgname");

		// Identify contact tab and click on it
		homepp.getContTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// Identify contact plus icon and click on it
		ContactsPage contactpp = new ContactsPage(driver);
		contactpp.getConPlusIcon();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact plus icon");

		// waiting for create new contact header element
		CreateContactPage createConpp = new CreateContactPage(driver);
		WebElement cncHeader = createConpp.getCreateContHeader();

		String timeouts = putil.fetchDataFromPropFile("timeouts");
		wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);
		UtilityClassObject.getTest().log(Status.INFO, "waiting for contact header using exp wait");

		// Identify lastname TF and enter lastname in it
		createConpp.getLastnameTF(lastname);
		UtilityClassObject.getTest().log(Status.INFO, "Given lastname");

		// Identify org plus icon and click on it
		createConpp.getOrgPlusIcon();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on org plus icon");

		// Fetch parent window id
		String pwid = wutil.fetchCurrentWindowID(driver);
		UtilityClassObject.getTest().log(Status.INFO, "fetch parent window id");

		// switch to the child window
		wutil.switchToChildWindow_URL(driver, "module=Accounts&action");
		UtilityClassObject.getTest().log(Status.INFO, "switch to child window");

		// Identify search TF and enter orgname
		createConpp.getOrgSearchTF(orgname);
		UtilityClassObject.getTest().log(Status.INFO, "entered orgname");

		// Identify search btn and click on it
		createConpp.getOrgSearchBtn();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on searchbtn");

		// Identify orgname and click on it
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		UtilityClassObject.getTest().log(Status.INFO, "selected orgname");

		// Switch back to parent window
		wutil.switchToParentWindow(driver, pwid);
		UtilityClassObject.getTest().log(Status.INFO, "switched back to parent window");

		// Identify save btn and click on it
		createConpp.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on save");

		// Validate lastname using hard assert
		ContactInfoPage conInfopp = new ContactInfoPage(driver);
		String verifylastname = conInfopp.getVerifyLastname();

		Assert.assertEquals(verifylastname, lastname, "Validating lastname in createConWithOrgTest");
		UtilityClassObject.getTest().log(Status.PASS, "verified lastname");

		// Validate orgname in contact info page using hard assert
		String verifyorgname_con = conInfopp.getVerifyOrgname();
		Assert.assertEquals(verifyorgname_con, orgname, "Validating orgname in con info page");
		UtilityClassObject.getTest().log(Status.PASS, "verified orgname");

		// Identify contact tab and click on it
		homepp.getContTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// Delete the created contact
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(2000);

		// Handle Confirmation popup and click on ok
		wutil.switchToAlert_ClickOK(driver);
		UtilityClassObject.getTest().log(Status.INFO, "deleted contact");

		// Identify organization link and click on it
		homepp.getOrgTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on org tab");

		// Delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);

		// Handle confirmation popup
		wutil.switchToAlert_ClickOK(driver);
		UtilityClassObject.getTest().log(Status.INFO, "deleted org");

		// Close Excel
		exutil.closeExcelFile();
		soft.assertAll();
		UtilityClassObject.getTest().log(Status.INFO, "closed excel and soft assert");

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createcon_SuppDateTest() throws InterruptedException, IOException {
		WebDriver driver = UtilityClassObject.getDriver();

		// Fetch the random number
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilityClassObject.getTest().log(Status.INFO, "Fetched random number");

		// Fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String lastname = exutil.fetchDataFromExcel("contact", 7, 3) + rnum;
		UtilityClassObject.getTest().log(Status.INFO, "Fetched data from excel file");

		WebdriverUtility wutil = new WebdriverUtility();

		// Validate the home page using soft assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getHomeheader();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilityClassObject.getTest().log(Status.INFO, "Validated home page using soft assert");

		// Identify contact tab and click on it
		homepp.getContTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// Identify contact plus icon and click on it
		ContactsPage contactpp = new ContactsPage(driver);
		contactpp.getConPlusIcon();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact plus icon");

		// waiting for create new contact header element
		CreateContactPage createConpp = new CreateContactPage(driver);
		WebElement cncHeader = createConpp.getCreateContHeader();

		String timeouts = putil.fetchDataFromPropFile("timeouts");
		wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);
		UtilityClassObject.getTest().log(Status.INFO, "waiting for contact header");

		// Identify lastname TF and enter lastname in it
		createConpp.getLastnameTF(lastname);

		// Get start/current date
		String startdate = jutil.fetchCurrentDate();
		Reporter.log(startdate, true);

		// Get End date after 30 days
		String enddate = jutil.fetchDateAfterGivenNoOfDays(30);
		Reporter.log(enddate, true);

		// Identify supp start date TF and pass current date
		createConpp.getSuppStartdateTF(startdate);

		// Identify supp end date TF and pass date after 30 days
		createConpp.getSuppEnddateTF(enddate);

		// Identify save btn and click on it
		createConpp.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "Given lastname, support start date and enddate and saved");

		// Validate lastname using hard assert
		ContactInfoPage conInfopp = new ContactInfoPage(driver);
		String verifyLastname = conInfopp.getVerifyLastname();

		Assert.assertEquals(verifyLastname, lastname, "Validating lastname in createContactwith supp date");

		// Validate suppStartdate using hard assert
		String verifyStartdate = conInfopp.getVerifySuppStartDate();

		Assert.assertEquals(verifyStartdate, startdate, "Validating startdate in createconwith supportdate");

		// Validate suppEnddate
		String verifyEnddate = conInfopp.getVerifySuppEnddate();

		Assert.assertEquals(verifyEnddate, enddate, "Validating enddate in createconwith supportdate");
		UtilityClassObject.getTest().log(Status.PASS, "verified lastname, start date and enddate");

		// Identify contact tab and click on it
		homepp.getContTab();
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// Delete the created contact
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(2000);

		// Handle Confirmation popup and click on ok
		wutil.switchToAlert_ClickOK(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Deleted contact");

		// Close Excel
		exutil.closeExcelFile();
		soft.assertAll();
		UtilityClassObject.getTest().log(Status.INFO, "Closed excel and soft assert");

	}

}
