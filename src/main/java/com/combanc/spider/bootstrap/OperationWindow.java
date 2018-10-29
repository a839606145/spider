package com.combanc.spider.bootstrap;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class OperationWindow {

	public ChromeDriverService getService(){
		String path=System.getProperty("user.dir");
		String windowsPath = "E:/chromeDreiver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", "E:\\chromeDreiver\\chromedriver.exe");
		ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(windowsPath)).usingAnyFreePort().build();
		return service;
	}
	
	public WebDriver getDriver(ChromeDriverService service){
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
		WebDriver driver = new ChromeDriver(service, chromeOptions);
        
        try {
            //部分网址进入后需等待1秒才加载，如有加载时间更长的网站在此设置等待时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return driver;
	}
	
	/**
	 * 打开网页
	 * @param driver 驱动
	 * @param url	 网页网址
	 */
	public void openUrl(WebDriver driver,String url){

		//打开网页
		driver.get(url);
		try {
			//部分网址进入后需等待0.2秒才加载，如有加载时间更长的网站在此设置等待时间
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭当前标签页
	 * @param driver
	 */
	public static void closeWindow(WebDriver driver){
		driver.close();
	}
	
	/**
	 * 关闭所有标签页
	 * @param driver
	 */
	public static void quitWindow(WebDriver driver){
		driver.quit();
	}
}
