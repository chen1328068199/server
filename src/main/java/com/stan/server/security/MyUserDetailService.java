package com.stan.server.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.model.SysUser;
import com.stan.server.mapper.UserMapper;
import com.stan.server.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserVO userVO = userMapper.getUserInfoByName(userName);
        if (userVO == null || ObjectUtils.isEmpty(userVO)) {
            throw new UsernameNotFoundException("该用户不存在！");
        }
        //角色
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        String[] roles = userVO.getRoles().split(",");
        for (int i = 0; i < roles.length; i++) {
            collection.add(new SimpleGrantedAuthority(roles[i]));
        }
        return new User(userVO.getUserName(), userVO.getUserPassword(), collection);
    }
}
