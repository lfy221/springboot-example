package com.neu.java.spring.springboot.swagger.modules.user.controller;

import com.neu.java.spring.springboot.swagger.modules.user.model.User;
import com.neu.java.spring.springboot.swagger.modules.user.model.UserQueryModel;
import com.neu.java.spring.springboot.swagger.modules.user.page.UserResult;
import com.neu.java.spring.springboot.swagger.modules.user.service.IUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/queryUserList")
    @ResponseBody
    @ApiOperation(value = "获取用户信息列表", httpMethod = "GET")
    public List<User> queryUserList() {
        List<User> list = new ArrayList<>();
        list.add(userService.createUser());
        list.add(userService.createUser());
        list.add(userService.createUser());

        return list;
    }

    @PostMapping("/queryUserForPage")
    @ResponseBody
    @ApiOperation(value = "分页获取用户信息", httpMethod = "POST")
    public UserResult queryUserForPage(@RequestBody UserQueryModel userQueryModel) {
        log.info("userQueryModel");
        return userService.queryUserForPage(userQueryModel);
    }

    @GetMapping("/queryUserByNameAndPhone")
    @ResponseBody
    @ApiOperation(value = "根据用户名和手机号获取用户信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value = "用户名", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "phone", value = "手机号码")
    })
    public User queryUserByNameAndPhone(String name, String phone) {
        log.info("name: {}, phone: {}", name, phone);
        User u = userService.createUser();
        return u;
    }

    @PostMapping("/setUserLotteryInfo")
    @ApiOperation(value = "用户购买彩票订单", httpMethod = "POST")
    public String setUserLotteryInfo(
            @ApiParam(value = "批量订单号，String数组传输") @RequestParam(value = "numList", required = false) List<String> numList,
            @ApiParam(value = "订单状态(1:待处理 2:出票中 3:完成 4:关闭)") @RequestParam(value = "status") int status) {

        if(numList != null) {
            for(String num : numList) {
                log.info("num: {}", num);
            }
        }
        log.info("status: {}", status);

        return "test ok";
    }

}
