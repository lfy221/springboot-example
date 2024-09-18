package com.neu.java.spring.springboot.mybatisplus.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.neu.java.spring.springboot.mybatisplus.common.enums.ResultCode.SUCCESS;
import static com.neu.java.spring.springboot.mybatisplus.common.enums.ResultCode.ERROR;

@Data
@Builder
@AllArgsConstructor
public class Result<T> {

    // 响应码
    private String code;

    //请求处理message
    private String message;

    //返回内容
    private T data;

    public Result() {
        super();
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public Result(T data) {
        this.data = data;
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
    }

    public static Result of(Object data) {
        return Result.builder().code(SUCCESS.getCode()).message(SUCCESS.getMessage()).data(data).build();
    }

    public static Result success() {
        return Result.builder().code(SUCCESS.getCode()).message(SUCCESS.getMessage()).data("success").build();
    }

    public static Result error() {
        return Result.builder().code(ERROR.getCode()).message(ERROR.getMessage()).data("error").build();
    }
}
