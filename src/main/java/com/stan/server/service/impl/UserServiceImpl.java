package com.stan.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.model.SysUser;
import com.stan.server.mapper.UserMapper;
import com.stan.server.model.vo.UserVO;
import com.stan.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void deleteBySb(Integer id) {
        userMapper.deleteBySb(id);
    }

    @Override
    public UserVO getUserInfoById(Integer id) {
        return userMapper.getUserInfoById(id);
    }

    @Override
    public UserVO getUserInfoByName(String userName) {
        return userMapper.getUserInfoByName(userName);
    }

}
