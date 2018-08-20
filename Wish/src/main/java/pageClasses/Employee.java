package pageClasses;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import utils.WebDriverUtils;

public class Employee extends WebDriverUtils{
	
	static String newappliation_link="linkText__New Individual Application";
	static String menu_list="xpath__//*[@id='menuFrontOffice']/div/div/ul[1]/li/a";
	
	
	static String firstname_textbox="id__txtFirstName";
	static String surname_textbox="id__txtSurname";
	static String maidenname_textbox="id__txtMaidenName";
	static String idnumber_textbox="id__txtIDNumber";
	static String dob_textbox="id__txtDOB";
	static String 	consentform_checkbox="id__chkConsentForm";
	static String accounts_drpdwn="id__cboAccounts";
	static String costcode_drpdwn="id__cboCostCodes";
	
	
	public static void fillForm(String[] args) throws Exception
	{
		try {
			driver.switchTo().defaultContent();
			switchToFrame("frmDisplay");
			getWebElement(firstname_textbox).sendKeys(args[0]);
			test.log(Status.PASS, "Entered firstname as :"+args[0]);
			getWebElement(surname_textbox).sendKeys(args[1]);
			test.log(Status.PASS, "Entered surname as :"+args[1]);
			getWebElement(maidenname_textbox).sendKeys(args[2]);
			test.log(Status.PASS, "Entered maiden name as :"+args[2]);
			getWebElement(idnumber_textbox).sendKeys(args[3]);
			test.log(Status.PASS, "Entered id number as :"+args[3]);
			getWebElement("id__txtPassportNumber").click();
			String dateofbirth=getWebElement(dob_textbox).getText();
			getWebElement(consentform_checkbox).click();
			test.log(Status.PASS, "Checked consent form checkbox");
			scrollIntoElementView(getWebElement(accounts_drpdwn));
			
			Select drp1=new Select(getWebElement(accounts_drpdwn));
			List<WebElement> drp1list=drp1.getOptions();
			int drp1index=0;
			for (WebElement webElement : drp1list) {
				
				if(webElement.getText().trim().equals(args[4]))
				{
					break;
				}
				drp1index=drp1index+1;
			}
			
			drp1.selectByIndex(drp1index);
			test.log(Status.PASS, "Accounts drop down value selected as :"+args[4]);
			Select drp2=new Select(getWebElement(costcode_drpdwn));
			
			drp2.selectByValue("18");
			test.log(Status.PASS, "cost code drop down value selected as :"+args[5]);
			
			
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, e.getMessage());
			throw e;
			
		}
	}
	
	public static void selectNewIndividualApplication() throws Exception
	{
		try {
			Thread.sleep(3000);
			List<WebElement> list=getWebElements(menu_list);
			for (WebElement webElement : list) {
				if(webElement.getText().trim().equals("Applications"))
					{
					webElement.click();
					break;
					}
			}
			
			getWebElement(newappliation_link).click();
			String[] header={"Request an Application"};
			//Index.validateHeader(header);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
}
