package com.salesforce.base;



import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.salesforce.RequestPojo.LoginReuestPojo;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.salesforce.RequestPojo.patchedDataRequest;
import com.salesforce.ResponsePojo.LoginResponsePojo;
import com.salesforce.utils.EnvironmentDetails;
import com.salesforce.utils.JsonSchemaValidate;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class APIHelper {
    RequestSpecification reqSpec;
   // String token = "";
    protected Logger apiHelperLog = LoggerFactory.getLogger(APIHelper.class);
    private Faker faker;
 String token;


    public APIHelper() {
    	faker=new Faker();
       // RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");

     reqSpec = RestAssured.given();
     
       
    }
  /*  public APIHelper(String token) {
    	
    this.token=token;
    System.out.println("classssss "+this.token);
    }*/


  public Response login(String password,String username,String client_id,String client_secret,String grant_type) {
	  LoginReuestPojo loginRequest  =new LoginReuestPojo().builder().grant_type(grant_type)
   		   .client_id(client_id).client_secret(client_secret)
   		   .username(username).password(password).build();// payload 
     /*  LoginReuestPojo loginRequest  =new LoginReuestPojo().builder().grant_type(EnvironmentDetails.getProperty("grant_type"))
    		   .client_id(EnvironmentDetails.getProperty("client_id")).client_secret(EnvironmentDetails.getProperty("client_secret"))
    		   .username(EnvironmentDetails.getProperty("username")).password(EnvironmentDetails.getProperty("password")).build();// payload
    		   */ 
     Map<String, String> formParams=new HashMap();
    formParams.put("grant_type", loginRequest.getGrant_type());
    formParams.put("client_id", loginRequest.getClient_id());

    formParams.put("client_secret", loginRequest.getClient_secret());

    formParams.put("username", loginRequest.getUsername());

    formParams.put("password", loginRequest.getPassword());
    reqSpec.formParams(formParams) ;
    
   reqSpec.headers("header","application/json;charset=UTF-8");
      reqSpec.log().all();
        Response response = null;
       
         //reqSpec.body(loginRequest); //Serializing  object into a JSON format that can be transmitted over the network. 
             response = reqSpec.when().post("https://login.salesforce.com/services/oauth2/token");
             response.then().log().all();
    

if (response.getStatusCode() == HttpStatus.SC_OK) {
               LoginResponsePojo loginResponse = response.getBody().as(LoginResponsePojo.class);
                this.token = loginResponse.getAccess_token();
                System.out.println("token====="+token);

}
      return response;
}

 public Response getData() {

      RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");

    	reqSpec = RestAssured.given().pathParam("id", EnvironmentDetails.getProperty("id"));
    	reqSpec.headers(getHeaders(false)).  
    			log().params().log().headers();//reqSpec.headers(getHeaders(false));  //  <body>Value for argument "documentPath" is not a valid resource path. Path must be a non-empty string.</body>
	    System.out.println("classssss "+token);

    //	apiHelperLoginfo("Repo: {}", repo);
        Response response = null;
        try {
            response = reqSpec.get("https://dan000000cz3teag-dev-ed.develop.my.salesforce.com/services/data/v58.0/sobjects/account/{id}");
           response.then().log().all();
        } catch (Exception e) {
            apiHelperLog.info("get data functinality is not working ");

            Assert.fail("Get data is failing due to :: " + e.getMessage());
        }
        return response;
    }
    
    
    
    public Response getinvalidData() {

    	apiHelperLog.info("getinvalidData method ");

    	reqSpec = RestAssured.given().pathParam("invalid_id", EnvironmentDetails.getProperty("invalid_id"));
    			reqSpec.headers(getHeaders(false)).
                log().params().log().headers();//reqSpec.headers(getHeaders(false));  //  <body>Value for argument "documentPath" is not a valid resource path. Path must be a non-empty string.</body>
    	apiHelperLog.info("Request body",reqSpec.log().body());

        Response response = null;
        try {
            response = reqSpec.get("account/{invalid_id}");
           response.then().log().body();
       	apiHelperLog.info("Response body",response.then().log().body());

        } catch (Exception e) {
            apiHelperLog.info("get data functinality is working ");

            Assert.fail("Get data is failing due to :: " + e.getMessage());
        }
        return response;
    }
    
 public Response getAllAccountData() {


    	reqSpec = RestAssured.given().baseUri(EnvironmentDetails.getProperty("baseURL"));
    			reqSpec.headers(getHeaders(false)).
                log().params().log().headers().queryParam("acc", "001/o");//reqSpec.headers(getHeaders(false));  //  <body>Value for argument "documentPath" is not a valid resource path. Path must be a non-empty string.</body>
    	
    //	apiHelperLoginfo("Repo: {}", repo);
        Response response = null;
        try {
            response = reqSpec.get("account");
           response.then().log().headers();
        } catch (Exception e) {
            apiHelperLog.info("get all data functinality is not working ");

            Assert.fail("Get all data is failing due to :: " + e.getMessage());
        }
        return response;
    }
/*
    public Response addData(CreateRepoRequest addDataRequest) {
        reqSpec = RestAssured.given();
        reqSpec.headers(getHeaders(false)).log().headers();

        Response response = null;
        try {
        	 apiHelperLog.info("Adding below data :: " + new ObjectMapper().writeValueAsString(addDataRequest));
        	 System.out.println(new ObjectMapper().writeValueAsString(addDataRequest));
            reqSpec.body(new ObjectMapper().writeValueAsString(addDataRequest)); //Serializing addData Request POJO classes to byte stream
            response = reqSpec.post("/user/repos");
            response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Add data functionality is failing due to :: " + e.getMessage());
        }
        return response;
    }
*/
    public Response patchData(patchedDataRequest patchRequest) throws JsonProcessingException {
     //log.info("Adding below data :: " + new ObjectMapper().writeValueAsString(updateDataRequest));

    	reqSpec = RestAssured.given().baseUri(EnvironmentDetails.getProperty("baseURL")).pathParam("id", EnvironmentDetails.getProperty("id"));
        reqSpec.headers(getHeaders(false));
        
        File schemaFile = new File(System.getProperty("user.dir")+"/src/test/resources/ExpectedJsonSchema/AccountPatchRequestSchema.json");

       // reqSpec.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ExpectedJsonSchema/AccountPatchRequestSchema.json"));

        Response response = null;
        try {

   //    log.info("Adding below data :: " + new ObjectMapper().writeValueAsString(updateDataRequest));

          reqSpec.body(new ObjectMapper().writeValueAsString(patchRequest)); //Serializing addData Request POJO classes to byte stream
            response = reqSpec.patch("account/{id}");
            //response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Update data functionality is failing due to :: " + e.getMessage());
        }
        return response;
    }

    public Response deleteRepo() {
    	   reqSpec = RestAssured.given().baseUri(EnvironmentDetails.getProperty("baseURL")).pathParam("owner", EnvironmentDetails.getProperty("accountOwner")).pathParam("repo", EnvironmentDetails.getProperty("deleteRepo"));
        reqSpec.headers(getHeaders(false));
        Response response = null;
        try {
            response = reqSpec.delete("/repos/{owner}/{repo}");
            response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Delete data functionality is failing due to :: " + e.getMessage());
        }
        return response;
    }
    public Response deleteNonExistAccount() {
    	String id =   faker.idNumber().valid();
 	   reqSpec = RestAssured.given().baseUri(EnvironmentDetails.getProperty("baseURL")).pathParam("id",id);
     reqSpec.headers(getHeaders(false));
     Response response = null;
     try {
         response = reqSpec.delete("account/{id}");
         response.then().log().all();
     } catch (Exception e) {
         Assert.fail("Delete data functionality is failing due to :: " + e.getMessage());
     }
     return response;
 }
 
    public HashMap<String, String> getHeaders(boolean forLogin) {
        HashMap<String, String> headers = new HashMap();
       headers.put("Content-Type", "application/json");
        if (!forLogin) {
            headers.put("Authorization", "Bearer " + token);
            System.out.println("token......."+token);;//EnvironmentDetails.getProperty("token"));
        }
        return headers;
    }

}
