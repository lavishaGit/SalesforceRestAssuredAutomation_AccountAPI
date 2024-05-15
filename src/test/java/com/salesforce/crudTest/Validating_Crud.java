package com.salesforce.crudTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.mozilla.javascript.IdFunctionCall;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.salesforce.RequestPojo.patchedDataRequest;
import com.salesforce.ResponsePojo.AccountResponsePojo;
import com.salesforce.ResponsePojo.AlldataRoot;
import com.salesforce.ResponsePojo.InvalidAccountResponsePojo;
import com.salesforce.ResponsePojo.LoginResponsePojo;
import com.salesforce.ResponsePojo.RecentItem;
import com.salesforce.ResponsePojo.StatusResponse;
import com.salesforce.base.APIHelper;
import com.salesforce.base.BaseTest;
import com.salesforce.utils.Constants;
import com.salesforce.utils.EnvironmentDetails;
import com.salesforce.utils.ExcelUtils;
import com.salesforce.utils.ExtentReportsUtility;
import com.salesforce.utils.JsonSchemaValidate;
import com.salesforce.utils.TestDataUtils;

import freemarker.log.Logger;
import io.restassured.response.Response;


@Listeners(com.salesforce.utils.SalesforceListenerUtility.class)
public class Validating_Crud extends BaseTest{
	ExtentReportsUtility reportExtent=ExtentReportsUtility.getInstance();

	private Faker faker;
	APIHelper apiHelper;
	ExcelUtils excelUtil;
	ArrayList<ArrayList<Object>>  list;
	String name;

	String BillingCity="Austin";
	String BillingState="Tx";
	String Type="Customer - Direct";
	String ParentId=null;
	String BillingStreet= "312 Constitution Place\nAustin, TX 78767\nUSA";

	@BeforeClass
	public void beforeClass() throws Exception {
        faker = new Faker();

		apiHelper = new APIHelper();
		excelUtil=new ExcelUtils();
		excelUtil.Excel(Constants.EXCEL_FILEPATH, "Sheet1");
		 list=	excelUtil.returnTheCompleteData();
		 Response dataResponse=	apiHelper.login(EnvironmentDetails.getProperty("password"),EnvironmentDetails.getProperty("username"),
					EnvironmentDetails.getProperty("client_id"),EnvironmentDetails.getProperty("client_secret"),EnvironmentDetails.getProperty("grant_type"));
			
	}
	
	@Test(priority = 1)
	public void validateGetAccountAPI() throws Exception {
		
	
		Response data = apiHelper.getData();
System.out.println(data.asPrettyString());
AccountResponsePojo  accountResponsePojo=  data.getBody().as(AccountResponsePojo.class);
//String cityString=   accountResponsePojo.billingAddress.getCity();
//System.out.println("==========>"+cityString);
if (accountResponsePojo.getBillingAddress() != null) {

Assert.assertEquals(accountResponsePojo.getBillingAddress().getCity(),"Austin");}
String contentType= data.getContentType();
Assert.assertEquals(contentType,"application/json;charset=UTF-8","\"Expected content type to be 'application/json;charset=UTF-8', but found a different content type");
int expectedStatusCode  = BaseTest.validateStatus(list, 2, 1,"Success");
System.out.println(expectedStatusCode );
/*for(ArrayList<Object>rowdata:list ) {
    if (rowdata != null && rowdata.size() > 1) {
	if(rowdata.get(0).equals("get"))
	{
		String expectedStatusCodeString = (String) rowdata.get(1); // Assuming it's a string representation of an integer
        int expectedStatusCode = Integer.parseInt(expectedStatusCodeString);
    this.expectedStatusCode=   expectedStatusCode;


//java.lang.IllegalArgumentException: Schema to use cannot be null
	}
	}
	}*/
Assert.assertEquals(data.getStatusCode(), expectedStatusCode,
		"Get data functionality is not working as expected.");
JsonSchemaValidate.validateSchema(data.asPrettyString(), 
 "AccountSchema.json");
reportExtent.logTestInfo("Validation passed for the Schema match, ensuring Json  accuracy");

	}

	
	@Test(priority = 2)
	public void invalidGetAccountAPI() {

		Response data = apiHelper.getinvalidData();System.out.println(data.asPrettyString());

		InvalidAccountResponsePojo[] invalidAccountResponsePojo=data.getBody().as(InvalidAccountResponsePojo[].class);
		for(InvalidAccountResponsePojo element:invalidAccountResponsePojo) {
	String actualError=	element.getErrorCode();
	System.out.println( actualError );
	for(ArrayList<Object>rowdata:list ) {
	    if (rowdata != null && rowdata.size() > 1) {
	    	if(rowdata.get(2).equals("NOT_FOUND")){
	    		String expErrorCode=(String) rowdata.get(2);
	    		Assert.assertEquals(actualError, expErrorCode);
break;
	    	}
	    }
	    }
	int expectedStatusCode  = BaseTest.validateStatus(list, 2, 1,"NOT_FOUND");

	//Assert.assertEquals(data.getStatusCode()  , expectedStatusCode,
		//	"Get data functionality is not working as expected.");
		MatcherAssert.assertThat(data.getStatusCode(), equalTo(expectedStatusCode));}
	JsonSchemaValidate.validateSchemaInClassPath(data, "ExpectedJsonSchema/InvalidAccountSchema.json");
	reportExtent.logTestInfo("Validation passed for the Schema match, ensuring Json  accuracy");

	}

	@Test(priority = 2)
 	public void GetAllRecentAccounts() {
		Response data = apiHelper.getAllAccountData();
		AlldataRoot alldataRoot=data.getBody().as(AlldataRoot.class);
	String	actualtype= alldataRoot.getRecentItems().get(0).attributes.getType();
	String	id=	alldataRoot.getRecentItems().get(0).getId();
	Assert.assertEquals(id,"001an0000010OJXAA2");
	reportExtent.logTestInfo("Successfully verified the type value as 'id'.");

	Assert.assertEquals(actualtype,"Account");
	reportExtent.logTestInfo("Successfully verified the type value as 'Account'.");
		data.then().body("recentItems[1].Name", equalTo("sForce"));
		reportExtent.logTestInfo("Validation passed for the name value, ensuring accurate data entry");
	JsonSchemaValidate.validateSchema(data.asPrettyString(), 
			 "AllAccountSchema.json");
	reportExtent.logTestInfo("Validation passed for the Schema match 'AllAccountSchema.json', ensuring Json  accuracy");

	}
	
	@Test(priority = 3)
	public void validatePatchedStatus() throws JsonProcessingException {
		
name= faker.company().name();
	patchedDataRequest	patchedRequest=new patchedDataRequest().builder().name(name).billingCity(BillingCity).billingState(BillingState).billingStreet(BillingStreet).parentId(ParentId).type(Type).build();
	
	Response data=apiHelper.patchData(patchedRequest);
	data.then().log().cookies();
	String actualCookieValue = data.getCookie("CookieConsentPolicy");
	Assert.assertEquals(actualCookieValue,"0:1");
	reportExtent.logTestInfo("Successfully verified a Cookie 'Domain' value ");

	   Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NO_CONTENT, "Add data functionality is not working as expected.");
		reportExtent.logTestInfo("Successfully verified the Status code  as 204.");
		reportExtent.logTestInfo(" Request for updated  Data Successfully updated and verified");

	  // JsonSchemaValidate.validateSchema(data.asPrettyString(), "ExpectedJsonSchema/AccountPatchRequestSchema.json");
}
	@Test(dependsOnMethods = {"validatePatchedStatus"})
	public void validatePatchedData() throws JsonProcessingException {
		
		Response data = apiHelper.getData();
		//System.out.println(data.asPrettyString());
		AccountResponsePojo  accountResponsePojo=  data.getBody().as(AccountResponsePojo.class);
		
		Assert.assertEquals(accountResponsePojo.getName(),name);
		reportExtent.logTestInfo("Validation passed: Patched Account name matches expected value.");
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Response code is not matching for get data.");
		reportExtent.logTestInfo("Successfully verified the Status code  as 200.");

		data.then().body("ShippingAddress.city", nullValue());
		data.then().body("Phone", endsWith("6000"));
		data.then().body("PhotoUrl", containsString("001an0000010OJXAA2"));
		reportExtent.logTestInfo("Validation passed for multiple resonse body: Shipping address,Phone,PhotoUrl matches with their expected values.");

	}
	
	@Test(priority = 5)
	public void validateDeletedNonExistAccount() {
		Response data = apiHelper.deleteNonExistAccount();
		Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NOT_FOUND,
				"Response code is not matching for get data.");
		reportExtent.logTestpassed(" Validation for Status code 404 is  passed successfully" );

		StatusResponse[] status = data.getBody().as(StatusResponse[].class);
		Assert.assertEquals(status[0].getErrorCode(), TestDataUtils.getProperty("message"));
		reportExtent.logTestInfo("Validation passed: Error code Not Found matches expected value.");

	}

}

