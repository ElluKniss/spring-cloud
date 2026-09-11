package com.dw.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName(value = "t_finish", autoResultMap = true)
public class FinishedProduct {

    @TableId(value = "id",type = IdType.AUTO)
    private long id;

    @TableField("orderid")
    // 订单号
    private String orderId;

    // 编号
    @TableField("noid")
    private String noId;

    // 入库-出库
    private String type;

    @TableField("date")
    private String date;


    @TableField("productname")
    private String productName;

    private String model;

    // 入库数量
    @TableField("countin")
    private int count;

    private String remark;

    private String jbr;

    // 插入时不传值，用数据库默认
    @TableField(value = "create_time", insertStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
