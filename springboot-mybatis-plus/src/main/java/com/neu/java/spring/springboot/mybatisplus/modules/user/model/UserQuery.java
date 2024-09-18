package com.neu.java.spring.springboot.mybatisplus.modules.user.model;

import lombok.Data;

@Data
public class UserQuery {
    //当前页
    protected long currentPage;
    //每页显示条数
    protected long pageSize;
    //名字
    private String name;
    //工作形式：全职、兼职
    private int workTime;
    //注册时间
    private String startTime;
}
