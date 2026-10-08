package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	// Declare
	@FindBy(linkText = "Contacts")
	private WebElement contactHeader;

	@FindBy(xpath = "//img[@alt=\"Create Contact...\"]")
	private WebElement conPlusIcon;

	// Initialize

	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getContactHeader() {
		return contactHeader.getText();
	}

	public void getConPlusIcon() {
		conPlusIcon.click();
	}

}
