package com.dw.domain.com.dw.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderInfoVo {

    @NotNull(message = "合同编号不能为空")
    private String contractNo;

    @NotNull
    private String signDate;

    private String company;

    @NotNull
    private String productName;

    @NotNull
    private String model;


    @Min(value = 1)
    private String count;

    @NotNull
    private String unitPrice;

    @NotNull
    private String amount;

    @NotNull
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
