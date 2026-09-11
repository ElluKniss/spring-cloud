package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dw.domain.LingLiao;
import com.dw.domain.Product;
import com.dw.domain.vo.DeleteReq;
import com.dw.domain.vo.LingQueryResp;
import com.dw.domain.vo.PurchaseQuery;
import com.dw.exception.BusinessException;
import com.dw.mapper.LingLiaoMapper;
import com.dw.service.LingService;
import com.dw.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class LingServiceImpl extends ServiceImpl<LingLiaoMapper, LingLiao> implements LingService {

//    @Resource
//    private ObjectMapper objectMapper;

    @Resource
    private LingLiaoMapper lingLiaoMapper;
    @Autowired
    private StockService stockService;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRecord(DeleteReq req) {
        // 删除领取记录
        int i = lingLiaoMapper.deleteById(req.getId());
        List<Product> proList = req.getList();
        if(Objects.isNull(proList) || proList.isEmpty()){
            log.warn("删除记录里没有库存明细，orderId is {}", req.getId());
            return  true;
        }
        if (i != 1) {
            log.warn("删除失败，orderId is {}", req.getId());
            throw new BusinessException("删除失败，记录不存在");
        }
        // 添加库存
        for(Product pro: proList){
            if (StringUtils.isBlank(pro.getProductName()) || pro.getCount() == null) {
                continue;  // 跳过无效数据
            }
            //为保证入库的数量不变, 实际操作的是出库库存，负数
//            stockService.addInStock(pro.getProductName(), pro.getModel(), pro.getCount(), pro.getUnit(), LocalDateTime.now());
            // 取反
            BigDecimal count = pro.getCount().negate();
            stockService.addOutStock(pro.getProductName(), pro.getModel(), count, LocalDateTime.now());
        }
        return true;
    }

}
