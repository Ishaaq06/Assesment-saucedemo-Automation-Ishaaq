package tests;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utils.*;
import webDriverBase.BrowserUtils;
public class LoginTest extends BrowserUtils {

	private static LoginPage loginPage;
	private static HomePage homePage;
	
	
	@BeforeMethod
	public void loadPages() {
		loginPage = new LoginPage(driver);
		homePage=new HomePage(driver);
		
		get("https://www.saucedemo.com/");
		
	}
	
	@DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},   // Positive
                {"locked_out_user", "secret_saucce", false},    // Invalid
                {"problem_user", "", false}, // empty pwd
                {"","",false} ,//empty
                {"performance_glitch_user", "secret_sauce", true}, // Positive 
                {"standard_user", "", true} //purposely failed this case
        };
    }
	
	@Test(dataProvider = "loginData")
	public void testLogin(String username, String password,boolean shouldLogin) {
		// Basic Task
		loginPage.login(username, password);
		waitForPageLoadComplete();
		 if (shouldLogin) {
	            Assert.assertTrue(getCurrentUrl().contains("inventory"), "Login should succeed.");
				
	            LocalStoreageUtils localStorage = new LocalStoreageUtils(driver); 
				  String backtraceguid = localStorage.getItem("backtrace-guid");
				  System.out.println("The current URL is : " + backtraceguid);
				  // Advanced Task -  Verify session-username after login. Cookie session-username should have correct value (used username)
	            Cookie session = getCookieName("session-username");
	            Assert.assertNotNull(session, "Session cookie not found");
	            Assert.assertEquals(session.getValue(), username, "Session username mismatch");
	            homePage.logout();
	        } else {
	            Assert.assertTrue(loginPage.getErrorMessage().length() > 0, "Error message should be shown.");
	        }
	}
	
	
	
}

