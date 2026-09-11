package com.dw.service;

import com.dw.domain.StockSummary;
import com.dw.exception.BusinessException;
import com.dw.mapper.StockSummaryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
public class StockService {

    @Resource
    private StockSummaryMapper stockSummaryMapper;

    /**
     * 入库时增加库存
     */
    public void addInStock(String productName, String model, BigDecimal quantity,
                           String unit, LocalDateTime inDate) {
        log.info("入库操作开始：productName={}, model={}, quantity={}, unit={}", productName, model, quantity, unit);
        StockSummary stock = getStockInfo(productName, model);
        if (null == stock) {
            stock = new StockSummary();
            stock.setProductName(productName);
            stock.setModel(model);
            stock.setUnit(unit);
            stock.setTotalIn(quantity);
            stock.setTotalOut(BigDecimal.ZERO);
            stock.setCurrentStock(quantity);
            stock.setLastInDate(inDate);
            stockSummaryMapper.insert(stock);
            log.info("新建库存记录成功：productName={}, model={}, currentStock={}", productName, model, quantity);
        } else {
            stock.setTotalIn(stock.getTotalIn().add(quantity));
            stock.setCurrentStock(stock.getCurrentStock().add(quantity));
            stock.setLastInDate(inDate);
            if (unit != null) {
                stock.setUnit(unit);
            }

            int updated = stockSummaryMapper.updateByIdWithVersion(stock);
            if (updated == 0) {
                log.warn("库存更新失败（乐观锁冲突）：productName={}, model={}", productName, model);
                throw new BusinessException("库存更新失败，请重试");
            }
            log.info("库存累加成功：productName={}, model={}, currentStock={}", productName, model, stock.getCurrentStock());
        }
    }

    /**
     * 领用时减少库存
     */
    public void addOutStock(String productName, String model, BigDecimal quantity,
                            LocalDateTime outDate) {
        log.info("出库操作开始：productName={}, model={}, quantity={}", productName, model, quantity);
        StockSummary stock = getStockInfo(productName, model);
        if (null == stock) {
            log.warn("库存不存在：productName={}, model={}", productName, model);
            throw new BusinessException("库存不存在：" + productName + " " + model);
        }

        if (stock.getCurrentStock().compareTo(quantity) < 0) {
            log.warn("库存不足：productName={}, model={}, 当前库存={}, 出库数量={}", productName, model, stock.getCurrentStock(), quantity);
            throw new BusinessException("库存不足，当前库存：" + stock.getCurrentStock() + " " + stock.getUnit());
        }

        stock.setTotalOut(stock.getTotalOut().add(quantity));
        stock.setCurrentStock(stock.getCurrentStock().subtract(quantity));
        stock.setLastOutDate(outDate);

        int updated = stockSummaryMapper.updateByIdWithVersion(stock);
        if (updated == 0) {
            log.warn("库存扣减失败（乐观锁冲突）：productName={}, model={}", productName, model);
            throw new BusinessException("库存更新失败，请重试");
        }
        log.info("库存扣减成功：productName={}, model={}, currentStock={}", productName, model, stock.getCurrentStock());
    }

    /**
     * 查询完整库存信息
     */
    public StockSummary getStockInfo(String productName, String model) {
        return stockSummaryMapper.selectByProductNameAndModel(productName, model);
    }
}
