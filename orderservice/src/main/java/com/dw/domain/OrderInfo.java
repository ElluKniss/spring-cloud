package com.dw.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_orderinfo")
public class OrderInfo {

    @TableId(value = "id",type = IdType.AUTO)
    @ExcelProperty("序号")
    private Long id;

    @TableField("contractno")
    @ExcelProperty("合同编号")
    private String contractNo;

    @TableField("signDate")
    private String signDate;

    private String company;

    @TableField("productName")
    private String productName;

    @TableField("model")
    private String model;

    @TableField("count")
    private String count;

    @TableField("unitPrice")
    private String unitPrice;

    @TableField("amount")
    private String amount;

    @TableField("jiaohuoDate")
    private String jiaohuoDate;

    @TableField("kaiGuan")
    private String kaiGuan;

    @TableField("fahuoDate")
    private String fahuoDate;

    @TableField("fahuoCount")
    private String fahuoCount;

    @TableField("fahuoAmount")
    private String fahuoAmount;

    @TableField("kaipiaoDate")
    private String kaipiaoDate;

    @TableField("kaipiaoCount")
    private String kaipiaoCount;

    @TableField("kaipiaoAmount")
    private String kaipiaoAmount;

    @TableField("remark")
    private String remark;

    @TableField("jsr")
    private String jsr;
}
