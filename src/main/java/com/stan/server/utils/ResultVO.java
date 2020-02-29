package com.stan.server.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "返回类")
public class ResultVO<T> {
    @ApiModelProperty(value = "返回代码")
    private Integer code;
    @ApiModelProperty(value = "返回数据")
    private T data;
    @ApiModelProperty(value = "返回信息")
    private String message;
}
