package com.stan.server.service.impl;

import com.stan.server.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@CacheConfig(cacheNames = "attendance")
public class AttendanceServiceImpl implements AttendanceService {

//    @Autowired
//    private AttendanceRulesService attendanceRulesService;

    @Override
    @Cacheable(value = "QRCodeCache", key = "#attendanceCacheKey")
    public String getQRCodeAttendanceKey(String attendanceCacheKey) {
        return null;
    }

    @Override
    @CachePut(value = "QRCodeCache", key = "#attendanceCacheKey")
    public String updateQRCodeAttendanceKey(String attendanceCacheKey, Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    @Override
    public boolean attendance(double longitude, double latitude) {
        return false;
    }
}