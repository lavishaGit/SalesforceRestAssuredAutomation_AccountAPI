package com.salesforce.loginTest;


import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.base.APIHelper;
import com.salesforce.base.BaseTest;
import com.salesforce.utils.EnvironmentDetails;
import com.salesforce.utils.ExtentReportsUtility;
import com.salesforce.utils.JsonSchemaValidate;
import com.salesforce.ResponsePojo.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
@Listeners(com.salesforce.utils.SalesforceListenerUtility.class)

public class LoginFunctionality extends BaseTest{
	ExtentReportsUtility reportExtent=ExtentReportsUtility.getInstance();
  protected Logger LOGGER = LogManager.getLogger();
  APIHelper apiHelper;
  
  @BeforeClass
  public void beforeclass() {
	  apiHelper = new APIHelper();
  }
  
	@Test(priority = 0)
	public void validatelogin() {
		 

	Response dataResponse=	apiHelper.login(EnvironmentDetails.getProperty("password"),EnvironmentDetails.getProperty("username"),
			EnvironmentDetails.getProperty("client_id"),EnvironmentDetails.getProperty("client_secret"),EnvironmentDetails.getProperty("grant_type"));
	
       LoginResponsePojo loginResponse = dataResponse.getBody().as(LoginResponsePojo.class);
      /* if (response.getStatusCode() == HttpStatus.SC_CREATED) {
           List<LoginResponsePojo> loginResponse = response.getBody().as(new TypeRef<List<LoginResponsePojo>>() {
           });
           this.token = loginResponse.get(0).getAccess_token();
           System.out.println("token====="+token);*/
       /*  String token=  loginResponse.getAccess_token();
         System.out.println("token====="+token);

         APIHelper apiHelper=new APIHelper(token);*/

        dataResponse.then().body("token_type", equalTo("Bearer"));
        reportExtent.logTestInfo("Successful validated tokenType with expected data");

        Assert.assertEquals(loginResponse.getInstance_url(),"https://dan000000cz3teag-dev-ed.develop.my.salesforce.com");
        reportExtent.logTestInfo("Successful validated instanceURL with expected data");

       JsonSchemaValidate.validateSchema(dataResponse.asPrettyString(), "LoginResponseSchema.json");
       reportExtent.logTestInfo("Validation passed for the Schema match, ensuring Json  accuracy");


/*
		RestAssured.given().formParam("grant_type", "password")
        .formParam("username", "sweety123@yahoo.com")
        .formParam("password", "lavisha2212ycd8ZcQowo13cV8nstyAyFQ6Q")
        .formParam("client_id","3MVG9.OYpR1Qkmcx3JciEcDg5fAnyusxzOcia4.TnoSKSMmxr6d36PO_n8PvlfCj6SIUaSF4qoJKs33a4yZrL")
        .formParam("client_secret","9920B1D54DA71FE20D1E8E971C583E0075845CD3E39F1630DDA3C245EAA3575A")
        

        .when().log().all()
        .post("https://login.salesforce.com/services/oauth2/token")
		.then().log().all();
	
		*/
	

}}
