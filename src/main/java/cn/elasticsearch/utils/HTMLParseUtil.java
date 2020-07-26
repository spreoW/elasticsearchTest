package cn.elasticsearch.utils;

import cn.elasticsearch.pojo.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTMLParseUtil {
    public static void main(String[] args) throws Exception {
        List<Goods> parse = new HTMLParseUtil().parse("C++");
        System.out.println(parse);
    }
    public List<Goods> parse(String keyword) throws Exception {
        String url = "https://search.jd.com/Search?keyword="+keyword;
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        List<Goods> goodsList = new ArrayList<>();
        for (Element el : elements){
            String image = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            if (image != null && image.length() != 0){
                Goods goods = new Goods();
                goods.setImage(image);
                goods.setPrice(price);
                goods.setTitle(title);
                goodsList.add(goods);
            }
        }
        return goodsList;
    }
}
