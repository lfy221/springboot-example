package com.neu.java.spring.springboot.mybatisplus.modules.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

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
     * 工作时间  1:全职 2:兼职
     */
    private int workTime;

    /**
     * 分数
     */
    private float score;

    /**
     * 用户级别：1，初级；2，中级；3，高级
     */
    private GradeEnum grade;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除(0：正常 1:删除)
     */
    @TableLogic
    private int status;

    @Version
    private int version;
}
