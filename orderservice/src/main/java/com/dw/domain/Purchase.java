package com.dw.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "t_purchase", autoResultMap = true)
public class Purchase {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("orderid")
    @ExcelProperty(value = "订单号", index = 7)
    private String orderId;

    @TableField("noid")
    private String noId;

    private String type;

    @TableField("date")
    @ExcelProperty(value = "日期", index = 9)
    private String date;

    @ExcelProperty(value = "供应商", index = 1)
    private String gys;

    @ExcelProperty(value = "部门", index = 0)
    private String dept;

    @TableField("productname")
    @ExcelProperty(value = "产品名称", index = 2)
    private String productName;

    @ExcelProperty(value = "数量", index = 5)
    private String count;

    @ExcelProperty(value = "单位", index = 4)
    private String dan;

    @ExcelProperty(value = "型号", index = 3)
    private String model;

    @TableField("unitprice")
    @ExcelProperty(value = "单价", index = 6)
    private String unitPrice;

    @TableField("totalprice")
    private String totalPrice;

    @TableField("taxrate")
    private int taxRate;

    @TableField("ordertype")
    @ExcelProperty(value = "orderType", index = 8)
    private String orderType;

    private String remark;

    private String jbr;
}
