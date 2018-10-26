package com.combanc.Process;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import okhttp3.Request;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class ZLProcess implements PageProcessor{

	private Site site=Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
	String domainUrl="https://xiaoyuan.zhaopin.com";
	public void process(Page page) {
		try{
			System.out.println(page.getRequest().getUrl());
			if(page.getRequest().getUrl().matches("https://xiaoyuan.zhaopin.com/full/0/.*")){//列表页
				 List<String> urls = page.getHtml().links().all();
				 for(String url:urls){
					 if(url.contains("//xiaoyuan.zhaopin.com/job/")){//列表页
						us.codecraft.webmagic.Request r=new us.codecraft.webmagic.Request();
						r.setUrl(url);
						page.addTargetRequest(r);
					 }
				 }
				 
				 Elements next= page.getHtml().getDocument().getElementsByAttributeValue("class", "font12 pageNext");
				 if(next.size()>0){
					 String nextUrl= next.get(0).parent().attr("href");
					 us.codecraft.webmagic.Request r=new us.codecraft.webmagic.Request();
					 r.setUrl(domainUrl+nextUrl);
					 page.addTargetRequest(r);
				 }
				
			}else if(page.getRequest().getUrl().matches("[\\S]*xiaoyuan.zhaopin.com/job/[\\S]+")){//奖励页面
				System.out.println("---------------------");
				System.out.println("职位名称："+page.getHtml().getDocument().getElementsByAttributeValue("class", "cJobDetailInforTitle").text());
				System.out.println("公司："+page.getHtml().getDocument().getElementById("jobCompany").text().replaceAll("<[\\s\\S]*.?>", ""));
				System.out.println("---------------------");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Site getSite() {
		
		return site;
	}
	
	public static void main(String args[]){
		String url="https://xiaoyuan.zhaopin.com/full/0/0_0_0_0_0_-1_0_2_0";
		System.out.println(url.matches("https://xiaoyuan.zhaopin.com/full/0/.*"));
	}

}
