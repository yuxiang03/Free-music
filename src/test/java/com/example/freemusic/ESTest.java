package com.example.freemusic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.example.esInfo.MAPPING_TEMPLATE;

//@SpringBootTest
public class ESTest {
    private RestHighLevelClient client;

    @Test
    void test(){
        System.out.println(client);
    }

    @BeforeEach
    void setUp(){
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://101.126.93.113:9200")
        ));
    }

    @AfterEach
    void clientDown() throws IOException {
        this.client.close();
    }

    @Test
    void createTest() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("jkdp");
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    void delectTest() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("jkdp");
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void existTest() throws IOException {
        GetIndexRequest request = new GetIndexRequest("jkdp");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
}
