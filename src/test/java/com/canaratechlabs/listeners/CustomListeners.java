package com.canaratechlabs.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.canaratechlabs.base.BaseTest;
import com.canaratechlabs.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	
    	test.log(LogStatus.PASS, result.getName().toUpperCase()+"  PASS");
    	repo.endTest(test);
    	repo.flush();
       
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	try {
			TestUtils.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Failed With Exception:"+result.getThrowable());
    	test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.screenshotName));
    	
    	repo.endTest(test);
    	repo.flush();
    	
        System.out.println("Test failed: " + result.getName());
        // Additional logic, e.g., taking a screenshot or logging the failure reason
    }

    @Override
    public void onTestSkipped(ITestResult result) {
       test.log(LogStatus.SKIP, result.getName().toUpperCase()+"Skipped Test Case As The RunMode is N.");
       repo.endTest(test);
   	   repo.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        test = repo.startTest(context.getName().toUpperCase());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finished test suite: " + context.getName());
        // You could generate a report or clean up resources here
    }
}
