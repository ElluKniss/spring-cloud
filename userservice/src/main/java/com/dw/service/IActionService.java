package com.dw.service;

import com.alibaba.fastjson2.JSONObject;
import com.dw.domain.base.Rsp;

public interface IActionService {
    Rsp doAction(JSONObject json) throws Exception;
}
