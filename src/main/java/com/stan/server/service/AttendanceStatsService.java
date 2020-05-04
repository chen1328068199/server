package com.stan.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.entity.AttendanceStats;
import com.stan.server.model.AttendanceStatsRequestParam;
import com.stan.server.model.vo.AttendanceRecordStatVO;
import com.stan.server.model.vo.AttendanceStatVO;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ren
 * @since 2020-05-04
 */
public interface AttendanceStatsService extends IService<AttendanceStats> {

    List<AttendanceStatVO> getStats(AttendanceStatsRequestParam requestParam);

    List<AttendanceRecordStatVO> attendanceStatsService(Integer userId, LocalDate date);
}
