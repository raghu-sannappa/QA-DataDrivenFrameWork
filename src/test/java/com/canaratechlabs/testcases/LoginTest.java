package com.canaratechlabs.testcases;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.canaratechlabs.base.BaseTest;

public class LoginTest extends BaseTest {
	
	@Test(groups ="excludeGroup")
	public void doLogin() throws InterruptedException { 
		appLog.debug("Inside LoginTest");
		driver.findElement(By.xpath(OR.getProperty("signinButtonMain"))).click();
		appLog.debug("Clicked on sigin");
	
		Thread.sleep(6000);
		
		WebElement iframeElement = driver.findElement(By.xpath(OR.getProperty("iframe")));

		// Switch to the located iframe
		driver.switchTo().frame(iframeElement);
		appLog.debug("Switeched to iFrame"+iframeElement);
		
		driver.findElement(By.xpath(OR.getProperty("signWithGo"))).click();
		appLog.debug("Clicked On SignInWithGoogle");
		
		String originalWindow = driver.getWindowHandle();
		
		System.out.println("Original Window Id is:-"+originalWindow);
		
		Set<String> windowHandles = driver.getWindowHandles();

		// Switch to the new window
		for (String handle : windowHandles) {
		    if (!handle.equals(originalWindow)) {
		    	System.out.println("Child Window Id is:-"+handle);
		        driver.switchTo().window(handle);
		        appLog.debug("Switched to child window:-"+handle);
		        break;
		    }
		}
		
		String expectedText = "to continue to Agoda";
		String agodatext = driver.findElement(By.xpath("//*[contains(text(),\"to continue to Agoda\")]")).getText();
		System.out.println("Before Assert Condition:....!");
		Assert.assertTrue(agodatext.equals(expectedText),"The agodatext text does not match the expected text. Expected: " + expectedText + " but found: " + agodatext);
		
	/*	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the element to be present in the DOM and visible
		WebElement signInHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath(OR.getProperty("signInTextPP"))));
		appLog.debug("Successfull:-"+signInHeader);
		
		//First Verify SignIn With Google Present
		String expectedText = "Sign in or create an account";
		String signInText = driver.findElement(By.xpath(OR.getProperty("signInTextPP"))).getText();
		appLog.debug("Successfully Fetched SiginIN-----Text");
		Assert.assertTrue(signInText.equals(expectedText),"The sign-in text does not match the expected text. Expected: " + expectedText + " but found: " + signInText);
		
		*/
		
		
		
		
		
		
		
		
	/*driver.findElement(By.xpath(OR.getProperty("signWithGo"))).click();
		appLog.debug("Clicked on siginwithgoogle");
		Thread.sleep(3000);
		*/
	} 

}
