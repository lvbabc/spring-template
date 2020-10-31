package com.rex.springtemplate.vo;

import com.rex.springtemplate.enums.ResultCode;
import lombok.Getter;

@Getter
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }
}
