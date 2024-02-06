package com.dw.controller;

import com.dw.domain.Order;
import com.dw.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/query/{orderID}")
    @ResponseBody
    public Order queryUser(@PathVariable String orderID) {
        Order order = orderService.queryOrder(orderID);
        order.setOrderID(orderID);
        return order;
    }

    @RequestMapping("now")
    public String getNowTime() {
        String format1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return format1;
    }

    @RequestMapping("/create/{productId}")
    public String createOrder(@PathVariable String productId) {

        int order1 = orderService.createOrder("123", productId);
        if (order1 > 0) {
            return "success";
        }

        return "fail";
    }
}
