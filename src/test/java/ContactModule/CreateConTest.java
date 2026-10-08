package ContactModule;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import BaseclassUtility.Example;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.UtilityClassObject;
import GenericUtilities.WebdriverUtility;
import POMUtilities.ContactInfoPage;
import POMUtilities.ContactsPage;
import POMUtilities.CreateContactPage;
import POMUtilities.HomePage;
@Listeners(ListenersUtility.ListenersImp.class)
public class CreateConTest extends Example {

	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createconTest() throws InterruptedException, IOException {
        System.out.println(driver);
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

		Assert.fail();
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
}