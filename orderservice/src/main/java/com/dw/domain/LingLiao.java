package com.dw.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "t_lingliao", autoResultMap = true)
public class LingLiao {

    @TableId(value = "id",type = IdType.AUTO)
    private long id;

    @TableField("orderid")
    @ExcelProperty(value = "订单号", index = 7)
    private String orderId;

    private String date;

    private String dept;

    private String remark;

    private String jbr;

    // 插入时不传值，用数据库默认
    @TableField(value = "create_time", insertStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    // 使用MyBatis-Plus的Jackson处理器
    @TableField(value = "productlist", typeHandler = JacksonTypeHandler.class)
    private List<Product> productList;

}
