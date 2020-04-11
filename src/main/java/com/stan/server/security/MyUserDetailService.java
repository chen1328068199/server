//package com.stan.server.security;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.stan.server.model.SysUser;
//import com.stan.server.mapper.UserMapper;
//import com.stan.server.model.vo.UserVO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Iterator;
//
///**
// * 废弃
// */
////@Component
////@Slf4j
//public class MyUserDetailService implements UserDetailsService {
//
////    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        UserVO userVO = userMapper.getUserInfoByName(userName);
//        if (userVO == null || ObjectUtils.isEmpty(userVO)) {
//            throw new UsernameNotFoundException("该用户不存在！");
//        }
//        //角色
//        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
//        String[] roles = userVO.getRoles().split(",");
//        for (int i = 0; i < roles.length; i++) {
//            collection.add(new SimpleGrantedAuthority(roles[i]));
//        }
//        return new User(userVO.getUserName(), userVO.getUserPassword(), collection);
//    }
//
//    public UserDetails loadUserByOpenId(String openId, String password) {
//        if (StringUtils.isEmpty(openId)) {
//            throw new InvalidGrantException("无效的openId");
//        }
//        // 判断成功后返回用户细节
//        //return new User(phone, "", AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user,root"));
//        UserVO userVO = userMapper.getUserInfoByOpenId(openId);
//        //角色
//        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
//        String[] roles = userVO.getRoles().split(",");
//        for (int i = 0; i < roles.length; i++) {
//            collection.add(new SimpleGrantedAuthority(roles[i]));
//        }
//        return new User(userVO.getUserName(), "", collection);
//    }
//}
