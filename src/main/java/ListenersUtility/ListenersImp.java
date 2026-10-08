package ListenersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import GenericUtilities.UtilityClassObject;

public class ListenersImp implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Configuring Report", true);
		String timestamp = new Date().toString().replace(":", "_").replace(" ", "_");

		// Configure report
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReports/VtigerReports" + timestamp + ".html");
		spark.config().setDocumentTitle("Vtiger CRM_Contact&OrgTest");
		spark.config().setReportName("CRM VTIGER");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Browser", "Chrome-154");
		report.setSystemInfo("OS Version", "Windows-11");

	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");
		test = report.createTest(testname + timestamp);
		UtilityClassObject.setTest(test);
		UtilityClassObject.getTest().log(Status.INFO, testname + timestamp + "Test Execution started");
		Reporter.log(testname + "Test Execution Started", true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");
		UtilityClassObject.getTest().log(Status.PASS, "Test Execution Success" + testname + timestamp);
		Reporter.log(testname + "Test Execution Success", true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");

		Reporter.log(testname + "Test Execution Failed - Screenshot", true);
		UtilityClassObject.getTest().log(Status.FAIL, testname + timestamp + "Test Execution Failed");

		TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getDriver();
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src, testname + timestamp + "Screenshot.png");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");

		UtilityClassObject.getTest().log(Status.SKIP, testname + timestamp + "Test Execution Skipped");
		Reporter.log(testname + "Test Execution Skipped", true);

	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report Backup", true);
		report.flush();
	}

}
