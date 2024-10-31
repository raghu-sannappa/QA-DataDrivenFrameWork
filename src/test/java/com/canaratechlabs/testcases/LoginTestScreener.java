package com.canaratechlabs.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.canaratechlabs.base.BaseTest;
import com.canaratechlabs.utilities.ExcelReader;
import com.canaratechlabs.utilities.TestUtils;

public class LoginTestScreener extends BaseTest{
	
	@Test(dataProviderClass = TestUtils.class,dataProvider ="dp")
	public void doLogin(Hashtable<String,String> data) throws InterruptedException, IOException { 
		
		if(data.get("runmode").equals("N")) { 
			throw new SkipException("Skipping the test case since run mode set to NO.");
		}
		
		appLog.debug("Inside doLogin Method....!!!");
		driver.findElement(By.xpath(OR.getProperty("loginbutton"))).click();
		appLog.debug("Clicked on InitialLogin Button....!!!");
		
		// Fetch username and password from the data
		String email = data.get("email");
		String password = data.get("password");
		
		// Check if email or password is null
		if (email == null || password == null) {
		    throw new IllegalArgumentException("Email or Password is null. Email: " + email + ", Password: " + password);
		}
		
		driver.findElement(By.xpath(OR.getProperty("username"))).sendKeys(data.get("email"));
		Thread.sleep(4000);
		appLog.debug("Entered Email Id ....!!!");
		driver.findElement(By.xpath(OR.getProperty("password"))).sendKeys(data.get("password"));
		appLog.debug("Entered Password  ....!!!");
		driver.findElement(By.xpath(OR.getProperty("Login"))).click();
		appLog.debug("Clicked on Login button....!!!");
		Thread.sleep(4000);
		
	/*	String actualText = driver.findElement(By.xpath(OR.getProperty("validate"))).getText();
		String expectedText = "Please enter a correct email and password. Note that both fields may be case-sensitive.";
		
		verifyEquals(actualText,expectedText);*/
		
	}
	



}
