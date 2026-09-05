package com.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dw.domain.LingLiao;
import com.dw.domain.com.dw.domain.vo.LingQueryResp;
import com.dw.domain.com.dw.domain.vo.PurchaseQuery;

public interface LingService extends IService<LingLiao> {

    LingQueryResp queryAll(PurchaseQuery query);
}
