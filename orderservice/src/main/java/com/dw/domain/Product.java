package com.dw.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Product {

    @ExcelProperty(value = "项目名称")
    private String productName;

    @ExcelProperty(value = "规格型号")
    private String model;

    @ExcelProperty(value = "单位")
    private String unit;

    @ExcelProperty(value = "数量")
    private int count;
    /**
     * 含税单价
     */
    @ExcelProperty(value = "含税单价")
    private String price;



    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private double amount;

    @ExcelProperty(value = "发货日期")
    private String taxRate;

}
