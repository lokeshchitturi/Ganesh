package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class WebDriverUtils {
	
	/*for (WebElement webElement : list) {
		System.out.println(webElement.isDisplayed());
		System.out.println("----"+webElement.getText()+"-----");
	}*/
	public static WebDriver driver=null;
	public static Properties prop=new Properties();
	public static ExtentReports extent=null;
	public static ExtentTest test=null;
	
	public static void openBrowser() throws Exception
	{
		String browserName=null;
		try {
			browserName=prop.getProperty("browser");
			System.out.println(browserName);
			switch (browserName.toLowerCase()) {
			case "chrome":
				driver=new ChromeDriver();
				break;
			case "firefox":
				driver=new FirefoxDriver();
				break;
			default:
				throw new Exception("Unable to launch browser :"+browserName);
			}
			test.log(Status.PASS, "Launced browser :"+browserName);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "Unable to launch browser :"+browserName);
			throw e;
		}
	}
	
	
	
	
	
	public static void clickELementJS(WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	
	
	
	private static void until(WebDriver driver,Function<WebDriver, Boolean> waitCondition,Long i)
	{
			WebDriverWait webDriverWait=new WebDriverWait(driver, i);
			webDriverWait.withTimeout(20, TimeUnit.SECONDS);
			try {
				webDriverWait.until(waitCondition);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
	}
	
	
	public static void untilJQueryIsDone(Long timeoutInSeconds)
	{	
		until(driver, (driver) ->{
			Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}
	
	public static void waitUntilPageLoads(int pageLoadTimeout) throws Exception
	{
		try {
			new WebDriverWait(driver, pageLoadTimeout).until(
			          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public static void waitUntilFrameAvailableAndSwitch(int timeOutInSeconds,String frameName) throws Exception
	{
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public static WebElement getWebElement(String args) throws Exception
	{
		try {
			WebElement element=null;
			String[] value=args.split("__");
			String locatorType=value[0];
			String locatorValue=value[1];
			switch (locatorType) {
			case "id":
				element=driver.findElement(By.id(locatorValue));
				break;
			case "xpath":
				element=driver.findElement(By.xpath(locatorValue));
				break;
			case "css":
				element=driver.findElement(By.cssSelector(locatorValue));
				break;
			case "name":
				element=driver.findElement(By.name(locatorValue));
				break;
			case "linkText":	
				element=driver.findElement(By.linkText(locatorValue));
				break;
			case "partialLinkText":
				element=driver.findElement(By.partialLinkText(locatorValue));
			default:
				throw new Exception(locatorType+" not found in the locator list");
			}
			return element;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
	public static void actionClick(String locator) throws Exception
	{
		Actions action=new Actions(driver);
		WebElement ele=getWebElement(locator);
		action.moveToElement(ele).click(ele).build().perform();
	}
	
	public static void actionSendkeys(String locator,String value) throws Exception
	{
		Actions action=new Actions(driver);
		WebElement ele=getWebElement(locator);
		action.moveToElement(ele).click(ele).sendKeys(value).build().perform();
	}
	
	public static void jsSendKeys(String locator,String value) throws Exception
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		WebElement ele=getWebElement(locator);
		
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", ele,"value",value);
	}
	
	public static String captureScreenshotAndGetpath() throws IOException
	{
		File fs =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-dd-MM-HH-mm-SS");
		String dest=System.getProperty("user.dir")+"//TestResultScreenShot//Image_"+sdf.format(cal.getTime())+".png";
		FileUtils.copyFile(fs, new File(dest));
		System.out.println("screenshot taken");
		return dest;
	}

	public static void main(String[] args) {
		
		
		
	}
	
	public static List<WebElement> getWebElements(String args) throws Exception
	{
		try {
			List<WebElement> Elements=new ArrayList<>();
			String[] value=args.split("__");
			String locatorType=value[0];
			String locatorValue=value[1];
			switch (locatorType) {
			case "id":
				Elements=driver.findElements(By.id(locatorValue));
				break;
			case "xpath":
				Elements=driver.findElements(By.xpath(locatorValue));
				break;
			case "css":
				Elements=driver.findElements(By.cssSelector(locatorValue));
				break;
			case "name":
				Elements=driver.findElements(By.name(locatorValue));
				break;
			case "linkText":	
				Elements=driver.findElements(By.linkText(locatorValue));
				break;
			default:
				throw new Exception(locatorType+" not found in the locator list");
			}
			System.out.println("size of list :"+Elements.size());
			return Elements;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
	public static void switchToFrame(String frameName)
	{
		driver.switchTo().frame(frameName);
	}
	
	public static Alert getAlert() 
	{ 
		Alert alert=null;
	    try 
	    { 
	        alert=driver.switchTo().alert(); 
	        return alert; 
	    }   // try 
	    catch (Exception Ex) 
	    { 
	        return alert; 
	    }   // catch 
	}
	
	public static void scrollIntoElementView(WebElement ele)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].scrollIntoView()", ele);
	}
	
	public String captureScreenShot(String fileName) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			String destLocation=System.getProperty("user.dir")+"/Screenshots/"+fileName;
			FileUtils.copyFile(src, new File(destLocation));
			
			return destLocation;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void moveToElement(WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	public static void moveToElementAndClick(WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).click(element).build().perform();
	}
	
	
	public static void waitUntilPresenceOfElement(int timeoutSeconds,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.withTimeout(timeoutSeconds, TimeUnit.SECONDS);
		//new WebDriverWait(driver,10).until(ExpectedConditions.)
	}
	
	
	public static void waitUntilElementClicable(int timeoutSeconds,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		wait.withTimeout(timeoutSeconds, TimeUnit.SECONDS);
		
	}
	
	
	public static void waitUntilVisible(int timeoutSeconds,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.withTimeout(timeoutSeconds, TimeUnit.SECONDS);
		
	}

	public static void javaScriptClickElement(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
	}
	
	
	public boolean executeMethod(String classname, String methodname) throws Exception,AssertionError  {
		try {
			boolean returnvalue = false;
			Object returnvalueObject= null;
			Object classobject = Class.forName(classname).newInstance();
			//Object castedObject = Class.forName(classname).cast(classobject);
			Class[] argtypes = new Class[] {};
			//Method method = classobject.getClass().getMethod(methodname,String.class,String.class);
			Method method = classobject.getClass().getMethod(methodname,argtypes);
			try {
				returnvalueObject=method.invoke(classobject);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw(e);
			}
			catch (NullPointerException e) {
				System.out.println("There is no return defined in the Method");
				e.printStackTrace();
				throw(e);
			}
			return returnvalue;
		}
		catch (InstantiationException e) {
			e.printStackTrace();
			throw(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw(e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw(e);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw(e);
		} catch(Exception e){
			e.printStackTrace();
			//MainDriverTest.finalresult.add( "FAIL");
			throw(e);
			//return false;
		}catch (AssertionError e) {
			// TODO Auto-generated catch block
			//MainDriverTest.finalresult.add( "FAIL");
			e.printStackTrace();
			throw(e);
			//return false;
		}
	}

	public boolean executeMethod(String classname, String methodname,String[] arlist) throws Exception,AssertionError{
		try {
			boolean returnvalue = false;
			Object returnvalueObject= null;
			Class cls = Class.forName(classname);
			Object obj = cls.newInstance();
			Class[] paramString = new Class[3];
			paramString[0] = String.class;
			paramString[1] = String.class;
			paramString[2] = String.class;
			Class [] argtypes = new Class [] {String[].class};
			java.lang.reflect.Method method = cls.getDeclaredMethod(methodname, argtypes); 
			try {
				returnvalueObject = method.invoke(obj,(Object)arlist);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw(e);
			}catch(Exception e){
				throw(e);
			}catch(AssertionError e){
				throw(e);
			}
			return returnvalue;
		}
		catch (InstantiationException e) {
			e.printStackTrace();
			throw(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw(e);
			//return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//MainDriverTest.finalresult.add( "FAIL");
			e.printStackTrace();

			throw(e);
			//return false;
		}catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//MainDriverTest.finalresult.add("FAIL");
			//err.commonExecutorlogError("Function");
			e.printStackTrace();
			throw(e);
			//return false;
		}catch (SecurityException e){
			// TODO Auto-generated catch block
			//MainDriverTest.finalresult.add(MainDriverTest.loopvar, "FAIL");
			e.printStackTrace();
			throw(e);
			//return false;
		}catch(Exception e){
			e.printStackTrace();
			//MainDriverTest.finalresult.add( "FAIL");
			throw(e);
			//return false;
		}catch (AssertionError e) {
			// TODO Auto-generated catch block
			//MainDriverTest.finalresult.add( "FAIL");
			e.printStackTrace();
			throw(e);
			//return false;
		}
	}
}


