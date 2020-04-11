package com.stan.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.stan.server.model.SysUser;
import com.stan.server.model.vo.UserVO;
import com.stan.server.service.AttendanceService;
import com.stan.server.service.LoginService;
import com.stan.server.service.SignInService;
import com.stan.server.service.UserService;
import com.stan.server.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weChat")
@Slf4j
@Api(tags = "签到接口")
public class WeChatController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private SignInService signInService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @PostMapping("scanQRCode")
    @ApiOperation("二维码考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "二维码内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "key", value = "二维码内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, dataType = "String"),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, dataType = "String")
    })
    public ResultVO<Object> qrCodeAttendance(@RequestParam("openId") String openId,
                                             @RequestParam("key") String key,
                                             @RequestParam("longitude") double longitude,
                                             @RequestParam("latitude") double latitude) {
        // 校验二维码
        String attendanceCacheKey = "QRCodeKey";
        String attendanceKey = attendanceService.getQRCodeAttendanceKey(attendanceCacheKey);
        if (!key.equals(attendanceKey)) {
            return ResultVO.result(211, "二维码错误");
        }
        return signInService.signIn(openId, longitude, latitude);
    }

    @PostMapping("loginByPassword")
    @ApiOperation("根据密码登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "openId", value = "openId", required = true, dataType = "String"),
        @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
        @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    public ResultVO<Object> loginByPassword(@RequestParam("openId") String openId,
                                  @RequestParam("userName") String userName,
                                  @RequestParam("password") String password) {
        UserVO userVO = userService.getUserInfoByName(userName);
        if (userVO == null)
            return ResultVO.result(232, "该用户不存在");
        if (userVO.getOpenId() != null && !userVO.getOpenId().equals(openId))
            return ResultVO.result(2333, "该账号已绑定其它微信");
        if (!new BCryptPasswordEncoder().matches(password, userVO.getUserPassword())) {
            return ResultVO.result(233, "密码错误");
        }
        UpdateWrapper<SysUser> userVOUpdateWrapper = new UpdateWrapper<>();
        userVOUpdateWrapper.eq("user_name", userName);
        userVOUpdateWrapper.set("open_id", openId);
        userService.update(userVOUpdateWrapper);
        return ResultVO.success();
    }

    @PostMapping("loginByOpenId")
    @ApiOperation("openId登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "openId", value = "openId", required = true, dataType = "String"),
    })
    public ResultVO<Object> loginByOpenId(@RequestParam("code") String code) {
        String wxOpenId = loginService.getWxOpenId(code).getData();
        UserVO userVO = userService.getUserInfoByOpenId(wxOpenId);
        if (userVO == null)
            return ResultVO.result(212, "用户未注册", "osAV75Qo7exKPMahc8pmRcJF6SJE");
        return ResultVO.success(wxOpenId);
    }
}
