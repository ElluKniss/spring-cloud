package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dw.domain.FinishedProduct;
import com.dw.domain.Purchase;
import com.dw.domain.vo.FinishedProductResp;
import com.dw.domain.vo.PurchaseListResp;
import com.dw.domain.vo.PurchaseQuery;
import com.dw.mapper.FinishedMapper;
import com.dw.service.FinishedProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinishedProductServiceImpl extends ServiceImpl<FinishedMapper, FinishedProduct> implements FinishedProductService {

    @Resource
    private FinishedMapper finishedMapper;

    @Override
    public FinishedProductResp queryAll(PurchaseQuery purchaseQuery) {

        QueryWrapper<FinishedProduct> queryWrapper = new QueryWrapper<FinishedProduct>();
        // 普通字段
        queryWrapper.like((StringUtils.isNotBlank(purchaseQuery.getProductName())), "productname", purchaseQuery.getProductName())
                .like(StringUtils.isNotBlank(purchaseQuery.getModel()), "model", purchaseQuery.getModel())
                .like(StringUtils.isNotBlank(purchaseQuery.getType()), "type", purchaseQuery.getType())
                .like(StringUtils.isNotBlank(purchaseQuery.getOrderType()), "ordertype", purchaseQuery.getOrderType());

        // 日期
        queryWrapper.ge(StringUtils.isNotBlank(purchaseQuery.getStartDate()), "date", purchaseQuery.getStartDate())
                .le(StringUtils.isNotBlank(purchaseQuery.getEndDate()), "date", purchaseQuery.getEndDate());


        queryWrapper.orderByDesc("create_time");

        Page<FinishedProduct> page = new Page<>(purchaseQuery.getCurrent(), purchaseQuery.getSize());
        Page<FinishedProduct> purchasePage = finishedMapper.selectPage(page, queryWrapper);
        List<FinishedProduct> list = purchasePage.getRecords();
        long total = purchasePage.getTotal();

        return new FinishedProductResp(list, total);
    }
}
