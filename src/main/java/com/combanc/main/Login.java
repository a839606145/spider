package com.combanc.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login {

	public static void main(String args[]){
		String url="https://passport.zhaopin.com/account/login";
		ChromeOptions chromeOptions = new ChromeOptions();
		//设置chrome不显示界面
		chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        //设置头文件（查看浏览器头文件网址：https://httpbin.org/get?show_env=1）
        chromeOptions.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        chromeOptions.addArguments("Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        chromeOptions.addArguments("Accept-Encoding=gzip, deflate, br");
        chromeOptions.addArguments("Accept-Language=zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        chromeOptions.addArguments("Cache-Control=max-age=0");
        //设置浏览器大小，不重要
        chromeOptions.addArguments("--window-size=1024,512");
		System.setProperty("webdriver.chrome.driver" ,  "E:/chromeDreiver/chromedriver.exe");
	    WebDriver driver = new ChromeDriver(chromeOptions);
	    driver.get(url);
		driver.findElement(By.id("loginName")).clear();
		driver.findElement(By.id("loginName")).sendKeys("a839606145@126.com");
		
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("a839606145");
		driver.findElement(By.id("submit")).click();
		
		try {
            Thread.sleep(1000);
            System.out.println(driver.manage().getCookies());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
			
		}
		
	}
}
