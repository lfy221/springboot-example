package com.neu.java.spring.springboot.swagger.modules.user.controller;

import com.neu.java.spring.springboot.swagger.modules.user.model.User;
import com.neu.java.spring.springboot.swagger.modules.user.service.IUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户操作接口")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/addUser")
    @ResponseBody
    @ApiOperation(value = "增加用户", httpMethod = "POST")
    public String addUser(@ApiParam(value = "用户信息") @RequestBody User user) {
        if(user == null) {
            log.info("user is null");
        } else {
            log.info("user name: {}", user.getName());
        }
        return "add success";
    }

    @GetMapping("/queryUserById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询用户", httpMethod = "GET")
    @ApiImplicitParam(paramType = "query", dataType = "int", name = "id", value = "用户Id", required = true)
    public User queryUserById(int id) {
        log.info("queryUserById id: {}", id);
        return new User();
    }

    @PostMapping("/queryUserByAge")
    @ResponseBody
    @ApiOperation(value = "根据年龄查询用户", httpMethod = "POST")
    public User queryUserByAge(@ApiParam(value = "用户年龄", required = true) Integer age) {
        log.info("queryUserByAge age: {}", age);
        return new User();
    }

    @GetMapping("/queryUserByIdno")
    @ResponseBody
    @ApiOperation(value = "根据身份证号获取用户信息", httpMethod = "GET")
    @ApiImplicitParam(paramType = "query", dataType = "String", name = "idno", value = "身份证号", required = true)
    public User queryUserByIdno(String idno) {
        log.info("queryLastAttence idno: {}", idno);
        return userService.createUser();
    }


}
