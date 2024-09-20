package com.neu.java.spring.springboot.mybatisplus.modules.user.service.impl;

import com.neu.java.spring.springboot.mybatisplus.MybatisPlusApplicationTests;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.GradeEnum;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.User;
import com.neu.java.spring.springboot.mybatisplus.modules.user.service.IUserService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends MybatisPlusApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void addUser() {
        User u = new User();
        u.setName("小羊");
        u.setPhone("1569669" + new Random().nextInt(10000));
        u.setScore(new Random().nextFloat() * 100);
        u.setGrade(GradeEnum.PRIMARY);
        u.setCreateTime(LocalDateTime.now());

        userService.addUser(u);

        User after = userService.getUserById(u.getId());
        Assert.assertEquals(u.getName(), after.getName());
        Assert.assertSame(GradeEnum.PRIMARY, after.getGrade());
    }
}
