package com.stan.server.granter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

@Deprecated
public abstract class MyAbstractTokenGranter extends AbstractTokenGranter {

    protected MyAbstractTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());
        UserDetails details = getUserDetails(parameters);
        if (details == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        OAuth2Authentication authentication = super.getOAuth2Authentication(client, tokenRequest);
        authentication.setDetails(details);
        authentication.setAuthenticated(true);
        return authentication;
    }

    protected abstract UserDetails getUserDetails(Map<String, String> parameters);
}
