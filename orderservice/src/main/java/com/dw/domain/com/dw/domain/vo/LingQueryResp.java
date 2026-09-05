package com.dw.domain.com.dw.domain.vo;

import com.dw.domain.LingLiao;
import com.dw.domain.Product;
import com.dw.domain.Purchase;
import lombok.Data;

import java.util.List;

@Data
public class LingQueryResp {

    private long total;

    private List<LingLiao> infoList;

    public LingQueryResp(List<LingLiao> list, long total) {
        this.total = total;
        this.infoList = list;
    }
}
