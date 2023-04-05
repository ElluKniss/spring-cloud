package com.dw.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public CommonResult customHandler(CustomException e){

        CommonResult commonResult = new CommonResult();
        commonResult.setMessage(e.getMessage());
        return commonResult;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public CommonResult commonHandler(Exception e){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(ResponseCode.ERROR.getCode());
        commonResult.setMessage(ResponseCode.ERROR.getMsg());
        commonResult.setData(e.getMessage());
        return commonResult;
    }
}
