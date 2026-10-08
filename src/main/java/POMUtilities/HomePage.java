package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

	// Declare

	@FindBy(partialLinkText = "Home")
	private WebElement homeheader;

	@FindBy(linkText = "Organizations")
	private WebElement orgTab;

	@FindBy(linkText = "Contacts")
	private WebElement contTab;

	@FindBy(xpath = "//img[contains(@src,'user')]")
	private WebElement adminIcon;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;

	// Initialize
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getHomeheader() {
		return homeheader.getText();
	}

	public void getOrgTab() {
		orgTab.click();
	}

	public void getContTab() {
		contTab.click();
	}

	public WebElement getAdminIcon() {
		return adminIcon;
	}

	public void getSignoutLink() {
		signoutLink.click();
	}

	// Businesslogic
	public void logout(WebDriver driver) {
		Actions act = new Actions(driver);
		act.moveToElement(adminIcon).perform();
		signoutLink.click();
	}

}
