package com.dw.util;

import com.dw.mapper.HystrixFallbackRecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class HystrixUtil {

    @Resource
    private HystrixFallbackRecordMapper mapper;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public void addHystrixRecord(String methodSign, String url, String content,
                                 String header, String encodeType) {
        executorService.execute(() -> insert(methodSign, url, content,
                null, null));
    }

    private void insert(String methodSign, String url, String content, Object header, Object encodeType) {

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("platform", "wohome");
        hashMap.put("methodSign", methodSign);
        hashMap.put("url", url);
        hashMap.put("content", content);
        hashMap.put("header", header);
        hashMap.put("encodeType", encodeType);
        hashMap.put(
                "fallbackTime",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        mapper.insert(hashMap);
    }

}
