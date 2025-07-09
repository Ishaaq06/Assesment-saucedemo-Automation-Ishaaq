package webDriverBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(listeners.ExtentListener.class)
public class BaseDriver {
	public static WebDriver driver;	
	
	
	 @BeforeClass
	 @Parameters("browsername")
	 
	    public void setupBrowser(String browsername) {
		 PageFactory.initElements(driver, this);
		 if(browsername.equalsIgnoreCase("chrome")) {
			 driver = BrowserUtils.initDriver("chrome");
			 if (driver == null) {
				    throw new RuntimeException("WebDriver initialization failed!");
				}
			 System.out.println(">>> Driver instance: " + driver);
			 driver.manage().deleteAllCookies();
			
		 }
			 
		 else if(browsername.equalsIgnoreCase("firefox"))
			 driver = BrowserUtils.initDriver("firefox");
	    }

	    @AfterClass
	    public void teardownBrowser() {
	    	 if (driver != null) {
	             driver.quit();
	        
	   
	    	 }
	    	 }
}
