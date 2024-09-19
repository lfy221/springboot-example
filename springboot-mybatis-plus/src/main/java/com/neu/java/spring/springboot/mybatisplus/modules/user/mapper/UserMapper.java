package com.neu.java.spring.springboot.mybatisplus.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neu.java.spring.springboot.mybatisplus.modules.user.entity.User;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserQuery;
import com.neu.java.spring.springboot.mybatisplus.modules.user.model.UserResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

    /**
     * <p>
     * 查询 : 根据userQuery查询用户列表，分页显示
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param userQuery 查询条件
     * @return 分页对象
     */
    IPage<UserResult> selectPageVo(Page<?> page, @Param(value = "query") UserQuery userQuery);

    @Select("select * from user where name = #{name}")
    List<User> selectUserByName(@Param("name") String name);

    @Delete("delete from user where id = #{id}")
    void delUser(@Param("id") String id);

}
