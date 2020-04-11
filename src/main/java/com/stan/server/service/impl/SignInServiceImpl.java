package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.bean.AttendanceRecord;
import com.stan.server.bean.AttendanceRules;
import com.stan.server.bean.AttendanceRulesTime;
import com.stan.server.enums.AttendanceModeEnum;
import com.stan.server.enums.HttpResultEnum;
import com.stan.server.enums.StatusEnum;
import com.stan.server.service.*;
import com.stan.server.utils.LocationUtils;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.SecurityAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private AttendanceRulesService attendanceRulesService;
    @Autowired
    private AttendanceRulesTimeService attendanceRulesTimeService;
    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @Override
    public ResultVO<Object> signIn(String openId, double longitude, double latitude) {
        LocalDateTime now = LocalDateTime.now();
        QueryWrapper<AttendanceRules> rulesQueryWrapper = new QueryWrapper<>();
        rulesQueryWrapper.eq("way", "QRCode");
        rulesQueryWrapper.eq("status", 1);
        AttendanceRules attendanceRules = attendanceRulesService.getOne(rulesQueryWrapper);
        if (attendanceRules != null) {
            return ResultVO.fail();
        }
        double distance = LocationUtils.getDistance(longitude, latitude, attendanceRules.getLon(), attendanceRules.getLat());
        if (distance < attendanceRules.getDistance()) {
            return ResultVO.fail();
        }
        QueryWrapper<AttendanceRulesTime> rulesTimeQueryWrapper = new QueryWrapper<>();
        LocalTime time = now.toLocalTime();
        rulesTimeQueryWrapper.lt("start_time", time);
        rulesTimeQueryWrapper.ge("end_time", time);
        rulesTimeQueryWrapper.eq("status", StatusEnum.NORMAL);
        List<AttendanceRulesTime> timeRules = attendanceRulesTimeService.list(rulesTimeQueryWrapper);
        if (timeRules.size() == 0) {
            return ResultVO.fail();
        }
        Integer purpose = timeRules.get(0).getAttendancePurpose();
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setUserId(SecurityAuthUtil.getLoginId());
        attendanceRecord.setAttendanceMode(AttendanceModeEnum.QRCode.getCode());
        attendanceRecord.setCreateTime(LocalDateTime.now());
        attendanceRecord.setLocationLat(latitude);
        attendanceRecord.setLocationLon(longitude);
        attendanceRecord.setAttendancePurpose(purpose);
        attendanceRecordService.save(attendanceRecord);
        return ResultVO.success();
    }
}
