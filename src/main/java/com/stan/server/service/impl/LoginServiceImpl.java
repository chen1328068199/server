package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.model.SysUser;
import com.stan.server.model.wx.WxLoginInfo;
import com.stan.server.service.LoginService;
import com.stan.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${wx.app-id}")
    private String APP_ID;

    @Value("${wx.secret}")
    private String SECRET;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    /**
     * 微信登录获取openId并校验是否绑定账号
     * @param jsCode
     * @return
     */
    @Override
    public String getWxOpenId(String jsCode) {
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session");
        url.append(String.format("?appid=%s", APP_ID));
        url.append(String.format("&secret=%s", SECRET));
        url.append(String.format("&js_code=%s", jsCode));
        url.append(String.format("&grant_type=%s", "authorization_code"));
//        String s = restTemplate.getForObject(url.toString(), String.class);
//        log.info(s);
        WxLoginInfo wxLoginInfo = restTemplate.getForObject(url.toString(), WxLoginInfo.class);
        log.info(wxLoginInfo.toString());
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getOpenId, wxLoginInfo.getOpenid());
        SysUser sysUser = userService.getOne(queryWrapper);
        if (sysUser == null) {
            return null;
        }
        return wxLoginInfo.getOpenid();
    }
}
