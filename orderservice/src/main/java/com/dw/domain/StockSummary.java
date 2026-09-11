package com.dw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("t_stock_summary")
public class StockSummary {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String productName;

    private String model;

    private String unit;

    private BigDecimal totalIn;

    private BigDecimal totalOut;

    private BigDecimal currentStock;

    private LocalDateTime lastInDate;

    private LocalDateTime lastOutDate;

    @Version
    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}