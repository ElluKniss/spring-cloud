package com.dw.controller;

import com.dw.domain.Purchase;
import com.dw.domain.com.dw.domain.vo.PurchaseListResp;
import com.dw.domain.com.dw.domain.vo.PurchaseQuery;
import com.dw.domain.com.dw.domain.vo.PurchaseVo;
import com.dw.domain.com.dw.domain.vo.ResultVo;
import com.dw.service.PurchaseService;
import com.dw.util.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @RequestMapping("/list")
    public PurchaseListResp queryAll(@RequestBody PurchaseQuery purchaseReq) {
        return purchaseService.queryAll(purchaseReq);
    }

    @RequestMapping("/add")
    public ResultVo addPurchase(@RequestBody @Validated PurchaseVo purchaseVo) {
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(purchaseVo, purchase);
        // 入参未带orderId时，系统生成：时间戳 + 4位随机数
        if (StringUtils.isBlank(purchase.getOrderId())) {
            String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
            purchase.setOrderId(timestamp + OrderUtil.getNextSeq());
        }
        if (purchase.getId() > 0) {
            return new ResultVo(purchaseService.updateById(purchase));
        } else {
            return new ResultVo(purchaseService.save(purchase));
        }
    }
}
