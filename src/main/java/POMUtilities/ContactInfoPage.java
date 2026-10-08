package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	// Declare
	@FindBy(xpath = "//span[contains(text(),'Contact Information')]")
	private WebElement coninfoHeader;

	@FindBy(id = "dtlview_Last Name")
	private WebElement verifyLastname;

	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']/a")
	private WebElement verifyOrgname;

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement verifySuppStartDate;

	@FindBy(id = "dtlview_Support End Date")
	private WebElement verifySuppEnddate;

	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getConinfoHeader() {
		return coninfoHeader.getText();
	}

	public String getVerifyLastname() {
		return verifyLastname.getText();
	}

	public String getVerifyOrgname() {
		return verifyOrgname.getText();
	}

	public String getVerifySuppStartDate() {
		return verifySuppStartDate.getText();
	}

	public String getVerifySuppEnddate() {
		return verifySuppEnddate.getText();
	}

}
