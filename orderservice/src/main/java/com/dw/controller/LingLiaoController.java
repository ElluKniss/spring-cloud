package com.dw.controller;

import com.dw.domain.LingLiao;
import com.dw.domain.Product;
import com.dw.domain.vo.*;
import com.dw.eum.ResultCode;
import com.dw.service.LingService;
import com.dw.service.PurchaseService;
import com.dw.service.StockService;
import com.dw.util.LocalDateTimeUtil;
import com.dw.util.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;



@Slf4j
@RestController
@RequestMapping("/purchase")
public class LingLiaoController {

    @Resource
    private LingService lingService;

    @Resource
    private PurchaseService purchaseService;

    @Resource
    private StockService stockService;

    @RequestMapping("/ling/list")
    public LingQueryResp queryAll(@RequestBody PurchaseQuery purchaseReq) {
        return lingService.queryAll(purchaseReq);
    }

    @RequestMapping("/ling/add")
    @Transactional(rollbackFor = Exception.class)
    public ResultVo addLing(@RequestBody @Validated LingLiaoVo lingLiaoVo){
        log.info("领料单新增/更新请求：dept={}, date={}", lingLiaoVo.getDept(), lingLiaoVo.getDate());
        LingLiao lingLiao = new LingLiao();
        BeanUtils.copyProperties(lingLiaoVo, lingLiao);
        String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        lingLiao.setOrderId(timestamp + OrderUtil.getNextSeq());
        if (lingLiao.getId()>0) {
            log.info("更新领料单：id={}", lingLiao.getId());
            return new ResultVo(lingService.updateById(lingLiao));
        }else {
            boolean saved = lingService.save(lingLiao);
            if (!saved) {
                log.error("领料单保存失败：orderId={}", lingLiao.getOrderId());
                return new ResultVo(false);
            }
            log.info("领料单保存成功：orderId={}", lingLiao.getOrderId());
            // 领料时，将产品列表从 t_stock_summary 扣减库存
            addOutStock(lingLiaoVo.getProductList(), lingLiaoVo.getDate());
            return new ResultVo(true);
        }
    }

    /**
     * 遍历 productList，逐条扣减库存
     */
    private void addOutStock(List<Product> productList, String date) {
        if (productList == null || productList.isEmpty()) {
            log.warn("领料单产品列表为空，跳过出库处理");
            return;
        }
        LocalDateTime outDate = parseDate(date);
        int count = 0;
        for (Product product : productList) {
            if (StringUtils.isBlank(product.getProductName()) || product.getCount() == null) {
                continue;
            }
            stockService.addOutStock(
                    product.getProductName(),
                    product.getModel(),
                    product.getCount(),
                    outDate
            );
            count++;
        }
        log.info("领料出库处理完成，共出库 {} 条产品明细", count);
    }

    private static LocalDateTime parseDate(String date) {
        return LocalDateTimeUtil.getLocalDateTime(date);
    }

    @GetMapping("/ling/price")
    public ResultVo queryPrice(@RequestParam String productName, @RequestParam String model){
        BigDecimal bigDecimal = purchaseService.queryPrice(productName, model);

        return new ResultVo(bigDecimal);
    }

    @RequestMapping("/ling/delete")
    public ResultVo delete(@RequestBody DeleteReq req) {
        boolean success = lingService.deleteRecord(req);
        if (success) {
            return new ResultVo(ResultCode.SUCCESS, "删除成功");
        } else {
            return new ResultVo(ResultCode.FAILED, null);
        }
    }

}
