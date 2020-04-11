package com.stan.server.service;

import com.stan.server.bean.AttendanceRulesTime;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ren
 * @since 2020-04-04
 */
public interface AttendanceRulesTimeService extends IService<AttendanceRulesTime> {
    AttendanceRulesTime getRulesByDate(LocalDate date);
}
