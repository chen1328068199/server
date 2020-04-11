package com.stan.server.utils;

import com.stan.server.enums.HttpResultEnum;
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

    private ResultVO(){}

    public static <T> ResultVO<T> success(T object){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(HttpResultEnum.SUCCESS.getCode());
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static <T> ResultVO<T> success() {
        return success(null);
    }

    public static <T> ResultVO<T> fail() {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(HttpResultEnum.FAILURE.getCode());
        resultVO.setMessage(HttpResultEnum.FAILURE.getMessage());
        return resultVO;
    }

    public static <T> ResultVO<T> fail(String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(HttpResultEnum.FAILURE.getCode());
        resultVO.setMessage(msg);
        return resultVO;
    }

    public static <T> ResultVO<T> resultEnum(HttpResultEnum resultEnum) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());
        return resultVO;
    }

    public static <T> ResultVO<T> result(Integer code, String message) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static <T> ResultVO<T> result(Integer code, String message, T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(data);
        return resultVO;
    }
}
