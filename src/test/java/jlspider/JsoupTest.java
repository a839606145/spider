package jlspider;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import us.codecraft.webmagic.selector.Html;

public class JsoupTest {
	static String html="<div id=\"fullSearch\">"+
		    "<div id=\"fullSearchForm\">"+
		        "<div class=\"z_searchBox fl\">"+
		            "<div class=\"city2 z_cityBtn fl\" id=\"z_clickCity\">"+
		               " <input class=\"z_searCityStyle c6\" type=\"button\" id=\"JobLocation2\" name=\"JobLocation2\" title=\"城市\" value=\"城市\">"+
		                "<input type=\"hidden\" id=\"loctitle\" name=\"loctitle\">"+
		                "<input type=\"hidden\" id=\"loc\" name=\"loc\" value=\"0\">"+
		            "</div>"+
		                "<p>hhhhhh</p>"+
		                "<input class=\"z_searchKey fl\" id=\"keyword\" type=\"text\" placeholder=\"输入公司名称或职位名称\" />"+
		            "<input id=\"searchSubmit\" class=\"z_searchBtn  fl cf  __ga__partResultsearchButton_clickpartresultsearchbutton_001\" type=\"submit\" value=\"搜索\">"+
		       " </div>"+
		    "</div>"+
		"</div>";
	public static void main(String[] args) {
		Document doc=Jsoup.parse(html);
		System.out.println(Jsoup.clean(html, new Whitelist().addTags("p")));
		
	}
}
