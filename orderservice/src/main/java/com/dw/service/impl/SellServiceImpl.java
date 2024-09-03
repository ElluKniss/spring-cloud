package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dw.domain.OrderInfo;
import com.dw.domain.SellInfo;
import com.dw.domain.com.dw.domain.vo.SellListResp;
import com.dw.domain.com.dw.domain.vo.SellQueryVo;
import com.dw.mapper.SellInfoMapper;
import com.dw.service.SellService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SellServiceImpl extends ServiceImpl<SellInfoMapper, SellInfo> implements SellService {

    @Resource
    private SellInfoMapper sellInfoMapper;

    @Override
    public SellListResp queryAll(SellQueryVo sellQueryVo) {
        QueryWrapper<SellInfo> queryWrapper = new QueryWrapper<SellInfo>();
        queryWrapper.like(StringUtils.isNotBlank(sellQueryVo.getCompany()),"company", sellQueryVo.getCompany()).
                like(StringUtils.isNotBlank(sellQueryVo.getZrr()),"zrr", sellQueryVo.getZrr());

        queryWrapper.ge(StringUtils.isNotBlank(sellQueryVo.getStartDate()), "signdate", sellQueryVo.getStartDate())
                .le(StringUtils.isNotBlank(sellQueryVo.getEndDate()), "signdate", sellQueryVo.getEndDate());

        Page<SellInfo> page = new Page<>(sellQueryVo.getCurrent(), sellQueryVo.getSize());
        Page<SellInfo> sellInfoPage = sellInfoMapper.selectPage(page, queryWrapper);
        List<SellInfo> list = sellInfoPage.getRecords();
        long total = sellInfoPage.getTotal();

        return new SellListResp(list, total);
    }
}
