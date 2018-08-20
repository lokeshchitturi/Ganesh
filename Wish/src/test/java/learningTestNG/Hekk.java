package learningTestNG;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import utils.WebDriverUtils;

public class Hekk extends WebDriverUtils{

	public static void clickTab(String tabName) throws Exception
	{
		List<WebElement>tabElements=getWebElements("xpath__//a[@class='dropdown-toggle link landingLinks']/span");
		
		for (WebElement webElement : tabElements) {
			System.out.println(webElement.getText());
		}
		for (WebElement webElement : tabElements) {
			if(webElement.getText().equalsIgnoreCase(tabName))
			{
				webElement.click();
				break;
			}
		}
	}
	
	WebDriver driver;
	@Test
	public void f()
	{
		driver=new ChromeDriver();
		driver.get("https://qa.wishkarma.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverUtils.driver=driver;
		
		
		try {
			getWebElement("xpath__//i[@alt='login']").click();
			getWebElement("id__userName").sendKeys("ganesh@cloudseed.io");
			getWebElement("id__password").sendKeys("gani1231");
			getWebElement("id__continueLoginButton").click();
			
			if(getWebElements("linkText__My Projects").size()==1)
				System.out.println("Login sucessful");
			else
				System.out.println("Login unsucessful");
			
			List<WebElement>tabElements=getWebElements("xpath__//a[@class='dropdown-toggle link landingLinks']/span");
			
			System.out.println("----------TabElements------------");
			for (WebElement webElement : tabElements) {
				System.out.println(webElement.getText());
			}
			
			clickTab("kitchen");
			
			//xpath for headers
			//a[@aria-expanded='true']//following-sibling::ul//li[@class='dropdown-header']
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
