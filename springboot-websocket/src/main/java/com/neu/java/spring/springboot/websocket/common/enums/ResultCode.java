package com.neu.java.spring.springboot.websocket.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("1", "请求处理成功"),
    ERROR("-1", "请求处理失败");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
