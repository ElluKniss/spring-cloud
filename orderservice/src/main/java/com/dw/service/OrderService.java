package com.dw.service;

import com.dw.domain.Order;

public interface OrderService {

    Order queryOrder(String orderId);
}
