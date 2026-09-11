package com.dw.controller;

import com.dw.domain.FinishedProduct;
import com.dw.domain.Product;
import com.dw.domain.Purchase;
import com.dw.domain.vo.*;
import com.dw.service.FinishedProductService;
import com.dw.service.StockService;
import com.dw.util.LocalDateTimeUtil;
import com.dw.util.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chengpin")
public class ChengPinController {

    @Autowired
    private FinishedProductService finishedProductService;

    @Resource
    private StockService stockService;
    /**
     *  查询所有成品
     */
    @RequestMapping("/list")
    FinishedProductResp qyeryAll(@RequestBody PurchaseQuery purchaseReq){

        return finishedProductService.queryAll(purchaseReq);
    }

    @RequestMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public ResultVo addFinished(@RequestBody @Validated FinishedVo finishedVo) {

        log.info("成品入库单新增请求：orderType={}, date={}", finishedVo.getType(), finishedVo.getDate());
        FinishedProduct finished = new FinishedProduct();
        BeanUtils.copyProperties(finishedVo, finished);
        // 入参未带orderId时，系统生成：时间戳 + 4位随机数
        if (StringUtils.isBlank(finished.getOrderId())) {
            String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
            finished.setOrderId(timestamp + OrderUtil.getNextSeq());
        }
        if (finished.getId() > 0) {
            log.info("更新采购单：id={}", finished.getId());
            return new ResultVo(finishedProductService.updateById(finished));
        } else {
            boolean saved = finishedProductService.save(finished);
            if (!saved) {
                log.error("采购单保存失败：product={}, model={}", finished.getProductName(), finished.getModel());
                return new ResultVo(false);
            }

            addInStock(finished);
            return new ResultVo(true);
        }
    }
    /**
     *
     */
    /**
     * 遍历 productList，逐条入库
     */
    private void addInStock(FinishedProduct finishedProduct) {
        if (finishedProduct == null) {
            log.warn("采购单产品列表为空，跳过入库处理");
            return;
        }
        LocalDateTime inDate = LocalDateTimeUtil.getLocalDateTime(finishedProduct.getDate());


        if (StringUtils.isBlank(finishedProduct.getProductName()) || finishedProduct.getModel() == null) {
            log.warn("成品列表为空，跳过入库处理");
        }
        stockService.addInStock(
                finishedProduct.getProductName(),
                finishedProduct.getModel(),
                new BigDecimal(finishedProduct.getCount()),
                "",
                inDate
        );
        log.info("成品入库处理完成");
    }
}
