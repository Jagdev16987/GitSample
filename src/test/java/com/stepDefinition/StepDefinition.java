package com.stepDefinition;

import static io.restassured.RestAssured.given;

import com.Resources.APIResources;
import com.Resources.TestDataBuild;
import com.Resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

	ResponseSpecification responseSpec;
	RequestSpecification request;
	Response response;
	TestDataBuild tdb=new TestDataBuild(); 
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address)  throws IOException {
		// Write code here that turns the phrase above into concrete actions
		request=given().spec(requestSpecification())
				.body(tdb.addPlacePayload(name,language,address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions

		APIResources resourceAPI=APIResources.valueOf(resource);

		responseSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		System.out.println(resourceAPI.getResource());

		if(method.equalsIgnoreCase("POST")){
			response=request.when().post(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("GET")){
			response=request.when().get(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("DELETE")){
			response=request.when().delete(resourceAPI.getResource());
		}
	}

	@Then("The API call got success with code is {int}")
	public void the_API_call_got_success_with_code_is(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		//		.then().spec(responseSpec).extract().response();
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(getJsonPath(response,keyValue),expectedValue);
	}
	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String ExpName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJsonPath(response,"place_id");
		request=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(response,"name");
		Assert.assertEquals(actualName, ExpName);
	}
	
	@Given("DeletePlace playload")
	public void deleteplace_playload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		request=given().spec(requestSpecification()).body(tdb.deleteBody(place_id));
	}
}
