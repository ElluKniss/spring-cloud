package com.dw.domain.vo;

import lombok.Data;

@Data
public class KuCunQuery {

    private String gys;

    private String productName;

    private String model;

    private String orderType;

    private int current;

    private int size;

    private String startDate;

    private String endDate;

    private String type;
}
