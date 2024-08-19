package com.neu.java.spring.springboot.swagger.modules.user.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("api接口通用返回对象")
public class Result implements Serializable {

    @ApiModelProperty(value = "返回码", dataType = "int")
    private int code;

    @ApiModelProperty(value = "错误信息", dataType = "String")
    private String msg;

    @ApiModelProperty(value = "api版本", dataType = "String")
    private String version;
}
