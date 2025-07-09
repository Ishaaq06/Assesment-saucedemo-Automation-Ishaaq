package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStoreageUtils {
	private final JavascriptExecutor js;

    public LocalStoreageUtils(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public String getItem(String key) {
        return (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", key));
    }
 


    public void setItem(String key, String value) {
        js.executeScript(String.format("window.localStorage.setItem('%s','%s');", key, value));
    }

    public void clear() {
        js.executeScript("window.localStorage.clear();");
    }

    public boolean containsKey(String key) {
        return getItem(key) != null;
    }
}
