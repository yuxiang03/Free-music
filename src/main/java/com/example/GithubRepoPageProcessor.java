package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class GithubRepoPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        final List<String> all = page.getHtml().xpath("//h1[@class='fontSize17andHei']/a[@target='_blank']").all();
        //final List<String> all = page.getHtml().xpath("//a[contains(@href, '//b.faloo.com/')]//text()").all();
        // 部分二：定义如何抽取页面信息，并保存下来
        //page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        List<String> all1 = page.getHtml().links().regex("(//b.faloo.com//[0-9]{7,8})").all();
        System.out.println(all);
        /*Document doc = Jsoup.parse(page.getHtml().toString());
        Elements select = doc.select("h1.fontSize17andHei a");
        Elements twoBox02_06 = doc.getElementsByClass("TwoBox02_06").select("a");
        List list = new ArrayList<>();
        for (Element e:select){
            list.add(e.attributes());
        }
        System.out.println(twoBox02_06);*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://b.faloo.com/y_0_1.html")
                //开启5个线程抓取
                //.thread(5)
                //启动爬虫
                .run();
    }
}
