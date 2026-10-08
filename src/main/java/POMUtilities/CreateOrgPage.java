package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPage {

	// Declare
	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement createOrgHeader;

	@FindBy(name = "accountname")
	private WebElement orgnameTF;

	@FindBy(id = "phone")
	private WebElement phnoTF;

	@FindBy(name = "industry")
	private WebElement industryDD;

	@FindBy(name = "accounttype")
	private WebElement typeDD;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialize
	public CreateOrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getCreateOrgHeader() {
		return createOrgHeader.getText();
	}

	public void getOrgnameTF(String orgname) {
		orgnameTF.sendKeys(orgname);
	}

	public void getPhnoTF(String phno) {
		phnoTF.sendKeys(phno);
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}

	public void getSaveBtn() {
		saveBtn.click();
	}

	// Utilize

}
