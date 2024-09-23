package com.neu.java.spring.springboot.mybatisplus.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neu.java.spring.springboot.mybatisplus.MybatisPlusApplicationTests;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.GradeEnum;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.User;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserQuery;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserResult;
import com.neu.java.spring.springboot.mybatisplus.modules.user.service.IUserService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends MybatisPlusApplicationTests {

    @Autowired
    IUserService userService;

//    @Test
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

//    @Test
    public void getUserList() {
        List<User> list = userService.getUsers();
        if(list != null && list.size() > 0) {
            list.forEach(user -> {
                int status = user.getStatus();
                Assert.assertEquals(status, 0);
            });
        }
    }

    @Test
    public void getUserPageVo() {
        UserQuery userQuery = new UserQuery();
        userQuery.setCurrentPage(1);
        userQuery.setPageSize(2);
        userQuery.setWorkTime(0);
        userQuery.setName("小羊");
        userQuery.setStartTime("2021-08-09 15:16:18");

        IPage<UserResult> resultIPage = userService.selectUserPage(userQuery);
        Assert.assertTrue(resultIPage.getTotal() >= 0);
    }

//    @Test
    public void getUserByName() {
        String name = "小羊苏西";
        User u = new User();
        u.setName(name);
        u.setPhone("1569669" + new Random().nextInt(10000));
        u.setScore(new Random().nextFloat() * 100);
        u.setGrade(GradeEnum.PRIMARY);
        u.setCreateTime(LocalDateTime.now());

        userService.addUser(u);

        List<User> list = userService.selectUserByName(name);

        int i = 0;
        for (User us: list) {
            if (name.equals(us.getName())) i++;
        }

        Assert.assertTrue(i > 0);
    }

//    @Test
    public void delLogicUser() {
        User u = new User();
        u.setName("小羊logic");
        u.setPhone("1569669" + new Random().nextInt(10000));
        u.setScore(new Random().nextFloat() * 100);
        u.setGrade(GradeEnum.HIGH);
        u.setCreateTime(LocalDateTime.now());

        userService.addUser(u);

        User after = userService.getUserById(u.getId());
        Assert.assertEquals(after.getName(), u.getName());

        userService.delLogicUser(u.getId());
        User afterDel = userService.getUserById(u.getId());
        Assert.assertNull(afterDel);
    }

//    @Test
    public void delUser() {
        User u = new User();
        u.setName("小羊del");
        u.setPhone("1569669" + new Random().nextInt(10000));
        u.setScore(new Random().nextFloat() * 100);
        u.setGrade(GradeEnum.HIGH);
        u.setCreateTime(LocalDateTime.now());

        userService.addUser(u);

        String id = u.getId();

        userService.delUser(id);

        User afterDel = userService.getUserById(id);
        Assert.assertNull(afterDel);
    }

//    @Test
    public void updateUser() {
        List<User> list = userService.getUsers();
        Assert.assertTrue(list.size() > 0);

        User u = list.get(0);
        u.setUpdateTime(LocalDateTime.now());

        boolean updateResult = userService.updateUser(u);
        Assert.assertTrue(updateResult);
    }

//    @Test
    public void updateUserLockerFail() throws Exception{
        List<User> list = userService.getUsers();
        Assert.assertTrue(list.size() > 0);

        User u = list.get(0);

        User u1 = userService.getUserById(u.getId());
        u1.setUpdateTime(LocalDateTime.now());

        Thread.sleep(1000);

        User u2 = userService.getUserById(u.getId());
        u2.setUpdateTime(LocalDateTime.now());

        boolean result1 = userService.updateById(u1);
        boolean result2 = userService.updateById(u2);

        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }
}
