package com.stan.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.entity.AttendanceRules;
import com.stan.server.enums.AttendancePurposeEnum;
import com.stan.server.enums.StatusEnum;
import com.stan.server.service.AttendanceRulesService;
import com.stan.server.utils.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

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

