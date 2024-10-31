package com.canaratechlabs.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.canaratechlabs.utilities.ExcelReader;
import com.canaratechlabs.utilities.ExtentManager;
import com.canaratechlabs.utilities.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class BaseTest {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")  + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports repo = ExtentManager.getInstance();
	public static ExtentTest test;
	
	 public static final Logger appLog = Logger.getLogger("appFile");
	 public static final Logger seleniumLog = Logger.getLogger("seleniumFile");
	
	@BeforeSuite
	public void setUp() { 
		if(driver==null) { 
		
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				appLog.debug("config file loaded.!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis =  new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				appLog.debug("OR file loaded.!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(config.getProperty("browser").equals("chrome")) { 
			driver = new ChromeDriver();
			appLog.debug("Chrome Launched Successfully....!!!");
		}else if(config.getProperty("browser").equals("firefox")) { 
			driver = new FirefoxDriver();
			appLog.debug("Firefox Launched Successfully....!!!");
		}else if(config.getProperty("browser").equals("IE")) { 
			driver = new InternetExplorerDriver();
			appLog.debug("InternetExplorer Launched Successfully....!!!");
		}
		
		driver.get(config.getProperty("testsiteurl2"));
		appLog.debug("Navigated to TestSite URL....!!!"+config.getProperty("testsiteurl12"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit-wait"))));
	    
		//Explicit Wait 
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	@AfterSuite
	public void tearDown() {
		
		driver.quit();
		
		appLog.debug("Test Execution Completed..!!");
		
	}
	
	public static void verifyEquals(String expected,String actual) throws IOException { 
		
		try { 
			Assert.assertEquals(actual, expected);
			
		}catch(Throwable t){ 
			
			TestUtils.captureScreenshot();
			test.log(LogStatus.FAIL, " Verification Failed With Exception:"+t.getMessage());
	    	test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.screenshotName));
		}
	}
	

}
