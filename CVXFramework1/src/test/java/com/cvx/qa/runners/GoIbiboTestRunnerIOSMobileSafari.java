package com.cvx.qa.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;

import com.cvx.qa.base.TestBase;

//@RunWith(Cucumber.class)
@CucumberOptions(
		// use this parameter to generate Gherkin scenarios      dryRun=true,
	features= "src/test/java/com/cvx/qa/features",
	glue= {"com.cvx.qa.stepdefinitions"},strict=true,tags= {"@browser"},
	plugin = {"pretty","junit:target/Jenkins/CVXJunitReport.xml","com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/CVX_ExtentReport1.html"},
	monochrome = true
	)

public class GoIbiboTestRunnerIOSMobileSafari extends AbstractTestNGCucumberTests {
	
	@BeforeClass
	public void selectBrowser() throws InterruptedException
	{
	//	System.out.println("First Before");
		TestBase.browserName="IOSIphoneSafari";
		
	}	
}
