package com.stan.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.entity.AttendanceRulesTime;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
public interface AttendanceRulesTimeService extends IService<AttendanceRulesTime> {

    void setDay(LocalDate date, Integer needWorking);

    boolean needWorking(LocalDate date);
}
