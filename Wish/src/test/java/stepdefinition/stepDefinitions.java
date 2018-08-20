package stepdefinition;

import java.io.IOException;
import cucumber.api.java.en.When;
import utils.WebDriverUtils;

public class stepDefinitions extends WebDriverUtils{
	
	WebDriverUtils utils=new WebDriverUtils();
	
		
	@When("((?!data).)*(?<!\\.)\\.{3}(?!\\.)(.*)$")
	public void executeMethod1(String whatever, String PageandMethod) throws Exception,AssertionError {
		String[] PandM = PageandMethod.split(":");
		String classname = PandM[0];
		String methodname = PandM[1];

		if( !utils.executeMethod("pageClasses."+classname, methodname))
		{
		}
		else
		{

		}	
	}

	//Edited Version for Automation Utility 2.0
	@SuppressWarnings("static-access")
	@When("^(.*) (.*data) \\(([^\"]*)\\)(?<!\\.)\\.{3}(?!\\.)(.*)$")
	public void executeMethod(String Whatever, String whateverbeforeData, String  arlistconvert,  String PageandMethod) throws IOException,Throwable  {
		String[] arlist=arlistconvert.split(";");
		String PageandMethodArr = PageandMethod.replace("...", "");
		String PandM[] = PageandMethodArr.split(":");
		String methodName = PandM[1];
		String className = PandM[0];

			
			if( !utils.executeMethod("pageClasses."+className, methodName, arlist)){

			}
			else{

			}
	}
}



