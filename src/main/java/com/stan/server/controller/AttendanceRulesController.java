package com.stan.server.controller;


import com.stan.server.service.AttendanceRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/attendanceRules")
public class AttendanceRulesController {

    @Autowired
    private AttendanceRulesService attendanceRulesService;


}

