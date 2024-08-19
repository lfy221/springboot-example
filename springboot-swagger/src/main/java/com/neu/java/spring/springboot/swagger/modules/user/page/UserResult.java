package com.neu.java.spring.springboot.swagger.modules.user.page;

import com.neu.java.spring.springboot.swagger.modules.user.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "user分页信息", parent = Result.class)
public class UserResult extends Result {
    @ApiModelProperty(value = "人员数据列表", dataType = "List")
    private Page<User> page;
}
