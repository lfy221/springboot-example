package com.neu.java.spring.springboot.websocket.common.util;

import cn.hutool.json.JSONUtil;
import com.neu.java.spring.springboot.websocket.common.model.Result;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Result> {

    @Override
    public String encode(Result result) throws EncodeException {
        return JSONUtil.toJsonStr(result);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        System.out.println("MessageEncoder - init method called.");
    }

    @Override
    public void destroy() {
        System.out.println("MessageEncoder - destroy method called.");
    }
}
