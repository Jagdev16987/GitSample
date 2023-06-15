package com.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features ="C:\\Users\\205620\\workspace\\Automation\\src\\test\\java\\com\\Feature\\PlaceValidation.feature",
		plugin={"pretty","json:C:\\Users\\205620\\workspace\\Automation\\target\\jsonReports\\cucumber-reports.json"},
		glue={"com.stepDefinition"},
		strict = true,
		monochrome =true)
		
		//tags="@DeletePlace")
public class TestRunner {

}
