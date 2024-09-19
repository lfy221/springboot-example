package com.neu.java.spring.springboot.mybatisplus.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neu.java.spring.springboot.mybatisplus.common.model.Result;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.User;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserQuery;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserResult;

import java.util.List;

public interface IUserService extends IService<User> {
    Result<String> addUser(User user);

    boolean updateUser(User user);

    void delUser(String id);

    void delLogicUser(String id);

    User getUserById(String id);

    List<User> selectUserByName(String name);

    List<User> getUsers();

    Result<List<User>> getAllUsers();

    IPage<UserResult> selectUserPage(UserQuery userQuery);
}
