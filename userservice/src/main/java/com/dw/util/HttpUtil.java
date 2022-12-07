package com.dw.util;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@Slf4j
public class HttpUtil {

    static final OkHttpClient client = new OkHttpClient();

    static public String doGet(String url){

        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().toString();
        } catch (Exception e) {
            log.error("http request error");
            throw new RuntimeException(e);
        }
    }

    static public String doPost(String url, String body){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        Request request = new Request.Builder().url(url).post(RequestBody.create(body, mediaType)).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error("http request error");
            System.out.println("http request error");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JSONObject header = new JSONObject();
        header.put("channel", "1001000001");
        header.put("key", "QueryCloudUsageInfo");

        JSONObject reqBody = new JSONObject();
        String deBody = "lHqiVSo9SCmVS/qJyGbVukyfsHAZo/gGshv5x75MTn88px/W+DtV+Iaj/AZ5LNvfXLCK07p9gz8xXhDFKjAhvQ==";
        reqBody.put("header", header);
        reqBody.put("body", deBody);

        String s = doPost("http://120.52.8.241/woapi/dispatcher", reqBody.toString());
        System.out.println(s);
    }
}
