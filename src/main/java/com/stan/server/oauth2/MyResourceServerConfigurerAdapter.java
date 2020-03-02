package com.stan.server.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 废弃
 */
//@Configuration
//@EnableResourceServer
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                .antMatchers( "/swagger-ui.html","/doc.html","/webjars/**","/favicon.ico","/v2/**","/swagger-resources/**")
                .permitAll()
                //以上的url不需要token，以下为需要token验证，或者是需要token+角色权限的url
                .antMatchers("/users/testAuth")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
        //跨域设置
        http.cors()
                .and()
                .csrf().disable();
    }
}
