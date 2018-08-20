package pageClasses;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.sun.jna.platform.win32.OaIdl.VARKIND;


import utils.WebDriverUtils;

public class Index extends WebDriverUtils{
	
	static String account_link="linkText__Accounts";
	static String accountname_textbox="id__txtAccountName";
	static String search_button="id__btnSearch";
	
	
	static String accountcolumn_accounttable="xpath__//table[@id='grdAccounts']//tr[@class='gridRow']/td[1]/a"; 
	static String sidetab_list="xpath__//table[@id='tab']//tr/td[@class]";
	static String employeesrows_table="xpath__//table[@id='employees']//tr[@class='gridRow']";
	static String employeesheader_table="xpath__//table[@id='employees']//tr[@class='gridHeader']/th";
	static String latestapplication_tab="name__applications";
	static String logout_impersonate="xpath__//*[@id='menuFrontOffice']/div/div/ul[2]/li[2]/a";
	
	public static void logout_impersonate()
	{
		try {
			driver.switchTo().defaultContent();
			getWebElement(logout_impersonate).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void validateFirstNameAndLastNameHome(String[] args) throws Exception
	{
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frmSearch"));
			String expectedFirstName=args[0];
			String expectedLastName=args[1];
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("frmSearch");
			Thread.sleep(3000);
			List<WebElement> rows=getWebElements("xpath__//tr[@class='gridRow']");
			boolean flag=false;
			for (WebElement row : rows) {
				List<WebElement> cols=row.findElements(By.tagName("td"));
				String actualLastName=cols.get(2).findElement(By.tagName("a")).getText().trim();
				String actualFirstName=cols.get(1).getText().trim();
				if(expectedFirstName.equalsIgnoreCase(actualFirstName)&&expectedLastName.equalsIgnoreCase(actualLastName))
				{
					scrollIntoElementView(row);
					flag=true;
					test.log(Status.PASS, "Expected First Name and Last Name found in the lasted application table");
					test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
					break;
				}
			}
			
			if(!flag)
			{
				throw new Exception(" Expected First Name and Last Name not found in the lasted application table");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Expected First Name and Last Name not found in the lasted application table");
			throw e;
		}
	}
	
	
	public static void validateFirstNameAndLastName(String[] args) throws Exception
	{
		try {
			String expectedFirstName=args[0];
			String expectedLastName=args[1];
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frmDisplay");
			driver.switchTo().frame("frmFilter");
			List<WebElement> rows=getWebElements("xpath__//tr[@class='gridRow']");
			boolean flag=false;
			for (WebElement row : rows) {
				List<WebElement> cols=row.findElements(By.tagName("td"));
				String actualLastName=cols.get(1).getText().trim();
				String actualFirstName=cols.get(2).getText().trim();
				if(expectedFirstName.equalsIgnoreCase(actualFirstName)&&expectedLastName.equalsIgnoreCase(actualLastName))
				{
					Login.watermarkId=cols.get(0).findElement(By.tagName("a")).getText().trim();
					flag=true;
					test.log(Status.PASS, "Expected First Name and Last Name found in the lasted application table");
					break;
				}
			}
			
			if(!flag)
			{
				throw new Exception(" Expected First Name and Last Name not found in the lasted application table");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Expected First Name and Last Name not found in the lasted application table");
			throw e;
		}
	}
	
	
	public static void clickLatestApplicationTab() throws Exception
	{
		try {
			driver.switchTo().defaultContent();
			switchToFrame("frmDisplay");
			waitUntilElementClicable(30, By.name("applications"));
			getWebElement(latestapplication_tab).click();
			test.log(Status.PASS, "Clicked on latest application tab");
			//waitUntilElementClicable(30, By.xpath("//tr[@class='gridRow']"));
			test.addScreenCaptureFromPath(captureScreenshotAndGetpath());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to click on latest application tab");
			throw e;
		}
	}
	
	
	public static void impersonateFirstEmployee() throws Exception
	{
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frmDisplay");
			driver.switchTo().frame("frmFilter");
			WebElement firstrow=getWebElements(employeesrows_table).get(0);
			firstrow.findElement(By.xpath("td[9]/a")).click();
			Alert alert=driver.switchTo().alert();
			System.out.println("Alert text "+alert.getText());
			alert.accept();
			waitUntilPageLoads(30);
			waitUntilVisible(30, By.xpath("//*[@id='menuFrontOffice']/div/div/ul[1]/li/a"));
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public static void clickSideTab(String[] tabName) throws Exception
	{
		try {
			
			List<WebElement> tabList=getWebElements(sidetab_list);
			boolean flag=false;
			for (WebElement webElement : tabList) {
				if(webElement.getText().trim().equals(tabName[0]))
				{
					webElement.click();
					flag=true;
					test.log(Status.PASS, "Clicked on tab with name :"+tabName[0]);
					break;
				}
			}
			if(!flag)
			{
				throw new Exception("Unable to find and click on tab with name :"+tabName[0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to find and click on tab with name :"+tabName[0]);
			throw e;
		}
	}
	
	
	public static void clickAccountNameInTable(String[] accountName) throws Exception
	{
		try {
			List<WebElement> accountList=getWebElements(accountcolumn_accounttable);
			boolean flag=false;
			for (WebElement webElement : accountList) {
				if(webElement.getText().trim().equalsIgnoreCase(accountName[0]))
				{
					webElement.click();
					test.log(Status.PASS, "clicked on account link in the searched table with text:"+accountName[0]);
					flag=true;
					waitUntilPageLoads(30);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("frmDisplay");
					validateHeader(accountName);
					break;
				}
			}
			if(!flag)
			{
				throw new Exception(accountName[0]+" not found in the table");
			}
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "unable to click on account link in the searched table with text:"+accountName[0]);
			throw e;
		}
	}
	
	public static void enterAccountName(String[] name) throws Exception
	{
		try {
			waitUntilElementClicable(30, By.id("txtAccountName"));
			getWebElement(accountname_textbox).sendKeys(name[0]);
			test.log(Status.PASS, "Enter account name as :"+name[0]);
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to enter account name");
			throw e;
		}
	}
	
	public static void clickSearch_Account()
	{
		try {
			getWebElement(search_button).click();
			test.log(Status.PASS, "Clicked on search button");
			Thread.sleep(3000);
			
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "unable to click on search button");
		}
	}
	
	
	public static void clickAccountMenu() throws Exception
	{
		try {
			getWebElement(account_link).click();
			switchToFrame("frmSearch");
			String[] header={"Accounts"};
			validateHeader(header);
			test.log(Status.PASS, "Clicked on Account in the menu bar");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to click on Account in the menu bar");
			throw e;
		}
	}
	
	public static boolean validateHeader(String[] headerName) throws Exception
	
	{
		boolean flag=false;
		try {
			List<WebElement> list=getWebElements("xpath__//*[@id='headerCell']/span");
			System.out.println("---------HeaderValues---------");
			for (WebElement webElement : list) {
				if(webElement.isDisplayed())
				{
					System.out.println(webElement.getText());
					if(webElement.getText().trim().equalsIgnoreCase(headerName[0]))
							{
								flag=true;
								test.log(Status.PASS, "Header displayed correctly with text :"+headerName[0]);
								break;
							}
				}
			}
			if(!flag)
			{
				throw new Exception("Header not displayed correctly with text :"+headerName[0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Header not displayed correctly with text :"+headerName[0]);
			throw e;
		}
		return flag;
	}

}
