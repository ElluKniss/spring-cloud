package com.dw.controller;

import com.dw.domain.Purchase;
import com.dw.domain.vo.*;
import com.dw.eum.ResultCode;
import com.dw.service.PurchaseService;
import com.dw.service.StockService;
import com.dw.util.LocalDateTimeUtil;
import com.dw.util.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @Resource
    private StockService stockService;

    @RequestMapping("/list")
    public PurchaseListResp queryAll(@RequestBody PurchaseQuery purchaseReq) {
        return purchaseService.queryAll(purchaseReq);
    }

    /**
     * 采购入库orderType=1
     * +销售出库orderType=4
     * @param purchaseVo purchaseVo
     * @return re
     */
    @RequestMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public ResultVo addPurchase(@RequestBody @Validated PurchaseVo purchaseVo) {
        log.info("新增/更新请求：orderType={}, productName={}", purchaseVo.getOrderType(), purchaseVo.getProductName());
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(purchaseVo, purchase);
        if (purchase.getId() > 0) {
            log.info("更新采购单：id={}", purchase.getId());
            return new ResultVo(purchaseService.updateById(purchase));
        } else {
            // 入参未带orderId时，系统生成：时间戳 + 4位随机数
            String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
            purchase.setOrderId(timestamp + OrderUtil.getNextSeq());
            boolean saved = purchaseService.save(purchase);
            if (!saved) {
                log.error("采购单保存失败：orderId={}", purchase.getOrderId());
                return new ResultVo(false);
            }
            log.info("采购单保存成功：orderId={}, orderType={}", purchase.getOrderId(), purchase.getOrderType());
            // orderType=1 时，将产品列表入库到 t_stock_summary
            purchaseService.operateStock(purchaseVo.getProductList(), purchaseVo.getDate(), purchaseVo.getOrderType());
            return new ResultVo(true);
        }
    }

    private static LocalDateTime parseDate(String date) {
        return LocalDateTimeUtil.getLocalDateTime(date);
    }

    @RequestMapping("/delete")
    public ResultVo deleteRecord(@RequestBody DeleteReq req) {
        boolean success = purchaseService.deleteRecord(req);
        if (success) {
            return new ResultVo(ResultCode.SUCCESS, "删除成功");
        } else {
            return new ResultVo(ResultCode.FAILED, null);
        }
    }
}
