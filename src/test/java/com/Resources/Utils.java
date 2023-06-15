package com.Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException{
		if(req==null){
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalProperty(String key) throws IOException{
		String fileName ="C:\\Users\\205620\\workspace\\Automation\\src\\test\\java\\com\\Resources\\Global.properties";
		Properties p= new Properties();
		FileInputStream fis=new FileInputStream(fileName);
		p.load(fis);
		return p.getProperty(key);
	}
	
	public static String getJsonPath(Response response, String key){
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
	}
}
