package com.example.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.Service.SearchService;
import com.example.Utils.*;
import com.example.Vo.Params.SearchParams;
import com.example.Vo.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public Result search(SearchParams searchParams) {
        StringBuilder sb = new StringBuilder("https://api.hamm.cn/song/search?keyword=");
        sb.append(searchParams.getMusicName());
        sb.append("&page=1");
        try {
            List<ResInfo> k = apacheHttpClient(String.valueOf(sb));
            return Result.success(k);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail(509, "SearchServiceImpl.34");
    }

    private static List<ResInfo> apacheHttpClient(String url) throws Exception {
        //InputStream is = null;
        SongInfo songInfo = JSON.parseObject(EntityUtils.toString(httpResponse(url)), SongInfo.class);
        List<ListInfo> listInfo = songInfo.getData().getList();
        List<ResInfo> res = new ArrayList<>();
        for (ListInfo info:listInfo) {
            StringBuilder stringBuilder = new StringBuilder("https://api.hamm.cn/song/play?mid=");
            stringBuilder.append(info.getMid());
            ResInfo resInfo = new ResInfo();
            resInfo.setName(info.getName());
            resInfo.setArtist(info.getArtist());
            resInfo.setDuration(info.getDuration());
            resInfo.setLocal(String.valueOf(stringBuilder));
            res.add(resInfo);
        }
        return res;
    }

    private static HttpEntity httpResponse(String url) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpGet http = new HttpGet(url);
        http.setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        HttpResponse response = client.execute(http);
        HttpEntity entity = response.getEntity();
        return entity;
    }
}
