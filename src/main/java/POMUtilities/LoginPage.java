package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	// Declare
	@FindBy(linkText = "vtiger")
	private WebElement Loginheader;

	@FindBy(name = "user_name")
	private WebElement usernameTF;

	@FindBy(name = "user_password")
	private WebElement passwordTF;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	// Initialize

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getLoginheader() {
		return Loginheader.getText();
	}

	public void getUsernameTF(String username) {
		usernameTF.sendKeys(username);
	}

	public void getPasswordTF(String password) {
		passwordTF.sendKeys(password);
	}

	public void getLoginBtn() {
		loginBtn.click();
	}

	// Business LOGIC
	public void login(String username, String password) {
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginBtn.click();
	}

}
