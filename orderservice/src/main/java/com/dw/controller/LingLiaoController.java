package com.dw.controller;

import com.dw.domain.LingLiao;
import com.dw.domain.SellInfo;
import com.dw.domain.com.dw.domain.vo.*;
import com.dw.service.LingService;
import com.dw.service.PurchaseService;
import com.dw.util.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class LingLiaoController {

    @Resource
    private LingService lingService;

    @Resource
    private PurchaseService purchaseService;

    @RequestMapping("/ling/list")
    public LingQueryResp queryAll(@RequestBody PurchaseQuery purchaseReq) {
        return lingService.queryAll(purchaseReq);
    }

    @RequestMapping("/ling/add")
    public ResultVo addLing(@RequestBody @Validated LingLiaoVo lingLiaoVo){
        LingLiao lingLiao = new LingLiao();
        BeanUtils.copyProperties(lingLiaoVo, lingLiao);
        String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        lingLiao.setOrderId(timestamp + OrderUtil.getNextSeq());
        if (lingLiao.getId()>0) {
            return new ResultVo(lingService.updateById(lingLiao));
        }else {
            return new ResultVo(lingService.save(lingLiao));
        }
    }

    @GetMapping("/ling/price")
    public ResultVo queryPrice(@RequestParam String productName, @RequestParam String model){
        BigDecimal bigDecimal = purchaseService.queryPrice(productName, model);

        return new ResultVo(bigDecimal);
    }


}
