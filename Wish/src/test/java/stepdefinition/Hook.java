package stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import utils.InstanceFactory;
import utils.WebDriverUtils;

public class Hook extends WebDriverUtils{

	
	@Before
	public void before(Scenario scenario) throws Exception
	{
		try {
			extent=InstanceFactory.getExtentReportInstance();
			prop=InstanceFactory.getPropertyReaderInstance();
			
			test=extent.createTest(scenario.getName());
			
			WebDriverUtils.openBrowser();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	@After
	public void after()
	{
		extent.flush();
		//driver.close();
		//driver.quit();
	}
}
