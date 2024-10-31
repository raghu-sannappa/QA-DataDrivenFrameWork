package com.canaratechlabs.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.canaratechlabs.base.BaseTest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import com.canaratechlabs.base.BaseTest;

public class TestUtils extends BaseTest {
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException { 
		
		screenshotName = "error.jpg";
		//Saving the screenshot in desired location
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//Path to the location to save screenshot
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") +"\\target\\surefire-reports\\Data Driven Basics\\"+screenshotName));
		
	}
	
	@DataProvider(name ="dp")
	 public static Object[][] getData(Method m) {
	     /*   if (excel == null) { 
	            // Path to Excel file
	            excel = new ExcelReader("C:\\selenium\\testdata.xlsx");
	        }*/
	        
	        String sheetName = m.getName();
	        
	        // Get row and column count from the sheet
	        int rows = excel.getRowCount(sheetName);
	        int cols = excel.getColumnCount(sheetName);
	        
	     // Ensure rows and cols are valid
	        if (rows <= 1 || cols == 0) {
	            throw new RuntimeException("Invalid row/column count: rows=" + rows + ", cols=" + cols);
	        }
	        
	        // Creating a data array to hold the Excel data
	        Object[][] data = new Object[rows - 1][1];  // rows-1 to skip the header row
	        
	        Hashtable<String,String>table = null;
	        // Loop through rows and columns to fetch data
	        for (int rowNum = 2; rowNum <=rows; rowNum++) {   // Start from 2 to skip header
	        	
	         	table = new Hashtable<String,String>();
	        	
	        	 for (int colNum = 0; colNum < cols; colNum++) {
	        			table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
	        		 
	             }
	             data[rowNum - 2][0] = table;
	            }
	        
	        return data;
	    }

}
