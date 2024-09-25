package com.neu.java.spring.springboot.hikari.modules.data;

import com.neu.java.spring.springboot.hikari.common.util.UUIDGenerator;
import com.neu.java.spring.springboot.hikari.modules.HikariApplicationTests;
import com.neu.java.spring.springboot.hikari.modules.data.entity.User;
import com.neu.java.spring.springboot.hikari.modules.data.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserServiceTest extends HikariApplicationTests {
    @Autowired
    IUserService userService;

    //    @Test
    public void addUser() {
        User u = new User();
        u.setId(UUIDGenerator.getUUID());
        u.setName("老牛");
        u.setCreateTime(LocalDateTime.now());

        userService.addUser(u);

        User after = userService.getById(u.getId());
        Assert.assertEquals(after.getName(), u.getName());
//        userService.removeById(u.getId());
    }

    @Test
    public void addUserMore() {
        long start  = System.currentTimeMillis();

        userService.addUserMore();

        long usetime = System.currentTimeMillis() - start;
        System.out.println("use time : " + usetime);

    }
}
