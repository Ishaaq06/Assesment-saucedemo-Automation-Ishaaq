package listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
	 private static ExtentReports extent;
	 public static String timestamp;


	    public static ExtentReports getInstance(String suiteName) {
	    	if (extent == null) {
	    		timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    		String folderName = suiteName + "_" + timestamp;
	    		String reportPath =  folderName + "/ExtentReport.html";
	    		
	    		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	    		
	           
	            extent = new ExtentReports();
	            extent.setSystemInfo("Test Suite", suiteName);
	            extent.attachReporter(reporter);
	        }
	        return extent;
	    }

}
