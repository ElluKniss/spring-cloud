package com.dw.service.impl;

import com.dw.client.UserClient;
import com.dw.domain.Order;
import com.dw.domain.User;
import com.dw.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private UserClient userClient;

    @Override
    public Order queryOrder(String orderId) {

        log.info("query order");
        Order order = new Order();
        order.setUserID("2");
        User user = userClient.queryUser(order.getUserID());
        order.setUser(user);
        return order;
    }
}
