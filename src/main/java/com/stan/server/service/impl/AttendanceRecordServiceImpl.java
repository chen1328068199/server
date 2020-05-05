package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.entity.AttendanceRecord;
import com.stan.server.entity.Department;
import com.stan.server.enums.AttendanceModeEnum;
import com.stan.server.enums.RecordTypeEnum;
import com.stan.server.mapper.AttendanceRecordMapper;
import com.stan.server.model.RecordRequestParam;
import com.stan.server.model.User;
import com.stan.server.model.vo.AttendanceRecordVO;
import com.stan.server.model.vo.AttendanceVO;
import com.stan.server.service.AttendanceRecordService;
import com.stan.server.service.DepartmentService;
import com.stan.server.service.UserService;
import com.stan.server.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public Page<AttendanceRecordVO> pageOrderByAttendanceTime(Page<AttendanceRecord> page, RecordRequestParam requestParam) {
        // 查询员工信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id", "user_code", "department_id");
        userQueryWrapper.eq(requestParam.getDepartmentId() != null, "department_id", requestParam.getDepartmentId());
        userQueryWrapper.like(requestParam.getUserCode() != null && !requestParam.getUserCode().trim().equals(""),
                "user_code", requestParam.getUserCode());
        List<User> users = userService.list(userQueryWrapper);
        Page<AttendanceRecordVO> voPage = new Page<>(page.getCurrent(), page.getSize());
        if (users.size() == 0)
            return voPage;
        List<Integer> list = users.stream().map(User::getId).collect(Collectors.toList());
        Map<Integer, User> map = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        // 查询考勤记录信息
        QueryWrapper<AttendanceRecord> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.ge(requestParam.getStartDate() != null, "attendance_time", requestParam.getStartDate());
        recordQueryWrapper.le(requestParam.getEndDate() != null, "attendance_time", requestParam.getEndDate());
        recordQueryWrapper.eq(requestParam.getAttendanceMode() != null, "attendance_mode", requestParam.getAttendanceMode());
        recordQueryWrapper.eq(requestParam.getType() != null, "type", requestParam.getType());
        recordQueryWrapper.like(requestParam.getUserName() != null && !requestParam.getUserName().trim().equals(""),
                "user_name", requestParam.getUserName());
        recordQueryWrapper.in(list.size() > 0, "user_id", list);
        super.page(page, recordQueryWrapper);
        List<AttendanceRecord> records = page.getRecords();
        if (records == null || records.size() == 0)
            return voPage;
        // 封装结果
        List<AttendanceRecordVO> vos = new ArrayList<>(records.size());
        Map<Integer, String> departmentMap = new HashMap<>();
        for (AttendanceRecord record : records) {
            AttendanceRecordVO vo = new AttendanceRecordVO();
            Integer userId = record.getUserId();
            User user = map.get(userId);
            if (user != null) {
                vo.setUserCode(user.getUserCode());
                Integer departmentId = user.getDepartmentId();
                if (departmentId != null) {
                    String departmentName = departmentMap.get(departmentId);
                    if (departmentName == null) {
                        Department department = departmentService.getById(departmentId);
                        departmentName = department.getName();
                        departmentMap.put(departmentId, departmentName);
                    }
                    vo.setDepartment(departmentName);
                }
            }
            vo.setId(record.getId());
            vo.setUserId(userId);
            vo.setUserName(record.getUserName());
            vo.setAttendanceTime(record.getAttendanceTime());
            vo.setAttendanceMode(AttendanceModeEnum.getMessageByCode(record.getAttendanceMode()));
            vo.setAddress(record.getAddress());
            vo.setType(RecordTypeEnum.getMessageByCode(record.getType()));
            vo.setLeaveId(record.getLeaveId());
            vos.add(vo);
        }
        voPage.setRecords(vos);
        return voPage;
    }

    @Override
    public AttendanceVO getAttendanceRecordFromUser(Integer id) {
        QueryWrapper<AttendanceRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        queryWrapper.ge("attendance_time", LocalDate.now().toString());
        List<AttendanceRecord> list = list(queryWrapper);
        if (list.size() == 0)
            ResultVO.result(200, "当天没有考勤记录");
        AttendanceVO vo = new AttendanceVO();
        vo.setLeave(0);
        for (AttendanceRecord attendanceRecord : list) {
            if (RecordTypeEnum.LEAVE.getCode().equals(attendanceRecord.getType())) {
                vo.setLeave(1);
                vo.setBeginWorking(0);
                vo.setEndWorking(0);
                return vo;
            }
            if (RecordTypeEnum.BEGIN_WORKING.getCode().equals(attendanceRecord.getType())) {
                vo.setBeginWorking(1);
            }
            if (RecordTypeEnum.END_WORKING.getCode().equals(attendanceRecord.getType())) {
                vo.setEndWorking(1);
            }
        }
        return vo;
    }
}
