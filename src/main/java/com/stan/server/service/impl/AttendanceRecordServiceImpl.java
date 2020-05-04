package com.stan.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.entity.AttendanceRecord;
import com.stan.server.mapper.AttendanceRecordMapper;
import com.stan.server.service.AttendanceRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考勤记录表 服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@Service
public class AttendanceRecordServiceImpl extends ServiceImpl<AttendanceRecordMapper, AttendanceRecord> implements AttendanceRecordService {

}
