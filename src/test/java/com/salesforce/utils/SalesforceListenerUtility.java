package com.salesforce.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.salesforce.base.BaseTest;


public class SalesforceListenerUtility  implements ITestListener {
	Logger ListenerLog = LogManager.getLogger();
	private static ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	public void onTestStart(ITestResult result) {
		ListenerLog.info(result.getMethod().getMethodName() + ".......test execution started........");
		report.startSingleTestReport(result.getMethod().getMethodName() + ".......execution started........");
		// Test container for report is created........");

	}
	
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
				ListenerLog.info(result.getMethod().getMethodName() + ".......test execution successful........");
				report.logTestpassed(result.getMethod().getMethodName() + ".......execution successful........");
				// execution sucess........");

	}
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
				ListenerLog.error(result.getMethod().getMethodName() + ".......test execution completed with failure........");
				report.logTestFailed(
						result.getMethod().getMethodName() + ".......test execution completed with failure........");
				
				report.logTestFailedWithException(result.getThrowable());

	}
	
	
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}
	public void onStart(ITestContext context) {
		// context contains all the informa about the tests
			ListenerLog.info(".......<test> execution started........");
				report.startExtentReport();
	}
	public void onFinish(ITestContext context) {
		ListenerLog.info(".......<test> execution completed........");
		report.endReport();
		}

	

}

