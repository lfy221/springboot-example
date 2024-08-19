package com.neu.java.spring.springboot.swagger.modules.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryModel {

    @ApiModelProperty("当前页")
    protected long currentPage;

    @ApiModelProperty("每页显示条数")
    protected long pageSize;

}
