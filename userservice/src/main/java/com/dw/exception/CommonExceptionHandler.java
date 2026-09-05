package com.dw.exception;

import com.dw.eum.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CommonResult customHandler(CustomException e){

        CommonResult commonResult = new CommonResult();
        ResultCode resultCode = e.getResultCode();
        if (resultCode != null) {
            commonResult.setCode(String.valueOf(resultCode.getCode()));
            commonResult.setMessage(resultCode.getMsg());
        } else {
            commonResult.setMessage(e.getMessage());
        }
        return commonResult;
    }

    @ExceptionHandler(InnerException.class)
    public CommonResult commonHandler(Exception e){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(String.valueOf(ResponseCode.ERROR.getCode()));
        commonResult.setMessage(ResponseCode.ERROR.getMsg());
        commonResult.setData(e.getMessage());
        return commonResult;
    }
}
