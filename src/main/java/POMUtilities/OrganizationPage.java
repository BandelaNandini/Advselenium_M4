package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	// Declare
	@FindBy(linkText = "Organizations")
	private WebElement orgHeader;

	@FindBy(xpath = "//img[@alt=\"Create Organization...\"]")
	private WebElement orgPlusIcon;

	// Initialize
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getOrgHeader() {
		return orgHeader.getText();
	}

	public void getOrgPlusIcon() {
		 orgPlusIcon.click();
	}

}
