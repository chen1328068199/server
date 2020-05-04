package com.stan.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.entity.AttendanceRecord;
import com.stan.server.model.RecordRequestParam;
import com.stan.server.model.vo.AttendanceRecordVO;
import com.stan.server.model.vo.AttendanceVO;

/**
 * <p>
 * 考勤记录表 服务类
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
public interface AttendanceRecordService extends IService<AttendanceRecord> {

    Page<AttendanceRecordVO> pageOrderByAttendanceTime(Page<AttendanceRecord> page, RecordRequestParam requestParam);

    AttendanceVO getAttendanceRecordFromUser(Integer id);
}
