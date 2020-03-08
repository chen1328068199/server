package com.stan.server.controller;

import com.stan.server.service.LoginService;
import com.stan.server.service.UserService;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.ResultVOUtil;
import io.micrometer.core.instrument.util.StringUtils;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @GetMapping("wx/openid")
    public ResultVO<String> getWxOpenId(@RequestParam String jsCode) {
        String wxOpenId = loginService.getWxOpenId(jsCode);
        if (StringUtils.isNotBlank(wxOpenId)) {
            return ResultVOUtil.success(wxOpenId);
        }else {
            return ResultVOUtil.error("openId未绑定!");
        }
    }
}
