package com.dw.domain.vo;

import com.dw.domain.Purchase;
import com.dw.domain.StockSummary;
import lombok.Data;

import java.util.List;

@Data
public class StockListResp {

    private long total;

    private List<StockSummary> infoList;

    public StockListResp(List<StockSummary> list, long total) {
        this.total = total;
        this.infoList = list;
    }
}
