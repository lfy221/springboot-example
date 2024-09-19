package com.neu.java.spring.springboot.mybatisplus.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neu.java.spring.springboot.mybatisplus.common.model.Result;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.User;
import com.neu.java.spring.springboot.mybatisplus.modules.user.mapper.UserMapper;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserQuery;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserResult;
import com.neu.java.spring.springboot.mybatisplus.modules.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> addUser(User user) {
        super.save(user);
        return Result.success();
    }

    @Override
    public boolean updateUser(User user) {
        return super.updateById(user);
    }

    @Override
    public void delUser(String id) {
        userMapper.delUser(id);
    }

    @Override
    public void delLogicUser(String id) {
        super.removeById(id);
    }

    @Override
    public User getUserById(String id) {
        return super.getById(id);
    }

    @Override
    public List<User> selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public List<User> getUsers() {
        return super.list();
    }

    @Override
    public Result<List<User>> getAllUsers() {
        List<User> list = super.list();
        return Result.of(list);
    }

    @Override
    public IPage<UserResult> selectUserPage(UserQuery userQuery) {
        Page<UserResult> page = new Page<>(userQuery.getCurrentPage(), userQuery.getPageSize());
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        IPage<UserResult> resultIPage = userMapper.selectPageVo(page, userQuery);
        return resultIPage;
    }
}
