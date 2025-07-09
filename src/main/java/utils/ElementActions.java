package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webDriverBase.BaseDriver;

public class ElementActions extends BaseDriver {
	protected WebDriver driver;
	private WebElement element;

	public ElementActions() {
		
	}
	  public ElementActions(WebElement element) {
	        this.element = element;
	    }
	
	 public  void clearAndType(WebElement element, String text) {
		
		 	waitForVisible(element, 60);
		 	element.clear();
			element.sendKeys(text);
	    }
	 
	 public  void click(WebElement element) {
			
		 	waitForVisible(element, 60);
		 	element.click();
			
	    }

	    public String getText(WebElement element) {
	    	waitForVisible(element, 60);
	        return element.getText();
	    }
	    
	    // Advacnced Task - Override the selenium getValue() or getText() method.
	    public String getText() {
	    	String text = element.getText().trim();
	    	return text;
	    }
	    public String getValue() {
	    	String value = element.getAttribute("value");
	    	return value;
	    }
	    
	    
	    
	    public boolean isElementPresent(WebElement element) {
			try {
				element.getAttribute("innerHTML");
			} catch (Exception ex) {
				return false;
			}
			return true;
		}
	    
	    public void waitForVisible(WebElement element, Integer timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
	    

}
