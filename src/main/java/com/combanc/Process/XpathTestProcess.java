package com.combanc.Process;

import javax.annotation.Generated;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class XpathTestProcess implements PageProcessor{

	@Override
	public void process(Page page) {
		System.out.println("结果："+page.getHtml().xpath("@lang").get());
	}

	@Override
	public Site getSite() {
		return Site.me();
	}

}
