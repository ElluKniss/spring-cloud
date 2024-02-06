package com.dw.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {

    static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(100, TimeUnit.SECONDS).
            callTimeout(10, TimeUnit.SECONDS).build();

    static public String doGet(String url){

        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error("http request error");
            throw new RuntimeException(e);
        }
    }

    static public String doPost(String url, String body){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        Request request = new Request.Builder().url(url).
                addHeader("Authorization", Credentials.basic("dw","21")).
                post(RequestBody.create(body, mediaType)).build();
        try {
            Response response = client.newCall(request).execute();
            Assert.hasLength(response.body().string(),"resp is empty" );
            return response.body().string();
        } catch (Exception e) {
            log.error("http request error");
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用MultipartBody上传多个文件
     * RequestBody.create用于接收MediaType.parse
     * MediaType.parse(“text/plain”) 指定文件上传的类型
     * Request 创建请求对象
     * url 参数为域名
     * post(multipartBody) 参数是需要上传的文件和类型
     * Builder 构建器
     * okHttpClient.newCall(request); 获取Call（准备好请求的对象） 参数是：Request 请求对象
     * Call 准备好请求的对象
     * call.enqueue 异步请求
     * @param url 上传url
     * @param file 文件
     */
    static public void doUpload(String url, File file){
        MultipartBody multipartBody = new MultipartBody.Builder().
                addFormDataPart("file", file.getName(), RequestBody.create(file, MediaType.parse("multipart/form-data"))).
                build();
        Request build = new Request.Builder().url(url).post(multipartBody).build();
        Call call = client.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("upload fail : {}", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Assert.hasLength(response.body().string(),"resp is empty" );
                    log.info(response.body().string());
                }
            }
        });
    }

}
