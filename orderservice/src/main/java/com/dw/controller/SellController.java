package com.dw.controller;

import com.dw.domain.SellInfo;
import com.dw.domain.com.dw.domain.vo.ResultVo;
import com.dw.domain.com.dw.domain.vo.SellInfoVo;
import com.dw.domain.com.dw.domain.vo.SellListResp;
import com.dw.domain.com.dw.domain.vo.SellQueryVo;
import com.dw.service.SellService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/sell")
public class SellController {

    @Resource
    private SellService sellService;

    @RequestMapping("/query/all")
    public SellListResp queryUser(@RequestBody SellQueryVo sellReq) {
        return sellService.queryAll(sellReq);
    }

    @RequestMapping("/add")
    public ResultVo addSell(@RequestBody @Validated SellInfoVo sellInfoVo){
        SellInfo sellInfo = new SellInfo();
        BeanUtils.copyProperties(sellInfoVo, sellInfo);
        if (sellInfo.getId()>0) {
            return new ResultVo(sellService.updateById(sellInfo));
        }else {
            return new ResultVo(sellService.save(sellInfo));
        }

    }
}
