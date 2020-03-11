package com.stan.server.service;

public interface AttendanceService {
    String getQRCodeAttendanceKey();
    String updateQRCodeAttendanceKey(Integer length);
}
