package com.dw.exception;

import lombok.Data;

@Data
public class CommonResult {

    private String code;
    private String message;
    private Object data;
}
