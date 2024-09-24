package com.neu.java.spring.springboot.hikari.modules.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neu.java.spring.springboot.hikari.common.entity.SuperEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User extends SuperEntity {
    private static final long serialVersionUID = 1L;

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
