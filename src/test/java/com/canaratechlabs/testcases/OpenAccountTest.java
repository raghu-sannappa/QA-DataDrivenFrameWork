package com.canaratechlabs.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.canaratechlabs.base.BaseTest;
import com.canaratechlabs.utilities.ExcelReader;
import com.canaratechlabs.utilities.TestUtils;

public class OpenAccountTest extends BaseTest{
	
	@Test(dataProviderClass = TestUtils.class,dataProvider ="dp")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException { 
		
		driver.findElement(By.xpath(OR.getProperty("createnewaccount"))).click();
		appLog.debug("Clicked on GetFreeAccount Button....!!!");
		driver.findElement(By.xpath(OR.getProperty("email"))).sendKeys(data.get("Email"));
		Thread.sleep(1000);
		appLog.debug("Entered Email Id ....!!!"+(data.get("email")));
		driver.findElement(By.xpath(OR.getProperty("reenteremail"))).sendKeys(data.get("Reenteremail"));
		Thread.sleep(1000);
		appLog.debug("ReEntered EmailId  ....!!!"+(data.get("reenteremail")));
		
		driver.findElement(By.xpath(OR.getProperty("password"))).sendKeys(data.get("Password"));
		Thread.sleep(1000);
		appLog.debug("Entered Password is  ....!!!"+(data.get("password")));
		driver.findElement(By.xpath(OR.getProperty("createaccount"))).click();
		appLog.debug("Createaccount Button clicked and account created successfully....!!!");
		
		
	}
	



}
