package com.dw.service;

import com.dw.domain.Order;

public interface OrderService {

    Order queryOrder(String orderId);

    /**
     * 创建订单
     * @param userId
     * @param productId
     * @return
     */
    int createOrder(String userId, String productId);
}
