package com.stan.server.service.impl;

import com.stan.server.model.wx.WxLoginInfo;
import com.stan.server.service.LoginService;
import com.stan.server.utils.ResultVO;
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

    /**
     * 微信登录获取openId
     * -1	系统繁忙，此时请开发者稍候再试
     * 0	请求成功
     * 40029	code 无效
     * 45011	频率限制，每个用户每分钟100次
     * @param jsCode
     * @return
     */
    @Override
    public ResultVO<String> getWxOpenId(String jsCode) {
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session");
        url.append(String.format("?appid=%s", APP_ID));
        url.append(String.format("&secret=%s", SECRET));
        url.append(String.format("&js_code=%s", jsCode));
        url.append(String.format("&grant_type=%s", "authorization_code"));
        int round = 0;
        WxLoginInfo wxLoginInfo;
        while (true) {
            wxLoginInfo = restTemplate.getForObject(url.toString(), WxLoginInfo.class);
            if (wxLoginInfo == null)
                continue;
            if (wxLoginInfo.getErrcode() == null && wxLoginInfo.getOpenid() != null)
                break;
            else if (wxLoginInfo.getOpenid() == null)
                continue;
            else if (wxLoginInfo.getErrcode() == -1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (wxLoginInfo.getErrcode().equals(45011)) {
                return ResultVO.result(45011, "频率限制，每个用户每分钟100次");
            } else if (wxLoginInfo.getErrcode().equals(40029)) {
                return ResultVO.result(40029, "code 无效");
            } else if (wxLoginInfo.getErrcode() == 0)
                break;
            if (round++ == 5)
                return ResultVO.fail();
        }
        log.info(wxLoginInfo.toString());
        return ResultVO.success(wxLoginInfo.getOpenid());
    }
}
