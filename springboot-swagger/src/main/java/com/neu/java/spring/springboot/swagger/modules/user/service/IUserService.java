package com.neu.java.spring.springboot.swagger.modules.user.service;

import com.neu.java.spring.springboot.swagger.modules.user.model.User;
import com.neu.java.spring.springboot.swagger.modules.user.model.UserQueryModel;
import com.neu.java.spring.springboot.swagger.modules.user.page.UserResult;

public interface IUserService {
    /**
     * 创建新用户
     * @return
     */
    User createUser();

    /**
     * 分页查询
     * @param userQueryModel
     * @return
     */
    UserResult queryUserForPage(UserQueryModel userQueryModel);
}
