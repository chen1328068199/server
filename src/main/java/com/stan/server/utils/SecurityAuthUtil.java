package com.stan.server.utils;

import com.stan.server.model.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityAuthUtil {
	
	/**
	 * 直接获取当前用户的登录名
	 * @return
	 */
	public static String getCurrentUserName() {
		return getCurrentUser().getUsername();
	}

	/**
	 * 直接获取当前用户ID
	 * @return
	 */
	public static int getCurrentUserId() {
		return getCurrentUser().getUserId();
	}
	
	/**
	 * 直接获取当前用户的认证用户信息
	 * @return
	 */
	public static MyUserDetails getCurrentUser() {
		Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
		return (MyUserDetails) authObj.getPrincipal();
	}

}
