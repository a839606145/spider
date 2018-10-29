package com.combanc.spider.bootstrap;

import com.combanc.spider.process.XpathTestProcess;

public class Bootstrap {

	public static void main(String[] args) {
		
		// ZLProcess processor=new ZLProcess();
		  XpathTestProcess processor=new XpathTestProcess();
		 us.codecraft.webmagic.Spider.create(processor)
         .addUrl("https://xiaoyuan.zhaopin.com/full/0/")
         .thread(1)
         .run();
	}
}
