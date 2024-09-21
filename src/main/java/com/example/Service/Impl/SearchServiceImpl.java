package com.example.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.Service.SearchService;
import com.example.Utils.*;
import com.example.Vo.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public Result search(RequestInfo requestInfo) {
        //https://dev.iw233.cn/Music1/
        try {
            SongInfo songInfo = JSON.parseObject(httpResponse(requestInfo), SongInfo.class);
            List<Data> dataInfo = songInfo.getData();
            return Result.success(dataInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail(509, "SearchServiceImpl.34");
    }

    private static String httpResponse(RequestInfo requestInfo) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://dev.iw233.cn/Music1/");
        List<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("input",requestInfo.getInput()));
        list.add(new BasicNameValuePair("filter",requestInfo.getFilter()));
        list.add(new BasicNameValuePair("type",requestInfo.getType()));
        list.add(new BasicNameValuePair("page",requestInfo.getPage()));
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        post.addHeader("Cookie","Hm_lvt_2bfe99c3d9f44e09ca1a2ac5a769294c=1698063594; Hm_lpvt_2bfe99c3d9f44e09ca1a2ac5a769294c=1698063594");
        post.addHeader("X-Requested-With","XMLHttpRequest");
        post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;
    }
}
