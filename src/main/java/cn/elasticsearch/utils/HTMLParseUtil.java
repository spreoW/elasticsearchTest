package cn.elasticsearch.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class HTMLParseUtil {
    public static void main(String[] args) throws Exception {
        String url = "https://search.jd.com/Search?keyword=java";
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        for (Element el : elements){
            String image = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            System.out.println("===========================");
            System.out.println(image);
            System.out.println(price);
            System.out.println(title);
        }

    }
}
