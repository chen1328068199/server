package com.stan.server.controller;

import com.stan.server.model.vo.UserVO;
import com.stan.server.service.UserService;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@Slf4j
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "testAuth")
    public ResultVO<UserVO> testAuth() {
        return ResultVOUtil.success("测试角色权限");
    }
}
