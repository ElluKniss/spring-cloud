package com.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dw.domain.FinishedProduct;
import com.dw.domain.vo.FinishedProductResp;
import com.dw.domain.vo.PurchaseListResp;
import com.dw.domain.vo.PurchaseQuery;

public interface FinishedProductService extends IService<FinishedProduct> {

    FinishedProductResp queryAll(PurchaseQuery purchaseQuery);
}
