package com.stan.server.oauth2;

import com.stan.server.security.MyUserDetailService;
import com.stan.server.granter.WeChatTokenGranter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 废弃
 */
//@Configuration
//@EnableAuthorizationServer
//@Slf4j
public class MyAuthorizationServerConfigureAdapter extends AuthorizationServerConfigurerAdapter {

//    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
    private DataSource dataSource;

//    @Autowired
    private MyUserDetailService userDetailService;

//    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        /*设置签名*/
        accessTokenConverter.setSigningKey("witnessstan");
        return accessTokenConverter;
    }

    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    public TokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter());
        //endpoints.authenticationManager(authenticationManager).tokenStore(jdbcTokenStore()).tokenEnhancer(jwtAccessTokenConverter()).userDetailsService(userDetailService);
        List<TokenGranter> tokenGranters = getTokenGranters(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        //log.error(tokenGranters.toString());
        //log.error(endpoints.getTokenGranter().toString());
        tokenGranters.add(endpoints.getTokenGranter());
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jdbcTokenStore())
                .tokenEnhancer(jwtAccessTokenConverter())
                .tokenGranter(new CompositeTokenGranter(tokenGranters))
                .userDetailsService(userDetailService);

    }

    //新增的授权类型
    private List<TokenGranter> getTokenGranters(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        return new ArrayList<>(Arrays.asList(
                new WeChatTokenGranter(tokenServices, clientDetailsService, requestFactory, userDetailService)
        ));
    }


//    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置客户端基本信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security.allowFormAuthenticationForClients();
        security.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
