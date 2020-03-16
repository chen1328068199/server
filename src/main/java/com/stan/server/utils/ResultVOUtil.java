package com.stan.server.utils;


import com.stan.server.enums.ResultEnum;

public class ResultVOUtil {

    public static <T> ResultVO<T> success(T object){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static <T> ResultVO<T> success() {
        return success(null);
    }

    public static <T> ResultVO<T> error() {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ResultEnum.SYSTEM_ERROR.getCode());
        resultVO.setMessage(ResultEnum.SYSTEM_ERROR.getMessage());
        return resultVO;
    }

    public static <T> ResultVO<T> error(String message) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ResultEnum.SYSTEM_ERROR.getCode());
        resultVO.setMessage(message);
        return resultVO;
    }
}
