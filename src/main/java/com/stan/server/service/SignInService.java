package com.stan.server.service;

import com.stan.server.utils.ResultVO;

public interface SignInService {
    ResultVO<Object> signIn(String openId, double longitude, double latitude);
}
