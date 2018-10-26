package com.combanc.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverU {

	private static WebDriver driver;
	static{
		System.setProperty("webdriver.chrome.driver", "E:\\chromeDreiver\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
	
}
