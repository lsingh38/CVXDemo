package com.cvx.qa.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.cvx.qa.base.TestBase;
import com.cvx.qa.pages.FlightResultPage;
import com.cvx.qa.pages.FlightSearchPage;
import com.cvx.qa.util.TestUtil;

public class FlightSearchSteps extends TestBase {
	
	public static Logger Log = LogManager.getLogger(FlightSearchSteps.class.getName());
	FlightSearchPage flightsearchpage;
	FlightResultPage flightresultpage;
	TestUtil testutil;
	String fsptitle;
	String frpActualTitle;
	String frpActualSource;
	String frpActualDestination;
	String frpActualFareCalendarText;
	
	
	public FlightSearchSteps() {
		super();
	}

	   @Given("^User navigates to Flight Search Page$")
	    public void user_navigates_to_flight_search_page() throws Throwable {	   
	        driver.get(prop.getProperty("flightSearchUrl"));
			Log.info("Driver is navigated to url");
			flightsearchpage = new FlightSearchPage();
			testutil = new TestUtil();  
	    }

	    @When("^Retrieve actual title of page$")
	    public void retrieve_actual_title_of_page() throws Throwable {
	    	fsptitle = testutil.doGetPageTitle();
			System.out.println(fsptitle);
			Log.info("The page title is: " + fsptitle); 
	    }
	    

	    @Then("^The actual title is matching with the expected title \"([^\"]*)\"$")
	    public void the_actual_title_is_matching_with_the_expected_title(String expectedTitle) throws Throwable {
	    	Assert.assertEquals(fsptitle, expectedTitle);
			Log.info("Successfully validated the title for page");
	    }
	    

	    
	    @When("^User searches flight from (.+) to (.+)$")
	    public void search_flight_from_to(String source, String destination) throws Throwable {
	    	flightresultpage = flightsearchpage.searchFlight(source, destination);
	    }

	    
	    @And("^Page contains \"([^\"]*)\",(.+),(.+) and title as \"([^\"]*)\"$")
	    public void Page_contains_fareCalendar_source_destination_title(String expectedFareCalendarText, String expectedSource, String expectedDestination, String expectedTitle) throws Throwable {
	    	frpActualFareCalendarText=flightresultpage.getFareCalendarText();
	    	frpActualSource= flightresultpage.getSourceText();
	    	frpActualDestination=flightresultpage.getDestinationText();
	    	frpActualTitle = flightresultpage.getFlightResultPageTitle();	    	
	    	Assert.assertTrue(frpActualFareCalendarText.equalsIgnoreCase(expectedFareCalendarText));
	    	Assert.assertTrue(frpActualSource.equalsIgnoreCase(expectedSource));
	    	Assert.assertTrue(frpActualDestination.equalsIgnoreCase(expectedDestination));
	    	Assert.assertTrue(frpActualTitle.equalsIgnoreCase(expectedTitle));	
	    }
	    
	
}
