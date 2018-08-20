package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class InstanceFactory extends WebDriverUtils{
	
	public static ExtentReports getExtentReportInstance() throws Exception
	{
		try {
			
			if(extent ==null)
			{
			ExtentHtmlReporter htmlReport=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/extent-output/ExtentReport.html"));
			htmlReport.config().setChartVisibilityOnOpen(true);
			htmlReport.config().setDocumentTitle("Automation Report");
			htmlReport.config().setReportName("Praticse automation");		
			extent=new ExtentReports();
			extent.attachReporter(htmlReport);
			
			}
			
			return extent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public static Properties getPropertyReaderInstance() throws Exception
	{
		if(prop!=null)
		{
			prop.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties"));
		}
		else
		{
			prop=new Properties();
			prop.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties"));
		}
		return prop;
	}

	
}
