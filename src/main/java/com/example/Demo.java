package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class Demo implements PageProcessor {
    @Override
    public void process(Page page) {
        /*Document doc = Jsoup.parse(page.getHtml().xpath("//h1[@class='fontSize17andHei']/a").all().toString());
        final Elements allElements = doc.getAllElements();
        final Elements a = allElements.select("a");
        //System.out.println(a);
        List<Book> books = new ArrayList<>();
        for (Element e:a) {
            Book book = new Book();
            book.setTitle(e.text());
            book.setUrl(e.attributes().get("href"));
            books.add(book);
        }
        Document docu = Jsoup.parse(page.getHtml().xpath("//div[@class='TwoBox02_06']/a").all().toString());
        System.out.println(books);*/
        Document doc = Jsoup.parse(page.getHtml().xpath("div[@class='pageliste_body']/a").all().toString());
        final Elements a = doc.select("a");
        for (Element e:a) {
            if (e.text().equals("下一页"))
                System.out.println(e.attributes().get("href"));
        }
    }

    public static void main(String[] args) {
        //for (int i = 1;i<5;i++)
        //Spider.create(new Demo()).addUrl("https://b.faloo.com/y_0_"+i+".html").run();
        //Spider.create(new Demo()).addUrl("https://b.faloo.com/y_0_1.html").run();
        jump(new int[]{2,3,1,2,4,1,3});
    }

    public static int jump(int[] nums) {
        int end = 0, p = 0, res = 0;
        for(int i = 0;i<nums.length-1;i++){
            p = Math.max(p,nums[i]+i);
            if(i==end){
                end = p;
                res++;
            }
        }
        return res;
    }
}

