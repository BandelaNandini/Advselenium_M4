package OrgModuleScenarios;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.UtilityClassObject;
import GenericUtilities.WebdriverUtility;
import POMUtilities.CreateOrgPage;
import POMUtilities.HomePage;
import POMUtilities.OrgInfoPage;
import POMUtilities.OrganizationPage;

public class OrganizationTest extends Baseclass {

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

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createorgIndType_Test() throws InterruptedException, IOException {
		WebDriver driver = UtilityClassObject.getDriver();

		// Fetch the random number
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();

		// Fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("org", 7, 3) + rnum;
		String industry = exutil.fetchDataFromExcel("org", 7, 4);
		String type = exutil.fetchDataFromExcel("org", 7, 5);
		UtilityClassObject.getTest().log(Status.INFO, "Fetched random num and data from excel");

		WebdriverUtility wutil = new WebdriverUtility();

		// Validate Home Page using soft assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getHomeheader();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilityClassObject.getTest().log(Status.INFO, "validated home using SA");

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

		// Identify industry DD and select education
		WebElement industryDD = createOrgpp.getIndustryDD();
		wutil.handleDDUsing_SelectByValue(driver, industryDD, industry);

		// Identify type DD and select competitor
		WebElement typeDD = createOrgpp.getTypeDD();
		wutil.handleDDUsing_SelectByValue(driver, typeDD, type);

		// Identify save btn and click on it
		createOrgpp.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "entered orgname, selected indutry & type");

		// validate orgname using hard assert
		OrgInfoPage orgInfopp = new OrgInfoPage(driver);
		String verifyOrgname = orgInfopp.getVerifyOrgname();

		Assert.assertEquals(verifyOrgname, orgname, "Validating orgname in createorg With industry and type");

		// Identify and validate industry using hard assert
		String verifyInd = orgInfopp.getVerifyIndustry();
		Assert.assertEquals(verifyInd, industry, "Validating industry in createorg With industry and type");

		// Identify and validate type using hard assert
		String verifyType = orgInfopp.getVerifyType();
		Assert.assertEquals(verifyType, type, "Validating type in createorg With industry and type");
		UtilityClassObject.getTest().log(Status.PASS, "Verified orgname,industry and type");

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
		UtilityClassObject.getTest().log(Status.INFO, "closed excel and handled SA");

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrg_PhnoTest() throws InterruptedException, IOException {
		WebDriver driver = UtilityClassObject.getDriver();

		// Fetch the random number
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();

		// Fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("org", 4, 3) + rnum;
		String phno = exutil.fetchDataFromExcel("org", 4, 4);
		UtilityClassObject.getTest().log(Status.INFO, "Fetched random num and data from excel");

		WebdriverUtility wutil = new WebdriverUtility();

		// Validate the home page using soft assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getHomeheader();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilityClassObject.getTest().log(Status.INFO, "validated home using SA");

		// Identify organization link and click on it
		homepp.getOrgTab();

		// Identify org plus icon and click on it
		OrganizationPage orgpp = new OrganizationPage(driver);
		orgpp.getOrgPlusIcon();

		// Identify orgname TF and pass org name in it
		CreateOrgPage createOrgpp = new CreateOrgPage(driver);
		createOrgpp.getOrgnameTF(orgname);

		// Identify phno tf and enter phno in it
		createOrgpp.getPhnoTF(phno);

		// Identify save btn and click on it
		createOrgpp.getSaveBtn();
		UtilityClassObject.getTest().log(Status.INFO, "created org with phno and clicked on save");

		// Identify org info header and validate orgname
		OrgInfoPage orgInfopp = new OrgInfoPage(driver);
		String verifyOrgname = orgInfopp.getVerifyOrgname();
		Assert.assertEquals(verifyOrgname, orgname, "Validating orgname in createorg With phno");

		// Identify and validate phno
		String verifyphno = orgInfopp.getVerifyPhno();

		Assert.assertEquals(verifyphno, phno, "Validating phno in createorg With phnoa");
		UtilityClassObject.getTest().log(Status.PASS, "verified org with phno");

		// Identify organization link and click on it
		homepp.getOrgTab();

		// Delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);

		// Handle confirmation popup
		wutil.switchToAlert_ClickOK(driver);
		UtilityClassObject.getTest().log(Status.INFO, "clicked on org tab and deleted orgname");

		// Close Excel
		exutil.closeExcelFile();
		soft.assertAll();
		UtilityClassObject.getTest().log(Status.INFO, "Closed excel and handled SA");

	}

}
