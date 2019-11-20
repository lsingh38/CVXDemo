package com.cvx.qa.pages;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.cvx.qa.base.TestBase;
import com.cvx.qa.util.TestUtil;

public class FlightResultPage extends TestBase {
	TestUtil testutil = new TestUtil();
	public static Logger Log = LogManager.getLogger(FlightResultPage.class.getName());
	
	By fareCalendar = By.cssSelector("div[class='fb curPointFlt quicks ico14']");
	By from = By.id("gi_source_st_srp"); 
	By to = By.id("gi_destination_st_srp"); 
	
	public String getFlightResultPageTitle() {
		return driver.getTitle();
	}
	
	public String getFareCalendarText() {
		return testutil.doGetText(fareCalendar);
	}
	
	public String getSourceText() {
		return testutil.doGetAttributeValue(from, "value");
	}
	
	public String getDestinationText() {
		return testutil.doGetAttributeValue(to, "value");
	}

}
