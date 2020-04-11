package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.bean.AttendanceRulesTime;
import com.stan.server.mapper.AttendanceRulesTimeMapper;
import com.stan.server.service.AttendanceRulesTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-04-04
 */
@Service
public class AttendanceRulesTimeServiceImpl extends ServiceImpl<AttendanceRulesTimeMapper, AttendanceRulesTime> implements AttendanceRulesTimeService {

    @Autowired
    private AttendanceRulesTimeMapper attendanceRulesTimeMapper;

    @Override
    public AttendanceRulesTime getRulesByDate(LocalDate date) {
        QueryWrapper<AttendanceRulesTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("effective_date", date);
        return attendanceRulesTimeMapper.selectOne(queryWrapper);
    }
}
