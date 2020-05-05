package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.entity.AttendanceRulesTime;
import com.stan.server.mapper.AttendanceRulesTimeMapper;
import com.stan.server.service.AttendanceRulesTimeService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@Service
public class AttendanceRulesTimeServiceImpl extends ServiceImpl<AttendanceRulesTimeMapper, AttendanceRulesTime> implements AttendanceRulesTimeService {

    @Override
    public void setDay(LocalDate date, Integer needWorking) {
        QueryWrapper<AttendanceRulesTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("effective_date", date.toString());
        AttendanceRulesTime attendanceRulesTime = getOne(queryWrapper);
        if (attendanceRulesTime == null) {
            attendanceRulesTime = new AttendanceRulesTime();
            attendanceRulesTime.setEffectiveDate(date);
            attendanceRulesTime.setStatus(needWorking);
            save(attendanceRulesTime);
        } else {
            if (!attendanceRulesTime.getStatus().equals(needWorking)) {
                attendanceRulesTime.setStatus(needWorking);
                updateById(attendanceRulesTime);
            }
        }
    }

    public boolean needWorking(LocalDate date) {
        QueryWrapper<AttendanceRulesTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("effective_date", date.toString());
        AttendanceRulesTime attendanceRulesTime = getOne(queryWrapper);
        if (attendanceRulesTime != null)
            return attendanceRulesTime.getStatus() == 1;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return !dayOfWeek.equals(DayOfWeek.SATURDAY) && !dayOfWeek.equals(DayOfWeek.SUNDAY);
    }
}
