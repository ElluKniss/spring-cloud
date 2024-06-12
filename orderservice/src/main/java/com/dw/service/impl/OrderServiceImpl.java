package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dw.client.UserClient;
import com.dw.domain.Order;
import com.dw.domain.OrderInfo;
import com.dw.domain.com.dw.domain.vo.OrderInfoQueryVo;
import com.dw.domain.Product;
import com.dw.mapper.OrderInfoMapper;
import com.dw.mapper.OrderMapper;
import com.dw.mapper.ProductMapper;
import com.dw.service.OrderService;
import com.dw.util.OrderUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private UserClient userClient;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> queryOrder(String orderId) {

        log.info("query order");
        QueryWrapper<OrderInfo> orderWrapper = new QueryWrapper<>();
        orderWrapper.eq("contractno", orderId);
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(orderWrapper);
        System.out.println("-------------------------------:" + orderInfos);

        return orderInfos;
    }

    @Override
    public List<OrderInfo> queryOrder(OrderInfoQueryVo orderReq) {
        QueryWrapper<OrderInfo> orderWrapper = new QueryWrapper<>();
        orderWrapper.eq(StringUtils.isNotBlank(orderReq.getContractNo()), "contractno", orderReq.getContractNo()).
                eq(StringUtils.isNotBlank(orderReq.getCompany()), "company", orderReq.getCompany()).
                eq(StringUtils.isNotBlank(orderReq.getJsr()), "jsr", orderReq.getJsr());

        Page<OrderInfo> page = new Page<>(orderReq.getCurrent(), orderReq.getSize());
        Page<OrderInfo> orderInfoPage = orderInfoMapper.selectPage(page, orderWrapper);
        List<OrderInfo> list = orderInfoPage.getRecords();
//        List<OrderInfo> list = orderInfoMapper.selectList(orderWrapper);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int createOrder(String userId, String productId) {
        // 查询数量
        Product product = productMapper.selectById(productId);
        if(null == product){
            log.error("商品不存在");
            return 0;
        }

        int count = product.getCount();
        if(0>= count){
            log.warn("库存没了");
            return 0;
        }

        // 创建订单
        Order order = new Order(userId, productId);
        order.setStatus("1");
        String orderId =  OrderUtil.getOrderNo();
        order.setOrderID(orderId);
        int insert = orderMapper.insert(order);


        // 扣减库存
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("productid", productId).eq("count", count).set("count", count-1);
        productMapper.update(null, updateWrapper);

        return 0;
    }

    @Override
    public int addOrderInfo(OrderInfo orderInfo) {
        return 0;
    }

}
