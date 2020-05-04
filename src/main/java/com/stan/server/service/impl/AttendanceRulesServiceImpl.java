package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.entity.AttendanceRules;
import com.stan.server.enums.StatusEnum;
import com.stan.server.mapper.AttendanceRulesMapper;
import com.stan.server.service.AttendanceRulesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@Service
public class AttendanceRulesServiceImpl extends ServiceImpl<AttendanceRulesMapper, AttendanceRules> implements AttendanceRulesService {

    @Override
    public AttendanceRules getByAttendanceWay(Integer attendanceWay) {
        QueryWrapper<AttendanceRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("way", attendanceWay);
        queryWrapper.eq("status", StatusEnum.NORMAL);
        List<AttendanceRules> list = list(queryWrapper);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }
}
