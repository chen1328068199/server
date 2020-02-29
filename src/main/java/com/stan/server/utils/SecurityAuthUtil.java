package com.stan.server.utils;

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
		User authenUser = (User)authenObj.getPrincipal();
		return authenUser.getUsername();
	}
	
	/**
	 * 直接获取当前用户的认证用户信息
	 * @return
	 */
	public static User getAuthenticationUser() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authenObj.getPrincipal();
		return user;
	}

}
