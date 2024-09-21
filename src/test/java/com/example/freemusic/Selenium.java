package com.example.freemusic;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
    @Test
    public void bilibili(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://read.douban.com/reader/column/4639817/chapter/24562734/?dcs=column&dcm=chapter-list");

        //获取文章标题
        WebElement titleContentElement = driver.findElement(By.xpath("//div[@class='chapter-header']/h1"));
        String titleContent = titleContentElement.getText();
        System.out.println(titleContent);

        //获取文章内容（此div包含标题）
        WebElement articleContentElement = driver.findElement(By.xpath("//div[@class='article']"));
        String articleContent = articleContentElement.getText();
        System.out.println(articleContent);

        //关闭浏览器
        driver.close();
    }
}
