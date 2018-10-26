package jlspider;

import java.util.List;

import us.codecraft.webmagic.selector.Html;

public class XpathTest {

	static String html="<div id=\"fullSearch\">"+
			    "<div id=\"fullSearchForm\">"+
			        "<div class=\"z_searchBox fl\">"+
			            "<div class=\"city2 z_cityBtn fl\" id=\"z_clickCity\">"+
			               " <input class=\"z_searCityStyle c6\" type=\"button\" id=\"JobLocation2\" name=\"JobLocation2\" title=\"城市\" value=\"城市\">"+
			                "<input type=\"hidden\" id=\"loctitle\" name=\"loctitle\">"+
			                "<input type=\"hidden\" id=\"loc\" name=\"loc\" value=\"0\">"+
			            "</div>"+
			                "<input class=\"z_searchKey fl\" id=\"keyword\" type=\"text\" placeholder=\"输入公司名称或职位名称\" />"+
			            "<input id=\"searchSubmit\" class=\"z_searchBtn  fl cf  __ga__partResultsearchButton_clickpartresultsearchbutton_001\" type=\"submit\" value=\"搜索\">"+
			       " </div>"+
			    "</div>"+
			"</div>";
	public static void main(String[] args) {
		Html htmld=new Html(html);
		List<String> list=htmld.xpath("//div[last()]").all();
		for(String s:list){
			System.out.println("结果"+s);
		}
		
	}
}
