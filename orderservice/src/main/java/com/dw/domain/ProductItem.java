package com.dw.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductItem {
    private String productName;
    private String model;
    private String unit;
    private BigDecimal count;
    private BigDecimal price;
}