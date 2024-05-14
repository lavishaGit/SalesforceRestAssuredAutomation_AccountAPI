package com.salesforce.base;



import java.util.ArrayList;

import org.testng.annotations.BeforeSuite;

import com.salesforce.utils.EnvironmentDetails;
import com.salesforce.utils.TestDataUtils;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        EnvironmentDetails.loadProperties();
       TestDataUtils.loadProperties();
    }
    
    public static int validateStatus(ArrayList<ArrayList<Object>> list, int actionIndex, int statusCodeIndex, String message) {
    	 int expectedStatusCode = 0;;
        for (ArrayList<Object> rowData : list) {
        	
            if (rowData != null && rowData.size() >statusCodeIndex) {
                if (message.equals(rowData.get(actionIndex))) {
                    // Call the reusable method to parse the expected status code
                	{
                		String expectedStatusCodeString = (String) rowData.get( statusCodeIndex); // Assuming it's a string representation of an integer
                   expectedStatusCode = Integer.parseInt(expectedStatusCodeString); 
                   break;
                     }
            }
}
}
		return expectedStatusCode;}}