package com.stan.server.service;

import com.stan.server.utils.ResultVO;

public interface LoginService {
    ResultVO<String> getWxOpenId(String jsCode);
}
