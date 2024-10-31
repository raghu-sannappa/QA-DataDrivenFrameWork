package com.canaratechlabs.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class TestProperties {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println(System.getProperty("user.dir"));
		Properties config = new Properties();
		Properties OR = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);
		System.out.println(config.getProperty("browser"));
		
		fis =  new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		

	}

}
