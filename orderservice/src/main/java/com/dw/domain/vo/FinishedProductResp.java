package com.dw.domain.vo;

import com.dw.domain.FinishedProduct;
import com.dw.domain.Purchase;
import lombok.Data;

import java.util.List;

@Data
public class FinishedProductResp {

    private long total;

    private List<FinishedProduct> infoList;

    public FinishedProductResp(List<FinishedProduct> list, long total) {
        this.total = total;
        this.infoList = list;
    }
}
