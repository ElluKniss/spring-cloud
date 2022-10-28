package com.dw.domain;

import lombok.Data;

@Data
public class Order {

    private String orderID;

    private String userID;

    private String createTime;

    private String pay;

    private User user;

}
