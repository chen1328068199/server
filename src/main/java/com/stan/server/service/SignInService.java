package com.stan.server.service;

import com.stan.server.enums.AttendanceModeEnum;
import com.stan.server.utils.ResultVO;

public interface SignInService {
    ResultVO<Object> signIn(String openId, AttendanceModeEnum mode, double longitude, double latitude, String address, Integer purpose);
}
