package com.neu.java.spring.springboot.mybatisplus.modules.user.controller;

import com.neu.java.spring.springboot.mybatisplus.common.model.Result;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.User;
import com.neu.java.spring.springboot.mybatisplus.modules.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("add")
    public Result<String> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("getAllUser")
    public Result<List<User>> getAllUser() {
        return userService.getAllUsers();
    }
 }
