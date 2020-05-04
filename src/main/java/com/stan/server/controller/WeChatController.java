package com.stan.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.stan.server.entity.AttendanceRules;
import com.stan.server.enums.AttendanceModeEnum;
import com.stan.server.enums.RecordTypeEnum;
import com.stan.server.enums.StatusEnum;
import com.stan.server.model.User;
import com.stan.server.model.vo.UserVO;
import com.stan.server.service.*;
import com.stan.server.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("weChat")
@Slf4j
@Api(tags = "签到接口")
public class WeChatController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceRulesService attendanceRulesService;
    @Autowired
    private SignInService signInService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @PostMapping("scanQRCode")
    @ApiOperation("二维码考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信openId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "key", value = "二维码内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true),
            @ApiImplicitParam(name = "address", value = "考勤位置", required = true, dataType = "String"),
            @ApiImplicitParam(name = "purpose", value = "打卡目的 0上班/1下班", required = true),
    })
    public ResultVO<Object> qrCodeAttendance(@RequestParam("openId") String openId,
                                             @RequestParam("key") String key,
                                             @RequestParam("longitude") double longitude,
                                             @RequestParam("latitude") double latitude,
                                             @RequestParam("address") String address,
                                             @RequestParam("purpose") Integer purpose) {
        // 校验二维码
        String attendanceCacheKey = "QRCodeKey";
        String attendanceKey = attendanceService.getCodeAttendanceKey(attendanceCacheKey);
        if (!key.equals(attendanceKey)) {
            return ResultVO.result(211, "二维码错误");
        }
        return signInService.signIn(openId, AttendanceModeEnum.QRCode, longitude, latitude, address, purpose);
    }

    @PostMapping("code")
    @ApiOperation("口令考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信openId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "口令", required = true, dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true),
            @ApiImplicitParam(name = "address", value = "考勤位置", required = true, dataType = "String"),
            @ApiImplicitParam(name = "purpose", value = "打卡目的 0上班/1下班", required = true),
    })
    public ResultVO<Object> codeAttendance(@RequestParam("openId") String openId,
                                             @RequestParam("code") String code,
                                             @RequestParam("longitude") double longitude,
                                           @RequestParam("latitude") double latitude,
                                           @RequestParam("address") String address,
                                           @RequestParam("purpose") Integer purpose) {
        // 校验二维码
        String attendanceCacheKey = "codeKey";
        String attendanceKey = attendanceService.getCodeAttendanceKey(attendanceCacheKey);
        if (!code.equals(attendanceKey)) {
            return ResultVO.result(211, "二维码错误");
        }
        return signInService.signIn(openId, AttendanceModeEnum.CODE, longitude, latitude, address, purpose);
    }

    @PostMapping("location")
    @ApiOperation("位置考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信openId", required = true, dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true),
            @ApiImplicitParam(name = "address", value = "考勤位置", required = true, dataType = "String"),
            @ApiImplicitParam(name = "purpose", value = "打卡目的 0上班/1下班", required = true),
    })
    public ResultVO<Object> locationAttendance(@RequestParam("openId") String openId,
                                             @RequestParam("longitude") double longitude,
                                               @RequestParam("latitude") double latitude,
                                               @RequestParam("address") String address,
                                               @RequestParam("purpose") Integer purpose) {
        // 校验二维码
        return signInService.signIn(openId, AttendanceModeEnum.LOCATION, longitude, latitude, address, purpose);
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
        User user = userService.getUserInfoByName(userName);
        if (user == null)
            return ResultVO.result(232, "该用户不存在");
        if (user.getOpenId() != null && !user.getOpenId().equals(openId))
            return ResultVO.result(2333, "该账号已绑定其它微信");
        if (!new BCryptPasswordEncoder().matches(password, user.getUserPassword())) {
            return ResultVO.result(233, "密码错误");
        }
        UpdateWrapper<User> userVOUpdateWrapper = new UpdateWrapper<>();
        userVOUpdateWrapper.eq("user_name", userName);
        userVOUpdateWrapper.set("open_id", openId);
        userService.update(userVOUpdateWrapper);
        return ResultVO.success();
    }

    @PostMapping("loginByOpenId")
    @ApiOperation("openId登录")
    public ResultVO<Object> loginByOpenId(@RequestParam("code") String code) {
        String wxOpenId = loginService.getWxOpenId(code).getData();
        UserVO userVO = userService.getUserInfoByOpenId(wxOpenId);
        if (userVO == null)
            return ResultVO.result(212, "用户未注册", "osAV75Qo7exKPMahc8pmRcJF6SJE");
        return ResultVO.success(wxOpenId);
    }

    @GetMapping("getAttendancePurpose")
    @ApiOperation("获得本次考勤目的")
    public ResultVO<Integer> notAuthGetAttendancePurpose() {
        QueryWrapper<AttendanceRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.NORMAL.getCode());
        List<AttendanceRules> list = attendanceRulesService.list(queryWrapper);
        if (list.size() == 0)
            return ResultVO.result(200, "没有可用打卡方式");
        AttendanceRules rules = list.get(0);
        LocalTime now = LocalTime.now();
        if (now.compareTo(rules.getBeginWorkEndTime()) < 0)
            return ResultVO.success(RecordTypeEnum.BEGIN_WORKING.getCode());
        else if (now.compareTo(rules.getEndWorkEndTime()) < 0)
            return ResultVO.success(RecordTypeEnum.END_WORKING.getCode());
        return ResultVO.result(200, "已过打卡时间");
    }

    @GetMapping("getAttendanceTime")
    @ApiOperation("获得考勤时间段")
    public ResultVO<AttendanceRules> notAuthGetAttendanceTime() {
        QueryWrapper<AttendanceRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.NORMAL.getCode());
        List<AttendanceRules> list = attendanceRulesService.list(queryWrapper);
        if (list.size() == 0)
            return ResultVO.result(200, "没有可用打卡方式");
        AttendanceRules rules = list.get(0);
        return ResultVO.success(rules);
    }
}
