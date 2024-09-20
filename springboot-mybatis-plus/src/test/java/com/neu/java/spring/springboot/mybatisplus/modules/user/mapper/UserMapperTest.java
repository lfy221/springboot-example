package com.neu.java.spring.springboot.mybatisplus.modules.user.mapper;

import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.User;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    public void getListUser() {
        List<User> list = userMapper.selectList(null);
        Assert.assertTrue(list.size() >= 0);
        list.forEach(System.out::println);
    }
}
