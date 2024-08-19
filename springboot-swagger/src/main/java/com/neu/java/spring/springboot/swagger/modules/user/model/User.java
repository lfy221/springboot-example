package com.neu.java.spring.springboot.swagger.modules.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(value = "用户信息")
public class User {

    @ApiModelProperty("主键Id")
    private String id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("身份证号")
    private String idno;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 类型为Date，用于测试与LocalDateTime的区别
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("删除(0：正常 1:删除）")
    private int status;
}
