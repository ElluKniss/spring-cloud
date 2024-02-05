package com.dw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_product")
public class Product {

    @TableId("productid")
    private String productId;

    private String price;

    private int count;

    private String description;

    @TableField("createtime")
    private String createTime;
}
