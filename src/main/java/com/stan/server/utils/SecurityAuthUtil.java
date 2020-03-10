package com.stan.server.utils;

import com.stan.server.model.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityAuthUtil {
	
	/**
	 * 直接获取当前用户的登录名
	 * @return
	 */
	public static String getLoginName() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails authenUser = (MyUserDetails) authenObj.getPrincipal();
		return authenUser.getUsername();
	}
	
	/**
	 * 直接获取当前用户的认证用户信息
	 * @return
	 */
	public static MyUserDetails getAuthenticationUser() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = (MyUserDetails) authenObj.getPrincipal();
		return user;
	}

}
