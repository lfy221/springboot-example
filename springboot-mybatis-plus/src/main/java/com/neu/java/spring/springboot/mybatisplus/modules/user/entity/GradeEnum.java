package com.neu.java.spring.springboot.mybatisplus.modules.user.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.neu.java.spring.springboot.mybatisplus.common.enums.IBaseEnum;
import lombok.Getter;

@Getter
public enum GradeEnum implements IBaseEnum<Integer> {

    PRIMARY(1, "初级"),
    SECONDARY(2, "中级"),
    HIGH(3, "高级");

    //标记存储到数据库的值
    @EnumValue
    private final int code;
    //标记json返回的值,比如前端显示为：初级
    @JsonValue
    private final String descp;

//    @JsonCreator
    GradeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @Override
    public String getDescription() {
        return descp;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
