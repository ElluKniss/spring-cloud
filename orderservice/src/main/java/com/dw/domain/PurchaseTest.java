package com.dw.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class PurchaseTest {
    public int id;

//    @ExcelIgnore
    @ExcelProperty(value = "订单号", index = 7)
    public String orderId;
    public String noId;
    public String type;



    @ExcelProperty(value = "日期", index = 9)
    public String date;

    @ExcelProperty(value = "部门", index = 0)
    public String dept;

    @ExcelProperty(value = "供应商", index = 1)
    public String gys;

    @ExcelProperty(value = "产品名称", index = 2)
    public String productName;

    @ExcelProperty(value = "型号", index = 3)
    public String model;

    @ExcelProperty(value = "单位", index = 4)
    public String dan;

    @ExcelProperty(value = "数量", index = 5)
    public int count;


    @ExcelProperty(value = "单价", index = 6)
    public String unitPrice;


    public String totalPrice;

    @ExcelProperty(value = "orderType", index = 8)
    public String orderType;

    public String remark;

    public String jbr;
}
