package com.stan.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.entity.AttendanceRules;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
public interface AttendanceRulesService extends IService<AttendanceRules> {

    AttendanceRules getByAttendanceWay(Integer attendanceWay);
}
