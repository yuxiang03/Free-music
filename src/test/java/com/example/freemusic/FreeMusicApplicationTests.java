package com.example.freemusic;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.Utils.*;
import com.example.Vo.*;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FreeMusicApplicationTests {

    @Resource
    private FoodMapper foodMapper;
    @Resource
    private Food2Mapper food2Mapper;
    @Resource
    private MealMapper mealMapper;
    @Resource
    private DetailMapper detailMapper;
    @Resource
    private MealMapper1 mealMapper1;

    @Test
    public void s() throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://home.meishichina.com/recipe/recai/page/1/");
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        Document doc = Jsoup.parse(result);
        Elements elements1 = doc.getElementsByClass("pic");
        Elements elements2 = doc.getElementsByClass("subcontent");
        List<Detail> list = new ArrayList();
        for (Element e:elements1){
            Elements children = e.children();
            Attributes attributes = children.first().attributes();
            Attributes img = children.select("img").first().attributes();
            Detail detail = new Detail();
            detail.setHref(attributes.get("href"));
            detail.setTitle(attributes.get("title"));
            detail.setImage(img.get("data-src"));
            list.add(detail);
        }
        List<String> list1 = new ArrayList();
        for (Element e:elements2)
            list1.add(e.text());
        for (int i = 0; i < list.size(); i++) {
            Detail detail = new Detail();
            detail.setImage(list.get(i).getImage());
            detail.setSubcontent(list1.get(i));
            detail.setHref(list.get(i).getHref());
            detail.setTitle(list.get(i).getTitle());
            detail.setMealId(1765679047993569281L);
            detailMapper.insert(detail);
        }
    }

    @Test
    public void test() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Food> foods = foodMapper.selectList(queryWrapper);
        System.out.println(foods.get(0).getUrl());
    }

    @SneakyThrows
    @Test
    public void r() {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://www.meishichina.com/YuanLiao/JiDan/yiji/");
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        Document doc = Jsoup.parse(result);
        Elements element = doc.getElementsByClass("mbox yiji");
        Elements div_a = element.select("div a");
        Elements li_p = element.select("li p");
        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        for (Element e : div_a) {
            if (!e.text().equals("查看菜谱"))
                list1.add(e.text());
        }
        for (Element e : li_p) {
            list2.add(e.text());
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < list1.size(); i++) {
            map.put(list1.get(i), list2.get(i));
        }
        Object s = JSON.toJSON(map);
        Food food = foodMapper.selectList(null).get(1);
        FoodInfo info = new FoodInfo();
        info.setId(food.getId());
        info.setFoodName(food.getFoodName());
        info.setSortId(food.getSortId());
        info.setFoodInfo(s.toString());
        food2Mapper.insert(info);
    }

    @SneakyThrows
    public void ss(Meal meal) {
        int m = 1;
        CloseableHttpClient client = HttpClients.createDefault();
        while (true) {
            String url = meal.getUrl()+"page/"+(++m)+"/";
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            val statusCode = response.getStatusLine().getStatusCode();
            if (statusCode!=200){
                System.out.println(response.getStatusLine());
                meal.setUrl(url);
                mealMapper1.insert(meal);
                return;
            }
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            Document doc = Jsoup.parse(result);
            Elements elements1 = doc.getElementsByClass("pic");
            Elements elements2 = doc.getElementsByClass("subcontent");
            List<Detail> list = new ArrayList();
            for (Element e:elements1){
                Elements children = e.children();
                Attributes attributes = children.first().attributes();
                Attributes img = children.select("img").first().attributes();
                Detail detail = new Detail();
                detail.setHref(attributes.get("href"));
                detail.setTitle(attributes.get("title"));
                detail.setImage(img.get("data-src"));
                list.add(detail);
            }
            List<String> list1 = new ArrayList();
            for (Element e:elements2)
                list1.add(e.text());
            for (int i = 0; i < list.size(); i++) {
                Detail detail = new Detail();
                detail.setImage(list.get(i).getImage());
                detail.setSubcontent(list1.get(i));
                detail.setHref(list.get(i).getHref());
                detail.setTitle(list.get(i).getTitle());
                detail.setMealId(meal.getId());
                detailMapper.insert(detail);
            }
            Thread.sleep(1000);
            System.out.println("------------------");
        }
    }

    @Test
    public void fina() {
        /*List<Meal> meals = mealMapper.selectList(null);
        for (int i = 0; i < meals.size(); i++) {
            ss(meals.get(i));
        }*/
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        canCompleteCircuit(gas, cost);
    }

    @SneakyThrows
    @Test
    public void dd(){
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://b.faloo.com/y_0_1.html");
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        Document doc = Jsoup.parse(result);
        Elements twoBox02 = doc.getElementsByClass("TwoBox02");
        Elements div_h1 = twoBox02.select("div h1");
        List<String> list = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        for (Element e:div_h1) {
            e.attr("a");
            list.add(e.text());
        }
        System.out.println(list);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for(int i = 0;i<len;i++) {
            int j = i + 1;
            int count = gas[i];
            while(gas[i] >= cost[i]) {
                count += gas[j%len] - cost[i];
                j %= len;
                if (j == i) return i;
            }
        }
        return -1;
    }
}
