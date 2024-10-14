package com.neu.java.spring.springboot.jwt.modules.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @GetMapping("/getUsername")
    public String getUsername() {
        return "admin";
    }
}
