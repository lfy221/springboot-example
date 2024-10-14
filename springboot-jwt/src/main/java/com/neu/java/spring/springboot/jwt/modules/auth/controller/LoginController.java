package com.neu.java.spring.springboot.jwt.modules.auth.controller;

import com.neu.java.spring.springboot.jwt.modules.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    public Map<String, Object> login(String username, String password) {
        return loginService.login(username, password);
    }
}
