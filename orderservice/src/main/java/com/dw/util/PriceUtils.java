package com.dw.util;

import java.math.BigDecimal;

public class PriceUtils {

    /**
     * 安全转换为 BigDecimal
     */
    public static BigDecimal toBigDecimal(Object value) {
        if (value == null) {
            return null;
        }

        String str = value.toString().trim();
        if (str.isEmpty()) {
            return null;
        }

        // 去掉逗号
        str = str.replace(",", "");

        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
