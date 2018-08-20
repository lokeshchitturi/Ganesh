package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import utils.WebDriverUtils;

public class Login extends WebDriverUtils{
	
	
	static String username_textbox="id__txtUserName";
	static String passowrd_textbox="id__txtPassword";
	static String signin_button="id__btnLogin";
	static String signout_link="partialLinkText__Sign Out";
	
	static String watermark_textbox="id__txtWatermark";
	
	public static String watermarkId=null; 
	
	public static void verifyApplicaantWithWatermark() throws Exception
	{
		try {
			if(watermarkId!=null)
			{
				driver.switchTo().defaultContent();
				getWebElement(watermark_textbox).sendKeys(watermarkId);
				getWebElement(watermark_textbox).sendKeys(Keys.ENTER);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw e;
		}
	}
	
	public static void loginIntoApplication() throws Exception
	{
		try {
	
			enterCredntials();
			clickSignIn();
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
			validateLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
	}
	
	public static void logoutFromApplication() throws Exception
	{
		try {
			getWebElement(signout_link).click();
			test.log(Status.PASS, "Clicked on Sign Out link");
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
			waitUntilPageLoads(30);
			getWebElement(username_textbox);
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "User unable to logout of application");
			throw e;
		}
	}
	
	public static void navigateToURL() throws Exception
	{
		try {
			driver.get(prop.getProperty("url"));
			test.log(Status.PASS, "Navigated to URL :"+prop.getProperty("url"));
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable tp navigate to URL :"+prop.getProperty("url"));
			throw e;
		}
	}
	
	
	public static void enterCredntials() throws Exception
	{
		try {
			getWebElement(username_textbox).sendKeys(prop.getProperty("username"));
			getWebElement(passowrd_textbox).sendKeys(prop.getProperty("password"));
			test.log(Status.PASS, "Entered username and password");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to enter either username/password");
			throw e;
		}	
	}
	
	public static void clickSignIn() throws Exception
	{
		try {
			getWebElement(signin_button).click();
			test.log(Status.PASS, "Clicked on signin button");
			waitUntilPageLoads(30);
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to click on signin button");
			throw e;
		}
	}
	
	public static void validateLogin() throws Exception
	{
		try {
			driver.findElement(By.partialLinkText("Sign Out"));
			test.log(Status.PASS, "User logged into application");
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "User unable to login into application");
			throw e;
		}
	}
	
	
	
}
