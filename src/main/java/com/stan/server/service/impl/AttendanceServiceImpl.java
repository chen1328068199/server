package com.stan.server.service.impl;

import com.stan.server.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig(cacheNames = "attendance")
public class AttendanceServiceImpl implements AttendanceService {

    @Override
    @Cacheable(key = "attendance_key")
    public String getAttendanceKey() {
        return null;
    }

    @Override
    @CachePut(key = "attendance_key")
    public String updateAttendanceKey(Integer length) {
        return RandomStringUtils.randomAlphabetic(13);
    }
}