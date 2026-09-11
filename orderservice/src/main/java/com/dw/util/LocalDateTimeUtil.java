package com.dw.util;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    public static LocalDateTime getLocalDateTime(String date) {
        if (StringUtils.isBlank(date)) {
            return LocalDateTime.now();
        }
        try {
            if (date.length() <= 10) {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
            }
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }

    public static LocalDateTime currentTime(){
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String str = LocalDateTime.now().format(fmt);
        LocalDateTime now = LocalDateTime.now();
        return now;

    }
}
