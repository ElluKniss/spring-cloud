package com.dw.domain.com.dw.domain.vo;

import com.dw.domain.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SellInfoVo {

    private long id;

    @NotNull
    private String taxNumber;

    @NotNull
    private String account;

    private String contractNo;

    @NotNull
    private String company;

    private String signDate;

    private String bank;

    @NotNull
    private String zrr;

    private String sqr;

    private String phone;

    private String address;

    private int total;

    public List<Product> productList;
}
