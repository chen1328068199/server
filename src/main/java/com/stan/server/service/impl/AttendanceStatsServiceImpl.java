package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.entity.AttendanceStats;
import com.stan.server.enums.AttendanceModeEnum;
import com.stan.server.enums.AttendanceStatusEnum;
import com.stan.server.mapper.AttendanceStatsMapper;
import com.stan.server.mapper.UserMapper;
import com.stan.server.model.AttendanceStatsRequestParam;
import com.stan.server.model.vo.AttendanceRecordStatVO;
import com.stan.server.model.vo.AttendanceStatLineChartVO;
import com.stan.server.service.AttendanceStatsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-05-04
 */
@Service
public class AttendanceStatsServiceImpl extends ServiceImpl<AttendanceStatsMapper, AttendanceStats> implements AttendanceStatsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<AttendanceStatLineChartVO> getStats(AttendanceStatsRequestParam requestParam) {
        LocalDate endDate = requestParam.getEndDate();
        if (endDate == null) {
            requestParam.setEndDate(LocalDate.now().minusDays(1));
            endDate = requestParam.getEndDate();
        }
        if (requestParam.getStartDate() == null)
            requestParam.setStartDate(endDate.minusDays(7));

        List<AttendanceStatLineChartVO> vos = new LinkedList<>();
        for (LocalDate date = requestParam.getStartDate(); date.compareTo(endDate) <= 0; date = date.plusDays(1)) {
            AttendanceStatLineChartVO vo = new AttendanceStatLineChartVO();
            for (AttendanceStatusEnum value : AttendanceStatusEnum.values()) {
                QueryWrapper<AttendanceStats> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("attendance_date", date);
                if (requestParam.getDepartmentId() != null) {
                    List<Integer> userIds = userMapper.listUserIdByDepartment(requestParam.getDepartmentId());
                    queryWrapper.in(userIds != null && userIds.size() > 0, "user_id", userIds);
                }
                queryWrapper.eq(requestParam.getAttendanceWay() != null, "attendance_way", requestParam.getAttendanceWay());
                queryWrapper.eq("status", value.getCode());
                int count = count(queryWrapper);
                switch (value) {
                    case LATE:
                        vo.setLateNum(count);
                        break;
                    case EARLY:
                        vo.setEarlyNum(count);
                        break;
                    case LEAVE:
                        vo.setLeaveNum(count);
                        break;
                    case NORMAL:
                        vo.setNormalNum(count);
                        break;
                    case ABSENCE:
                        vo.setAbsenceNum(count);
                        break;
                    case NOT_END:
                        vo.setNotEndNum(count);
                        break;
                    case OVERTIME:
                        vo.setOvertimeNum(count);
                        break;
                    case NOT_BEGIN:
                        vo.setNotBeginNum(count);
                        break;
                }
            }
            vo.setDate(date);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AttendanceRecordStatVO> attendanceStatsService(Integer userId, LocalDate date) {
        QueryWrapper<AttendanceStats> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("attendance_date", date.toString());
        List<AttendanceStats> list = list(queryWrapper);
        List<AttendanceRecordStatVO> vos = new ArrayList<>();
        for (AttendanceStats attendanceStats : list) {
            AttendanceRecordStatVO vo = new AttendanceRecordStatVO();
            vo.setId(attendanceStats.getId());
            vo.setStatus(AttendanceStatusEnum.getMessageByCode(attendanceStats.getStatus()));
            vo.setAttendanceWay(AttendanceModeEnum.getMessageByCode(attendanceStats.getAttendanceWay()));
            vo.setBeginWorkingTime(attendanceStats.getBeginWorkingTime());
            vo.setEndWorkingTime(attendanceStats.getEndWorkingTime());
            vos.add(vo);
        }
        return vos;
    }
}
