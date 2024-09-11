package com.neu.java.spring.springboot.websocket.modules.user.controller;

import com.neu.java.spring.springboot.websocket.common.model.Result;
import com.neu.java.spring.springboot.websocket.common.server.WebSocketServer;
import com.neu.java.spring.springboot.websocket.modules.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/adduser")
    @ResponseBody
    public String addUser() {
        log.info("add user");
        return "ok";
    }

    @GetMapping("/sendMessage")
    @ResponseBody
    public String sendMessage() {
        String msg = "sengMessage OK";
        String sid = "abc";

        WebSocketServer.sendMessageToSid(msg, sid);
        log.info("sendMessage:{}", msg);
        return "ok";
    }

    @GetMapping("/sendObject")
    @ResponseBody
    public String sendObject() {
        User user = new User();
        user.setName("杨");
        user.setAge(23);
        user.setPhone("13566668888");

        Result result = new Result(user);
        String sid = "abc";
        WebSocketServer.sendObjectToSid(result, sid);
        log.info("sendObject:{}", user);
        return "ok";
    }

    @GetMapping("/sendBinary")
    @ResponseBody
    public String sendBinary() {
        String path = "";
        FileInputStream inputStream;
        try {
            File file = new File(path);
            inputStream = new FileInputStream(file);
            byte b[] = new byte[(int)file.length()];

            ByteBuffer buffer = ByteBuffer.wrap(b);
            String sid = "abc";
            WebSocketServer.sendBinaryToSid(buffer, sid);

            inputStream.close();
            buffer.clear();
            log.info("sendBinary");
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "ok";
    }
}
