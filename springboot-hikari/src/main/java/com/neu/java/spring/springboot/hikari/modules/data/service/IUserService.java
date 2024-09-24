package com.neu.java.spring.springboot.hikari.modules.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neu.java.spring.springboot.hikari.modules.data.entity.User;

public interface IUserService extends IService<User> {
    void addUser(User user);
    void addUserMore();
}
