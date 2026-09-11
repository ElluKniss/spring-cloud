package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dw.client.UserClient;
import com.dw.domain.Order;
import com.dw.domain.OrderInfo;
import com.dw.domain.vo.OrderInfoQueryVo;
import com.dw.domain.Product;
import com.dw.domain.vo.OrderListResp;
import com.dw.exception.BusinessException;
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
        log.info("按合同号[{}]查询到 {} 条订单明细", orderId, orderInfos.size());

        return orderInfos;
    }

    @Override
    public OrderListResp queryOrder(OrderInfoQueryVo orderReq) {
        QueryWrapper<OrderInfo> orderWrapper = new QueryWrapper<>();
        orderWrapper.eq(StringUtils.isNotBlank(orderReq.getContractNo()), "contractno", orderReq.getContractNo()).
                eq(StringUtils.isNotBlank(orderReq.getCompany()), "company", orderReq.getCompany()).
                eq(StringUtils.isNotBlank(orderReq.getJsr()), "jsr", orderReq.getJsr());

        Page<OrderInfo> page = new Page<>(orderReq.getCurrent(), orderReq.getSize());
        Page<OrderInfo> orderInfoPage = orderInfoMapper.selectPage(page, orderWrapper);
        List<OrderInfo> list = orderInfoPage.getRecords();
        long total = orderInfoPage.getTotal();
//        List<OrderInfo> list = orderInfoMapper.selectList(orderWrapper);
        return new OrderListResp(list, total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int createOrder(String userId, String productId) {
        log.info("创建订单开始：userId={}, productId={}", userId, productId);
        // 查询数量
        Product product = productMapper.selectById(productId);
        if(null == product){
            log.warn("商品不存在：productId={}", productId);
            throw new BusinessException("商品不存在：" + productId);
        }

        int count = product.getCount().intValue();
        if(0 >= count){
            log.warn("库存不足：productId={}, 当前库存={}", productId, count);
            throw new BusinessException("库存不足，商品ID：" + productId);
        }

        // 创建订单
        Order order = new Order(userId, productId);
        order.setStatus("1");
        String orderId =  OrderUtil.getOrderNo();
        order.setOrderID(orderId);
        orderMapper.insert(order);


        // 扣减库存（乐观条件：仅当库存未被其他事务改动时才扣减）
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("productid", productId).eq("count", count).set("count", count-1);
        int updated = productMapper.update(null, updateWrapper);
        if (updated == 0) {
            log.warn("库存扣减失败（并发冲突）：productId={}, orderId={}", productId, orderId);
            throw new BusinessException("库存扣减失败，请重试");
        }
        log.info("创建订单成功：orderId={}, productId={}", orderId, productId);

        return 1;
    }

    @Override
    public int addOrderInfo(OrderInfo orderInfo) {

        return orderInfoMapper.insert(orderInfo);
    }

}
