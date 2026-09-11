package com.dw.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FinishedVo {

    private long id;


    // 订单号
    private String orderId;

    // 编号

    private String noId;

    // 入库-出库
    private String type;


    private String date;



    private String productName;

    private String model;

    // 入库数量
    private int count;

    private String remark;

    private String jbr;

    // 插入时不传值，用数据库默认
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
