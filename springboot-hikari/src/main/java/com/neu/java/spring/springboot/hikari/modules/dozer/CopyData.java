package com.neu.java.spring.springboot.hikari.modules.dozer;

import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CopyData {
    @Autowired
    private Mapper dozerBeanMapper;

    public UserTarget copyUser(UserSource source) {
        UserTarget target = new UserTarget();
        dozerBeanMapper.map(source, target);
        return target;
    }
}
