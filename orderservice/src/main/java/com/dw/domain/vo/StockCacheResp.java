package com.dw.domain.vo;

import com.dw.domain.StockSummary;
import lombok.Data;

import java.util.List;

@Data
public class StockCacheResp {

    private List<StockSummary> infoList;

    public StockCacheResp(List<StockSummary> list) {
        this.infoList = list;
    }
}
