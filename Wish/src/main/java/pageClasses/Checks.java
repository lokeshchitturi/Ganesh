package pageClasses;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import utils.WebDriverUtils;

public class Checks  extends WebDriverUtils{
	
	
	
	static String checks_list="xpath__//tr[starts-with(@id,'trCheckSelector')]";
	static String submit_button="id__Button1";
	
	static String enquiryreason_ITC="xpath__//td[@id='tdAdditionalData_ITC']//select";
	static String termsofuse_ITC="id__chkITCTermsOfUse";
	
	public static void submitApplicationForm() throws Exception
	{
		try {
			getWebElement(submit_button).click();
			test.log(Status.PASS, "Submited Application Form");
			waitUntilPresenceOfElement(30, By.xpath("//td[text()='Latest Applications']"));
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to Submit Application Form");
			throw e;
		}
	}
	
	public static void input_Check_ITC() throws Exception
	{
		try {
			WebElement drpdwn=getWebElement(enquiryreason_ITC);
			Select select=new Select(drpdwn);
			select.selectByIndex(3);
			
			test.log(Status.PASS, "Selected enquiry drpdwn of ITC :");
			
			getWebElement(termsofuse_ITC).click();
			test.log(Status.PASS, "Checked terms of use checkbox of ITC");
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to fill ITC check form :"+e.getMessage());
			throw e;
		}
	}
	
	public static void selectCheck(String[] checkName) throws Exception
	{
		try {
			List<WebElement> list=getWebElements(checks_list);
			boolean flag=false;
			for (WebElement webElement : list) {
				List<WebElement> childs=webElement.findElements(By.tagName("td"));
				System.out.println(childs.size());
				
				
				if(childs.get(0).getText().equals(checkName[0]))
				{
					scrollIntoElementView(childs.get(1));
					childs.get(1).findElement(By.tagName("input")).click();
					Thread.sleep(3000);
					Alert alert=getAlert();
					if(alert!=null)
					{
						alert.accept();
					}
					test.log(Status.PASS, "Clicked on check with name :"+checkName[0]);
					test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
					flag=true;
					break;
				}
			}
			
			if(!flag)
			{
				throw new Exception("unable to find the check with name :"+checkName[0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "unable to click on check with name :"+checkName[0]);
			throw e;
		}
	}
	
	

}
