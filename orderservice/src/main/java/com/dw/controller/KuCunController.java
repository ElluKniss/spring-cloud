package com.dw.controller;

import com.dw.domain.vo.KuCunQuery;
import com.dw.domain.vo.StockCacheResp;
import com.dw.domain.vo.StockListResp;
import com.dw.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/kucun")
public class KuCunController {

    @Resource
    private PurchaseService purchaseService;

    @RequestMapping("/list")
    public StockListResp queryAll(@RequestBody KuCunQuery kuCunQuery) {

        return purchaseService.queryKuCun(kuCunQuery);
    }

    @RequestMapping("/ling")
    public StockCacheResp queryKuCun(@RequestBody KuCunQuery kuCunQuery) {
        return purchaseService.queryKuCunFromLing(kuCunQuery);
    }
}
