package com.dw.controller;

import com.dw.domain.com.dw.domain.vo.KuCunQuery;
import com.dw.domain.com.dw.domain.vo.PurchaseListResp;
import com.dw.domain.com.dw.domain.vo.PurchaseQuery;
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
    public PurchaseListResp queryAll(@RequestBody KuCunQuery kuCunQuery) {

        return purchaseService.queryKuCun(kuCunQuery);
    }
}
