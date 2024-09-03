package com.dw.domain.com.dw.domain.vo;

import com.dw.domain.OrderInfo;
import lombok.Data;

import java.util.List;

@Data
public class OrderListResp {

    private long total;

    private List<OrderInfo> infoList;

    public OrderListResp(List<OrderInfo> list, long total) {
        this.total = total;
        this.infoList = list;
    }
}
