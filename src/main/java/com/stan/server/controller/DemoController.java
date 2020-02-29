package com.stan.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stan.server.model.SysUser;
import com.stan.server.service.UserService;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.ResultVOUtil;
import com.stan.server.utils.SecurityAuthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
@Slf4j
@Api(tags = "测试接口")
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("test")
    @ApiOperation("测试")
    public ResultVO test(@RequestParam Integer id) {
        userService.deleteBySb(id);
        return ResultVOUtil.success();
    }

    @GetMapping("falied")
    @ApiOperation("失败")
    public ResultVO failed() {
        return ResultVOUtil.error();
    }

    @GetMapping("user")
    @ApiOperation("用户")
    public ResultVO auth() {
        return ResultVOUtil.success(SecurityAuthUtil.getAuthenticationUser());
    }

    @GetMapping("gettest")
    public ResultVO<SysUser> getTest(@RequestParam String name) {
        QueryWrapper<SysUser> demoQueryWrapper = new QueryWrapper<>();
        demoQueryWrapper.lambda().eq(SysUser::getUserName, name);
        return ResultVOUtil.success(userService.getOne(demoQueryWrapper));
    }

    /**
     * 单表分页测试
     * @param current
     * @param size
     * @return
     */
    @GetMapping("pagetest")
    @ApiOperation(value="说明方法的用途、作用", notes="方法的备注说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "分页大小", paramType = "query", required = true, dataType = "Integer")
    })
    public ResultVO<IPage> pageTest(@RequestParam Integer current, @RequestParam Integer size) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        return ResultVOUtil.success(userService.page(new Page<>(current, size), queryWrapper));
    }

    @GetMapping("userInfo")
    @ApiOperation("用户信息")
    public ResultVO auth(@RequestParam Integer id) {
        return ResultVOUtil.success(userService.getUserInfoById(id));
    }
}
