package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {

	// Declare
	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement createContHeader;

	@FindBy(name = "lastname")
	private WebElement lastnameTF;

	@FindBy(xpath = "//img[contains(@onclick,'module=Accounts&action')]")
	private WebElement orgPlusIcon;

	@FindBy(id = "search_txt")
	private WebElement orgSearchTF;

	@FindBy(name = "search")
	private WebElement orgSearchBtn;

	@FindBy(name = "support_start_date")
	private WebElement suppStartdateTF;

	@FindBy(name = "support_end_date")
	private WebElement suppEnddateTF;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialize

	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public WebElement getCreateContHeader() {
		return createContHeader;
	}
	
	public void getLastnameTF(String lastname) {
		lastnameTF.sendKeys(lastname);
	}

	public void getOrgPlusIcon() {
		orgPlusIcon.click();
	}

	public void getOrgSearchTF(String orgname) {
		orgSearchTF.sendKeys(orgname);
	}

	public void getOrgSearchBtn() {
		orgSearchBtn.click();
	}

	public void getSuppStartdateTF(String startdate) {
		suppStartdateTF.clear();
		suppStartdateTF.sendKeys(startdate);
	}

	public void getSuppEnddateTF(String enddate) {
		suppEnddateTF.clear();
		suppEnddateTF.sendKeys(enddate);
	}

	public void getSaveBtn() {
		saveBtn.click();
	}

}
