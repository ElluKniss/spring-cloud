package com.dw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dw.handle.String2ListTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.ArrayTypeHandler;

import java.util.List;

@Data
@TableName(value = "t_sellinfo", autoResultMap = true)
public class SellInfo {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("contractno")
    private String contractNo;

    @TableField("signdate")
    private String signDate;

    private String company;

    private String zrr;

    private String sqr;

    private String pzr;

    @TableField("taxnumber")
    private String taxNumber;

    private String account;

    private String bank;

    private String phone;

    private String address;

    private int total;

    @TableField(typeHandler = String2ListTypeHandler.class, value = "productlist")
    private List<Product> productList;

}
