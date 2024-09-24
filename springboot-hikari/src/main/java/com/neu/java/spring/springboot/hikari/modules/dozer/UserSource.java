package com.neu.java.spring.springboot.hikari.modules.dozer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSource {
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idno;

    /**
     * 住址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 删除(0：正常 1:删除）
     */
    private int status;
}
