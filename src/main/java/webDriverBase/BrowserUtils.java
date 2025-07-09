package webDriverBase;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserUtils extends BaseDriver {

	protected static Logger logger = LoggerFactory.getLogger(BaseDriver.class.getName());
	JavascriptExecutor javaScriptExecutor;
	public static WebDriver initDriver(String browserType) {
        
		 if (browserType.equalsIgnoreCase("chrome")) {
	            WebDriverManager.chromedriver().setup();
	            ChromeOptions options = new ChromeOptions();

	            Map<String, Object> prefs = new HashMap<>();
	            prefs.put("credentials_enable_service", false);
	            prefs.put("profile.password_manager_enabled", false);
	            prefs.put("profile.default_content_setting_values.notifications", 2);
	            prefs.put("autofill.profile_enabled", false);

	            options.setExperimentalOption("prefs", prefs);
	            options.addArguments("--incognito");
	            options.addArguments("--disable-save-password-bubble");
	            options.addArguments("--disable-notifications");
	            options.addArguments("--disable-extensions");
	            options.addArguments("--start-maximized");
	            options.addArguments("--disable-popup-blocking");
	            options.addArguments("--no-default-browser-check");
	            options.addArguments("--disable-autofill-keyboard-accessory-view[8]");
	            return new ChromeDriver(options);
	            
		 }
		 else if(browserType.equalsIgnoreCase("firefox")) {
			 WebDriverManager.firefoxdriver().setup();

		        FirefoxOptions options = new FirefoxOptions();

		        Map<String, Object> prefs = new HashMap<>();
		        prefs.put("signon.rememberSignons", false);  
		        prefs.put("permissions.default.desktop-notification", 2); 
		        prefs.put("browser.privatebrowsing.autostart", true); 
		        prefs.put("extensions.enabledScopes", 0); 
		        prefs.put("dom.popup_maximum", 0); 
		        prefs.put("browser.shell.checkDefaultBrowser", false); 
		        prefs.put("signon.autofillForms", false);

		        
		        for (Map.Entry<String, Object> entry : prefs.entrySet()) {
		            options.addPreference(entry.getKey(), entry.getValue());
		        }

		        options.addArguments("--width=1920");
		        options.addArguments("--height=1080");

		        return new FirefoxDriver(options);
			 
		 }
	        throw new IllegalArgumentException("Browser not supported: " + browserType);
	    }
        


    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

	public void waitForPageLoadComplete() {
		waitForPageLoad(30);
		return;
	}
    public void waitForPageLoad(int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class, WebDriverException.class);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				String result = (String) getJavaScriptExecutor().executeScript("return document.readyState");
				if (result == null)
					return false;
				else
					return result.equals("complete");
			}
		});
		return;
	}
    
	public JavascriptExecutor getJavaScriptExecutor() {
		if (javaScriptExecutor == null)
			javaScriptExecutor = (JavascriptExecutor) driver;
		return javaScriptExecutor;
	}
    public void get(String url) {
		BrowserUtils.driver.get(url);
	}
	public String getCurrentUrl() {
		logger.info("The current URL is : " + driver.getCurrentUrl());
		
		return BrowserUtils.driver.getCurrentUrl();
	}
	
	public Cookie getCookieName(String cookieName) {
		Cookie session = driver.manage().getCookieNamed(cookieName);
		return session;
	}
}
