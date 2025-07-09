package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementActions;

public class LoginPage extends ElementActions {
	
	 private WebDriver driver;

	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement userNameField;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@id='login-button']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errors;
	
	 public LoginPage(WebDriver driver) {
		 
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	 public void login(String username, String password) {
		 clearAndType(userNameField,username);  
		 clearAndType(passwordField,password);  
		 click(loginButton);
	    }
	
	 public String getErrorMessage() {
		 return getText(errors);
	    }
	 public String verifyLoginPage() {
		 return getText(loginButton);
	 }
	
	
}
