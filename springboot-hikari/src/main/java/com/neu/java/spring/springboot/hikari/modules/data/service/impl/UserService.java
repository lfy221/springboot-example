package com.neu.java.spring.springboot.hikari.modules.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neu.java.spring.springboot.hikari.modules.data.entity.User;
import com.neu.java.spring.springboot.hikari.modules.data.mapper.UserMapper;
import com.neu.java.spring.springboot.hikari.modules.data.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    ExecutorService pool = Executors.newFixedThreadPool(10);

    @Override
    public void addUser(User user) {
        super.save(user);
    }

    @Override
    public void addUserMore() {
        int i = 0;
        while (i < 10) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pool.execute(this::run);
            i++;
        }
    }

    private void run() {
        User u = new User();
        u.setId(String.valueOf(System.currentTimeMillis()) + new Random().nextInt(6));
        u.setName("老牛");
        u.setAddress("北京市大兴区北京经济技术开发区");
        u.setIdno("110221196002061256");
        u.setPhone("01087260266");
        u.setSex("m");
        u.setRemark("" + System.currentTimeMillis());
        u.setCreateTime(LocalDateTime.now());
        System.out.println("adduser");
        addUser(u);
    }
}
