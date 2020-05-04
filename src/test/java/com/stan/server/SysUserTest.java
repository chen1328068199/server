package com.stan.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.mapper.UserMapper;
import com.stan.server.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysUserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User sysUser = new User();
        sysUser.setUserName("test3");
        sysUser.setUserPassword(new BCryptPasswordEncoder().encode("passwd"));
        userMapper.insert(sysUser);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserName, sysUser.getUserName());
        userMapper.selectOne(queryWrapper);
        log.info(sysUser.toString());
        //demoMapper.deleteBySb(2);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void tset2() {
        String secret = passwordEncoder.encode("secret");
        log.error(secret);
    }
}
