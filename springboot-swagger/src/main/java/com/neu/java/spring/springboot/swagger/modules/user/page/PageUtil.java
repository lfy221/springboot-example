package com.neu.java.spring.springboot.swagger.modules.user.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("分页对象")
public class PageUtil<T> implements Serializable {

    @ApiModelProperty(value = "页码", dataType = "int")
    private int currentPage;

    @ApiModelProperty(value = "页面记录数", dataType = "int")
    private int pageSize;

    @ApiModelProperty(value = "总页数", dataType = "long")
    private long totalPage;

    @ApiModelProperty(value = "总记录数", dataType = "long")
    private long totalCount;

    @ApiModelProperty(value = "分页记录", dataType = "List")
    private List<T> list;
}
