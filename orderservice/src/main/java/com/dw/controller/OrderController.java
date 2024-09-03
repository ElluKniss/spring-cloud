package com.dw.controller;

import com.dw.domain.OrderInfo;
import com.dw.domain.com.dw.domain.vo.OrderInfoQueryVo;
import com.dw.domain.com.dw.domain.vo.OrderInfoVo;
import com.dw.domain.com.dw.domain.vo.OrderListResp;
import com.dw.domain.com.dw.domain.vo.ResultVo;
import com.dw.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/query/orderId/{orderID}")
    @ResponseBody
    public List<OrderInfo> queryUser(@PathVariable String orderID) {
        return orderService.queryOrder(orderID);
    }

    @PostMapping("/query/all")
    @ResponseBody
    public OrderListResp queryOrderInfo(@RequestBody OrderInfoQueryVo orderReq) {
        return orderService.queryOrder(orderReq);
    }

    /**
     * 新增订单信息
     */
    @PostMapping("/add")
    public ResultVo addOrderInfo(@RequestBody @Validated OrderInfoVo orderReq) {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderReq, orderInfo);
        return new ResultVo(orderService.addOrderInfo(orderInfo));
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
