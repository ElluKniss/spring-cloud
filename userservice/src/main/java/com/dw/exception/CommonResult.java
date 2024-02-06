package com.dw.exception;

import lombok.Data;

@Data
public class CommonResult {

    private int code;
    private String message;
    private Object data;
}
