package com.stan.server.service;

public interface AttendanceService {
    /**
     * 根据缓存名字查找缓存
     * @param attendanceCacheKey
     * @return
     */
    String getCodeAttendanceKey(String attendanceCacheKey);

    /**
     * 生成指定长度随机字符串并存入指定缓存名的缓存
     * @param attendanceCacheKey
     * @param length
     * @return
     */
    String updateCodeAttendanceKey(String attendanceCacheKey, Integer length);

    /**
     * 将指定字符串存入指定缓存
     * @param attendanceCacheKey
     * @param code
     * @return
     */
    String updateCodeAttendanceKey(String attendanceCacheKey, String code);
    boolean attendance(double longitude, double latitude);
}
