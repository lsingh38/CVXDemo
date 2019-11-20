Feature: Test Flight Search Page of GoIbibo
Description: The purpose of this feature is to search for flights on Flight Search Page

Background: User is on Flight Search Page
Given User navigates to Flight Search Page
   
@browser
Scenario: Validate the title of Flight Search Page
When Retrieve actual title of page
Then The actual title is matching with the expected title "Flight Tickets, Flights Booking at Lowest Airfare, Book Air Tickets-Goibibo"

@chrome 
Scenario Outline: Validate FlightResult Page that flights are searched successfully on Flight Search page
When User searches flight from <source> to <destination>
And Page contains "Fare Calendar",<source>,<destination> and title as "Flight Tickets, Flights Booking at Lowest Airfare, Book Air Tickets-Goibibo"

Examples:
|source		|destination	|
|mumbai		|delhi			|
|Bengaluru	|Chennai		|
