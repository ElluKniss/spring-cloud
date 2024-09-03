package com.dw.domain.com.dw.domain.vo;

import com.dw.domain.OrderInfo;
import com.dw.domain.SellInfo;
import lombok.Data;

import java.util.List;

@Data
public class SellListResp {

    private long total;

    private List<SellInfo> infoList;

    public SellListResp(List<SellInfo> list, long total) {
        this.total = total;
        this.infoList = list;
    }
}
