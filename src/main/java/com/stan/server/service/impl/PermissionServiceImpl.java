package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.bean.Permission;
import com.stan.server.bean.Role;
import com.stan.server.mapper.PermissionMapper;
import com.stan.server.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.utils.ResultVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public ResultVO<Object> updatePermission(Permission permission) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", permission.getId());
        Permission permission1 = getOne(queryWrapper);
        if (permission.getPermission() != null && !permission1.getPermission().equals(permission.getPermission())) {
            queryWrapper.clear();
            queryWrapper.eq("permission", permission.getPermission());
            int count = count(queryWrapper);
            if (count > 0)
                return ResultVO.fail("该权限已存在");
        }
        updateById(permission);
        return ResultVO.success();
    }
}
