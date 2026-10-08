package ContactModule;

import java.io.IOException;

import org.openqa.selenium.By;
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
import POMUtilities.HomePage;

public class CreateConWithSupportDateTest extends Baseclass {

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createcon_SuppDateTest() throws InterruptedException, IOException {

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
