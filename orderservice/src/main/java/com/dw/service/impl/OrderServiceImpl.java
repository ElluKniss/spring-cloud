package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dw.client.UserClient;
import com.dw.domain.Order;
import com.dw.domain.Product;
import com.dw.domain.User;
import com.dw.mapper.OrderMapper;
import com.dw.mapper.ProductMapper;
import com.dw.service.OrderService;
import com.dw.util.OrderUtil;
import com.zaxxer.hikari.util.DriverDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private UserClient userClient;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Order queryOrder(String orderId) {

        log.info("query order");
        Order order = new Order();
        order.setUserID("2");
        User user = userClient.queryUser(order.getUserID());
        order.setUser(user);
        return order;
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

}
