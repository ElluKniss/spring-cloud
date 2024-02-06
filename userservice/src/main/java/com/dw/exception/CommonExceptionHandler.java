package com.dw.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CommonResult customHandler(CustomException e){

        CommonResult commonResult = new CommonResult();
        commonResult.setMessage(e.getMessage());
        return commonResult;
    }

    @ExceptionHandler(InnerException.class)
    public CommonResult commonHandler(Exception e){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(ResponseCode.ERROR.getCode());
        commonResult.setMessage(ResponseCode.ERROR.getMsg());
        commonResult.setData(e.getMessage());
        return commonResult;
    }
}
