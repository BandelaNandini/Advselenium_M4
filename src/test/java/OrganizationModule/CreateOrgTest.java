package OrganizationModule;

import java.io.IOException;

import org.openqa.selenium.By;
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
import POMUtilities.CreateOrgPage;
import POMUtilities.HomePage;
import POMUtilities.OrgInfoPage;
import POMUtilities.OrganizationPage;

@Listeners(ListenersUtility.ListenersImp.class)
public class CreateOrgTest extends Example {
	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createorgTest() throws InterruptedException, IOException {

		// Fetch the random number
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();

		// Fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("org", 1, 3) + rnum;
		UtilityClassObject.getTest().log(Status.INFO, "Fetched random num and data from excel");

		WebdriverUtility wutil = new WebdriverUtility();

		// Validate the home page using soft assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getHomeheader();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilityClassObject.getTest().log(Status.INFO, "validated home page using SA");

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

		// Identify save btn and click on it
		createOrgpp.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "entered orgname and saved");

		// validate orgname using soft assert
		OrgInfoPage orgInfopp = new OrgInfoPage(driver);
		String verifyOrgname = orgInfopp.getVerifyOrgname();

		Assert.assertEquals(verifyOrgname, orgname, "Validating orgname in createOrgTest");
		UtilityClassObject.getTest().log(Status.PASS, "validated orgname and saved");

		// Identify organization link and click on it
		homepp.getOrgTab();

		// Delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);

		// Handle confirmation popup
		wutil.switchToAlert_ClickOK(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Clicked on org tab and deleted orgname");

		// Close Excel
		exutil.closeExcelFile();
		soft.assertAll();
		UtilityClassObject.getTest().log(Status.INFO, "closed excel and handled soft assert");

	}
}
