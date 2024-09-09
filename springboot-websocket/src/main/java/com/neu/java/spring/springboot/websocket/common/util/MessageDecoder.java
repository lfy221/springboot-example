package com.neu.java.spring.springboot.websocket.common.util;

import cn.hutool.json.JSONUtil;
import com.neu.java.spring.springboot.websocket.common.model.Result;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Result> {

    @Override
    public Result decode(String s) throws DecodeException {
        return JSONUtil.toBean(s, Result.class);
    }

    @Override
    public boolean willDecode(String s) {
        try {
            return JSONUtil.isJson(s);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        System.out.println("MessageDecoder - init method called.");
    }

    @Override
    public void destroy() {
        System.out.println("MessageDecoder - destroy method called.");
    }
}
