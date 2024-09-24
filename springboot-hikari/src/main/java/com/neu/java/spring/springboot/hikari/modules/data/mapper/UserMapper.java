package com.neu.java.spring.springboot.hikari.modules.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.java.spring.springboot.hikari.modules.data.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}
