#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Validating place API's

	@AddPlace  @Regression
  Scenario Outline: Verify if Place is being successfully added using AddPlace API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then The API call got success with code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "GetPlaceAPI"
    
  Examples:
  		|name	  |language	|address					 |
  		|AAhouse|English  |2552 Street Avenue|
  #		|BBhouse|Italian  |Sea Cross Centre|
		
	@DeletePlace  @Regression
	Scenario: Verify if delete place is working
		Given DeletePlace playload
		When User calls "DeletePlaceAPI" with "POST" http request
    Then The API call got success with code is 200
    And "status" in response body is "OK"