package com.stan.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.entity.AttendanceApproval;
import com.stan.server.model.FillApprovalRequestParam;
import com.stan.server.model.LeaveApprovalRequestParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ren
 * @since 2020-05-04
 */
public interface AttendanceApprovalService extends IService<AttendanceApproval> {

    void requestLeave(LeaveApprovalRequestParam requestParam);

    void requestFill(FillApprovalRequestParam requestParam);
}
