package pageClasses;

import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import utils.JSONUtils;
import utils.WebDriverUtils;

public class Register extends WebDriverUtils{

	static String registernow_link="id__lnkSignUp";
	static String termsandconditions_checkbox="id__chkTermsAndConditions";
	static String welcomenext_button="id__step0Next";
	static String registrationnext_button="id__step1Next";
	static String capturenext_button="id__step2Next";
	
	static String selectindustry_dropdown="id__cboIndustry";
	static String finish_button="id__step3Finish";
	
	public static void clickFinish() throws Exception
	{
		try {
			WebElement ele=getWebElement(finish_button);
			scrollIntoElementView(ele);
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
			ele.click();
			test.log(Status.PASS, "Clicked on finish button");
			waitUntilVisible(30, By.xpath("//*[@id='textTable']/tbody/tr/td"));
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to click on finish button");
			throw e;
		}
	}
	
	
	public static void clickCaptureNext() throws Exception
	{
		try {
			WebElement ele=getWebElement(capturenext_button);
			scrollIntoElementView(ele);
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
			ele.click();
			test.log(Status.PASS, "clicked on capture next button");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "unable to click on capture next button");
			throw e;
		}
	}
	
	public static void selectRadioButtons(String[] args) throws Exception
	{
		String radioTestDataName=args[0];
		try {
			JSONArray testdata=JSONUtils.getJsonArray(radioTestDataName);
			//testdata
			
			for(int i=0;i<testdata.size();i++)
			{
				JSONObject jsonObj=(JSONObject) testdata.get(i);
				String locator=null;
				String input=null;
				Set<String> set=jsonObj.keySet();
				
				for (String string : set) {
					locator=string;
					input=(String) jsonObj.get(string);
				}
				
				WebElement ele=getWebElement("id__"+locator);
				List<WebElement> list=ele.findElements(By.tagName("label"));
				int index=0;
				for (WebElement webElement : list) {
					if(webElement.getText().equalsIgnoreCase(input))
					{
						break;
					}
					index++;
				}
				
				List<WebElement> radioIcons=ele.findElements(By.tagName("input"));
				radioIcons.get(index).click();
			}
			
			test.log(Status.PASS,"Selected required buttons ");
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL,"Unable to Select required buttons ");
			throw e;
		}
	}
	
	public static void fillForm(String[] args) throws Exception
	{
		String registrationType=args[0];
		try {
			JSONObject testdata=JSONUtils.getJsonObject(registrationType.toLowerCase());
			
			Set<String> fields=testdata.keySet();
			
			
			for (String fieldlocator : fields) {
				System.out.println(fieldlocator);
				WebElement ele=getWebElement("id__"+fieldlocator);
				System.out.println(ele.isDisplayed());
				
				scrollIntoElementView(ele);
				String input=(String)testdata.get(fieldlocator);
				if(fieldlocator.substring(0, 3).equalsIgnoreCase("txt"))
				{
					ele.sendKeys(input);
					test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
					//Thread.sleep(2000);
				}
				else
				{
					Select sel=new Select(ele);
					sel.selectByVisibleText(input);
					test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
					//Thread.sleep(2000);
				}
				
			}
			
			test.log(Status.PASS, "Filled fields in the form ");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to Fill fields in the form ");
			throw e;
		}
	}
	
	public static void selectIndustry(String[] args) throws Exception
	{
		String industry=args[0];
		
		try {
			Select select=new Select(getWebElement(selectindustry_dropdown));
			Thread.sleep(2000);
			select.selectByVisibleText(industry);
			
			System.out.println("Selected option : "+select.getFirstSelectedOption().getText());
			test.log(Status.PASS, "Selected the dropdown with value :"+industry);
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
			getWebElement(registrationnext_button).click();
			waitUntilVisible(30, By.xpath("//*[@id='step2']/fieldset/legend"));
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to select the dropdown with value :"+industry);
			throw e;
		}
		
	}
	
	public static void clickRegisterNow() throws Exception
	{
		try {
			
			getWebElement(registernow_link).click();
			test.log(Status.PASS, "Clicked on register now link");
			waitUntilVisible(30, By.id("stepDesc0"));
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
			
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "unable to click on register now link");
			throw e;
		}
	}
	
	public static void acceptWelcomeTerms() throws Exception
	{
		try {
			getWebElement(termsandconditions_checkbox).click();
			getWebElement(welcomenext_button).click();
			test.log(Status.PASS, "Accepted terms and conditons in Welcome page");
			waitUntilVisible(30, By.xpath("//*[@id='step1']/fieldset/legend"));
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to accept terms and conditons in Welcome page");
			throw e;
		}
	}
	
	public static void selectRegistrationType(String[] args) throws Exception
	{
		String registrationType=args[0];
		try {
			
			List<WebElement> list=getWebElements("xpath__//*[@id='tblAccountType']/tbody/tr[2]/td/label");
			int i=0;
			for (WebElement webElement : list) {
				System.out.println(webElement.getText().trim());
				System.out.println(i);
				i++;
			}
			
			int j=0;
			for (WebElement webElement : list) {
				
				if(webElement.getText().trim().equalsIgnoreCase(registrationType))
				{
				break;	
				}
				j++;
			}
			
			List<WebElement> radioIcons=getWebElements("xpath__//*[@id='tblAccountType']/tbody/tr[2]/td/label/input");
			radioIcons.get(j).click();
			
			test.log(Status.PASS, "Clicked on radio button with text "+registrationType);
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
			
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to click on radio button with text "+registrationType);
			throw e;
		}
	}
	
	
	
	
}
