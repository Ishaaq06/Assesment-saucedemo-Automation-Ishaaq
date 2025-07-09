package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementActions;

public class HomePage extends ElementActions {

	private WebDriver driver;
	

	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
	private WebElement burgerMenu;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//span[@class='title']")
	private WebElement verifyHome;
	
	 public HomePage(WebDriver driver) {
		 this.driver = driver;
	     PageFactory.initElements(driver, this);
	    }
	
	
	 public void logout() {
		 click(burgerMenu);
		 click(logoutButton);
	    }
	
	 public String verifyHomePage() {
		 return getText(verifyHome);
	 }
}
