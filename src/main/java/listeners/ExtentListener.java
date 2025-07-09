package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import webDriverBase.*;

public class ExtentListener implements ITestListener, ISuiteListener {
	 private static ExtentReports extent;
	 private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	 private static String reportDir;

	    @Override
	    public void onStart(ISuite suite) {
	    	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        reportDir = "test-output/reports/" + suite.getName() + "_" + timestamp;
	        extent = ReportManager.getInstance(reportDir + "/ExtentReport.html");
	    }

	    @Override
	    public void onFinish(ISuite suite) {
	        extent.flush();
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	  test.get().log(Status.FAIL, result.getThrowable());

	    	    Object currentClass = result.getInstance();
	    	    WebDriver driver = ((BaseDriver) currentClass).driver;

	    	    TakesScreenshot ts = (TakesScreenshot) driver;
	    	    File src = ts.getScreenshotAs(OutputType.FILE);

	    	    String suiteName = result.getTestContext().getSuite().getName();
	    	    String folderName = suiteName + "_" + ReportManager.timestamp;
	    	    String screenshotDir = "test-output/reports/" + folderName + "/screenshots/";
	    	    String screenshotPath = screenshotDir + result.getName() + ".png";

	    	    try {
	    	        Files.createDirectories(Paths.get(screenshotDir));
	    	        Files.copy(src.toPath(), Paths.get(screenshotPath));

	    	        test.get().addScreenCaptureFromPath("screenshots/" + result.getName()  + ".png");
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.get().log(Status.PASS, "Test passed");
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.get().log(Status.SKIP, "Test skipped");
	    }
}
