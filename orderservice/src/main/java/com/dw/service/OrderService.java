package com.dw.service;

import com.dw.domain.OrderInfo;
import com.dw.domain.com.dw.domain.vo.OrderInfoQueryVo;
import com.dw.domain.com.dw.domain.vo.OrderListResp;

import java.util.List;

public interface OrderService {

    List<OrderInfo> queryOrder(String orderId);

    OrderListResp queryOrder(OrderInfoQueryVo orderReq);

    /**
     * 创建订单
     * @param userId
     * @param productId
     * @return
     */
    int createOrder(String userId, String productId);

    int addOrderInfo(OrderInfo orderInfo);
}
