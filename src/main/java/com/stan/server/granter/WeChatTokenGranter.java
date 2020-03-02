package com.stan.server.granter;

import com.stan.server.security.MyUserDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * 废弃
 */
public class WeChatTokenGranter extends MyAbstractTokenGranter {

    private MyUserDetailService myUserDetailService;

    public WeChatTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, MyUserDetailService myUserDetailService) {
        super(tokenServices, clientDetailsService, requestFactory, "wechat");
        this.myUserDetailService = myUserDetailService;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String openId = parameters.get("open_id");
        String password = parameters.get("password");
        return myUserDetailService.loadUserByOpenId(openId, password);
    }
}
