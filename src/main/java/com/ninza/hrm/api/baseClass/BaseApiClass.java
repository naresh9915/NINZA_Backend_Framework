package com.ninza.hrm.api.baseClass;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseApiClass {
	public WebDriver driver;
	public static WebDriver sdriver;
	
	public String projectName;
	public ProjectPojo pojoObj;
	public static RequestSpecification reqSpecObj;
	public static ResponseSpecification respSpecObj;
	public FileUtility fUtil=new FileUtility();
	public JavaUtility jUtil = new JavaUtility();
	
	@BeforeSuite
	public void configBS() throws IOException {
		System.out.println("before suite");
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username", "password"));
		//builder.addHeader("", "");
		builder.setBaseUri(fUtil.getDataFromPropertiesFile("BASEUri"));
		reqSpecObj = builder.build();
		
		ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
		resBuilder.expectContentType(ContentType.JSON);
		respSpecObj = resBuilder.build();
			
	}
	
	@AfterSuite
	public void configAS() {
		System.out.println("after suite");
	}
}
