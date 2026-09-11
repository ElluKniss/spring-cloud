package com.dw.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.dw.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class LingLiaoVo {

    private long id;

    private String orderId;

    @NotNull
    private String date;

    @NotNull
    private String dept;

    private String remark;

    private String jbr;

    private LocalDateTime createTime;

    @NotNull
    private List<Product> productList;

}
