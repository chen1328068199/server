package com.stan.server.service;

public interface AttendanceService {
    String getQRCodeAttendanceKey(String attendanceCacheKey);
    String updateQRCodeAttendanceKey(String attendanceCacheKey, Integer length);
    boolean attendance(double longitude, double latitude);
}
