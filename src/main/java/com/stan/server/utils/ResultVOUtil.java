package com.stan.server.utils;


import com.stan.server.enums.ResultEnum;

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SYSTEM_ERROR.getCode());
        resultVO.setMessage(ResultEnum.SYSTEM_ERROR.getMessage());
        return resultVO;
    }

    public static ResultVO error(String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SYSTEM_ERROR.getCode());
        resultVO.setMessage(message);
        return resultVO;
    }
}
