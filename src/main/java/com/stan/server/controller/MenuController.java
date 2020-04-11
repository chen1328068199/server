package com.stan.server.controller;

import com.stan.server.service.MenuService;
import com.stan.server.utils.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("list")
    @ApiOperation("获得角色可查看菜单")
    public ResultVO<String> list() {
        return menuService.getMenuFromRole();
    }
}

