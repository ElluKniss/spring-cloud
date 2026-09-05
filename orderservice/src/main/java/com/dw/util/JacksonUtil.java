package com.dw.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JacksonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 忽略未知字段
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将对象转为JSON字符串
     */
    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON序列化失败", e);
        }
    }

    /**
     * 将JSON字符串转为指定类型的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON反序列化失败", e);
        }
    }

    /**
     * 将JSON字符串转为List集合
     */
    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json,
                    new TypeReference<List<T>>() {});
        } catch (Exception e) {
            throw new RuntimeException("JSON反序列化失败", e);
        }
    }
}
