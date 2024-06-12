package com.dw.advice;

import com.dw.domain.com.dw.domain.vo.ResultVo;
import com.dw.eum.ResultCode;
import com.dw.exception.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.dw.controller")
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ControllerResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class className) {
        // response是resultVo类型，或者有NotControllerResponseAdvice注解 都不需要包装
        //A(isAssignableFrom)B a是b的父类
        log.info("----------method {}", methodParameter.getParameterType().getName());
        System.out.println("+++++++++++++method {}"+ methodParameter.getParameterType().getName());
        return !methodParameter.getParameterType().isAssignableFrom(ResultVo.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(new ResultVo(data));
            } catch (JsonProcessingException e) {
                throw new APIException(ResultCode.RESPONSE_PACK_ERROR, e.getMessage());
            }
        }

        return new ResultVo(data);
    }
}
