package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dw.domain.LingLiao;
import com.dw.domain.Product;
import com.dw.domain.Purchase;
import com.dw.domain.com.dw.domain.vo.LingQueryResp;
import com.dw.domain.com.dw.domain.vo.PurchaseQuery;
import com.dw.mapper.LingLiaoMapper;
import com.dw.service.LingService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LingServiceImpl extends ServiceImpl<LingLiaoMapper, LingLiao> implements LingService {

//    @Resource
//    private ObjectMapper objectMapper;

    @Resource
    private LingLiaoMapper lingLiaoMapper;

    @Override
    public LingQueryResp queryAll(PurchaseQuery query) {
        QueryWrapper<LingLiao> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(query.getDept()), "dept", query.getDept());

        queryWrapper.ge(StringUtils.isNotBlank(query.getStartDate()), "date", query.getStartDate())
                .le(StringUtils.isNotBlank(query.getEndDate()), "date", query.getEndDate());

        if (StringUtils.isNotBlank(query.getProductName())) {
            queryWrapper.apply("JSON_CONTAINS(productlist, JSON_OBJECT('productName', {0}))", query.getProductName());
        }

        if (StringUtils.isNotBlank(query.getModel())) {
            queryWrapper.apply("JSON_CONTAINS(productlist, JSON_OBJECT('model', {0}))", query.getModel());
        }

        queryWrapper.orderByDesc("create_time");


        Page<LingLiao> page = new Page<>(query.getCurrent(), query.getSize());
        IPage<LingLiao> lingLiaoIPage = lingLiaoMapper.selectPage(page, queryWrapper);

        List<LingLiao> list = lingLiaoIPage.getRecords();
        long total = lingLiaoIPage.getTotal();
        return new LingQueryResp(list, total);
    }

//    public void test(){
//        try {
//            // 直接使用注入的 objectMapper
//            String json = objectMapper.writeValueAsString(1);
//            System.out.println(json);
//
//            // 反序列化
//            List<Product> list = objectMapper.readValue(
//                    json,
//                    new TypeReference<List<Product>>() {}
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
