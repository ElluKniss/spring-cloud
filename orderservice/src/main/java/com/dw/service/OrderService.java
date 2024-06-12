package com.dw.service;

import com.dw.domain.OrderInfo;
import com.dw.domain.com.dw.domain.vo.OrderInfoQueryVo;

import java.util.List;

public interface OrderService {

    List<OrderInfo> queryOrder(String orderId);

    List<OrderInfo> queryOrder(OrderInfoQueryVo orderReq);

    /**
     * 创建订单
     * @param userId
     * @param productId
     * @return
     */
    int createOrder(String userId, String productId);

    int addOrderInfo(OrderInfo orderInfo);
}
