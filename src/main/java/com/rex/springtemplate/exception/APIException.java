package com.rex.springtemplate.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    private Integer code;
    private String msg;

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
