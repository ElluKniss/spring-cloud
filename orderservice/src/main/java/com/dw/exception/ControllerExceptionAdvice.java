package com.dw.exception;

import com.dw.domain.vo.ResultVo;
import com.dw.eum.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({BindException.class})
    public ResultVo MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        log.warn("参数校验失败：{}", objectError.getDefaultMessage());
        return new ResultVo(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVo APIExceptionHandler(APIException e) {
        log.warn("API异常：code={}, msg={}", e.getCode(), e.getMessage());
        return new ResultVo(e.getCode(), e.getMsg(), e.getMessage());
    }

    /**
     * 业务异常：库存不足、库存不存在等可预期错误，
     * 异常信息直接返回给前端展示（事务已在抛出时回滚）
     */
    @ExceptionHandler(BusinessException.class)
    public ResultVo BusinessExceptionHandler(BusinessException e) {
        log.warn("业务异常：{}", e.getMessage());
        return new ResultVo(e.getCode(), e.getMessage(), null);
    }

    /**
     * 兜底异常处理：未预期的系统错误（空指针、SQL异常等），
     * 记录完整错误栈，前端只返回通用提示，避免暴露内部细节
     */
    @ExceptionHandler(Exception.class)
    public ResultVo ExceptionHandler(Exception e) {
        log.error("系统异常：", e);
        return new ResultVo(ResultCode.FAILED.getCode(), "系统繁忙，请稍后再试", null);
    }
}
