package com.ninza.hrm.api.employetest;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.testng.annotations.Test;

import com.ninza.hrm.api.pojoclass.EmployeePojo;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class EmployeeTest {

	@Test
	public void addEmployeTest() {
		
		//create an object to pojo class
		Random random = new Random();
		int randomNum = random.nextInt(5000);
		
		//API-1==>add a project inside server
		ProjectPojo pojoObj=new ProjectPojo("AAABBB6"+randomNum, "Created", "TEKP", 0);
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .body(pojoObj)
		    
		.when()
		    .post("http://49.249.29.6:8084/addProject");
		    
		 response.then()
		     .assertThat().statusCode(201)
		     .log().all();
		    
		//capture project Name from response
		String projName = response.jsonPath().get("projectName");
		System.out.println(projName);
		
		//API for authentication
		
		
		//API-2==>Add emp to same project
		EmployeePojo empPojo=new EmployeePojo("ATE", "06/10/1994", "tp@gmail.com", "AAA_"+randomNum, 10,"6376493755",projName, "ROLE_EMPLOYEE","AAA_"+randomNum);
		
		given()
	    .contentType(ContentType.JSON)
	    .body(empPojo)
	    
	.when()
	    .post("http://49.249.29.6:8084/employees")
	.then()
	    .assertThat().statusCode(201)
	    .log().all();
	}
}
