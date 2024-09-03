package com.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dw.domain.SellInfo;
import com.dw.domain.com.dw.domain.vo.SellListResp;
import com.dw.domain.com.dw.domain.vo.SellQueryVo;

import java.util.List;

public interface SellService extends IService<SellInfo> {

    SellListResp queryAll(SellQueryVo sellQueryVo);
}
