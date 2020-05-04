package com.stan.server;

import com.stan.server.service.AttendanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {

    @Autowired
    private AttendanceService attendanceService;

    @Test
    public void test() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("1234");
        System.out.println(bCryptPasswordEncoder.matches("12345", encode));
        System.out.println(bCryptPasswordEncoder.matches("1234", encode));
        System.out.println(bCryptPasswordEncoder.matches("145", encode));
        System.out.println(bCryptPasswordEncoder.matches("15", encode));
    }
}
