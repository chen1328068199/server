package com.stan.server.service.impl;

import com.stan.server.entity.AttendanceApproval;
import com.stan.server.entity.AttendanceLeave;
import com.stan.server.entity.AttendanceRecord;
import com.stan.server.enums.*;
import com.stan.server.mapper.AttendanceApprovalMapper;
import com.stan.server.model.FillApprovalRequestParam;
import com.stan.server.model.LeaveApprovalRequestParam;
import com.stan.server.model.MyUserDetails;
import com.stan.server.service.AttendanceApprovalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.service.AttendanceLeaveService;
import com.stan.server.service.AttendanceRecordService;
import com.stan.server.utils.SecurityAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class AttendanceApprovalServiceImpl extends ServiceImpl<AttendanceApprovalMapper, AttendanceApproval> implements AttendanceApprovalService {

    @Autowired
    private AttendanceLeaveService attendanceLeaveService;
    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @Transactional
    @Override
    public void requestLeave(LeaveApprovalRequestParam requestParam) {
        MyUserDetails user = SecurityAuthUtil.getCurrentUser();
        // 填写请假信息
        AttendanceLeave attendanceLeave = new AttendanceLeave();
        LocalDate startDate = requestParam.getStartDate();
        attendanceLeave.setStartDate(startDate);
        LocalDate endDate = requestParam.getEndDate();
        attendanceLeave.setEndDate(endDate);
        attendanceLeave.setReason(requestParam.getReason());
        attendanceLeave.setType(requestParam.getType());
        attendanceLeaveService.save(attendanceLeave);
        // 向考勤记录表中插入记录并关联请假信息
        List<AttendanceRecord> list = new LinkedList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (LocalDate date = startDate; date.compareTo(endDate) < 1; date = date.plusDays(1)) {
            AttendanceRecord attendanceRecord = new AttendanceRecord();
            list.add(attendanceRecord);
            attendanceRecord.setUserId(user.getUserId());
            attendanceRecord.setUserName(user.getUsername());
            String format = dtf.format(date);
            LocalDateTime dateTime = LocalDateTime.parse(format, dtf);
            attendanceRecord.setAttendanceTime(dateTime);
            attendanceRecord.setType(RecordTypeEnum.LEAVE.getCode());
            attendanceRecord.setLeaveId(attendanceLeave.getId());
        }
        if (list.size() > 0)
            attendanceRecordService.saveBatch(list);
        // 填写审批信息
        AttendanceApproval attendanceApproval = new AttendanceApproval();
        attendanceApproval.setInitiator(user.getUserId());
        attendanceApproval.setReceiver(requestParam.getReceiver());
        attendanceApproval.setStatus(ApprovalStatusEnum.PENDING.getCode());
        attendanceApproval.setApprovalType(ApprovalTypeEnum.LEAVE.getCode());
        attendanceApproval.setRecordId(attendanceLeave.getId());
        attendanceApproval.setDescription(requestParam.getReason());
        save(attendanceApproval);
    }

    @Transactional
    @Override
    public void requestFill(FillApprovalRequestParam requestParam) {
        MyUserDetails currentUser = SecurityAuthUtil.getCurrentUser();
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setUserId(currentUser.getUserId());
        attendanceRecord.setUserName(currentUser.getUsername());
        attendanceRecord.setAttendanceTime(requestParam.getDateTime());
        attendanceRecord.setAttendanceMode(AttendanceModeEnum.FILL.getCode());
        attendanceRecord.setType(requestParam.getType());
        attendanceRecordService.save(attendanceRecord);
        // 填写审批信息
        AttendanceApproval attendanceApproval = new AttendanceApproval();
        attendanceApproval.setInitiator(currentUser.getUserId());
        attendanceApproval.setReceiver(requestParam.getReceiver());
        attendanceApproval.setStatus(ApprovalStatusEnum.PENDING.getCode());
        attendanceApproval.setApprovalType(ApprovalTypeEnum.FILL.getCode());
        attendanceApproval.setRecordId(attendanceRecord.getId());
        attendanceApproval.setDescription(requestParam.getDescription());
        save(attendanceApproval);
    }
}
