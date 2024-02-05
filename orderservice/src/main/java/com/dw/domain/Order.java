package com.dw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order")
public class Order {

    @TableId("orderid")
    private String orderID;

    @TableField("userid")
    private String userID;

    @TableField("createtime")
    private String createTime;

    private String status;

    @TableField("productid")
    private String productId;

    private User user;

    public Order() {

    }

    public Order(String userID, String productId) {
        this.userID = userID;
        this.productId = productId;
    }

}
