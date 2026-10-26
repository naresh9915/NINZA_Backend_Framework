package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseApiClass;
import com.ninza.hrm.api.constant.endpoints.IEndPoint;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import io.restassured.response.Response;


@Listeners(com.ninza.hrm.api.listnerutility.ListnerUtility.class)
public class ProjectTest extends BaseApiClass {

	@Test
	public void addSingleProjectWithCreated() throws IOException {
		
		      //create an object to pojo class
			
				String expectedMessage="Successfully Added";
				projectName="AAABBB6"+jUtil.getRandomNumber();
				String BASEURI = fUtil.getDataFromPropertiesFile("BASEUri");
				
				pojoObj=new ProjectPojo(projectName, "Created", "TEKP", 0);
				//verify the project name in API layer
				Response response = given()
				    .spec(reqSpecObj)
				    .body(pojoObj)
				    
				.when()
				    .post(IEndPoint.ADDProj);
				    
				 response.then()
				     .assertThat().statusCode(201)
				     .assertThat().time(Matchers.lessThan(3000L))
				     .spec(respSpecObj)
				     .log().all();
				 //to capture the response key
				 String actualMessage = response.jsonPath().get("msg");
				 Assert.assertEquals(expectedMessage, actualMessage);
						
	}
	
	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void createDuplicateProjectTest() throws IOException {
		
		String BASEURI = fUtil.getDataFromPropertiesFile("BASEUri");
	
		     given()
			    .spec(reqSpecObj)
			    .body(pojoObj)
			    
			.when()
			    .post(IEndPoint.ADDProj)
			 .then()
			 .assertThat().statusCode(409)
			 .spec(respSpecObj)
		     .log().all();
		     
		        
	}
	
	
}
