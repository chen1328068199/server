package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.bean.Menu;
import com.stan.server.bean.Role;
import com.stan.server.mapper.RoleMapper;
import com.stan.server.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getSysRoleByRole(String role) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("role", role);
        return getOne(roleQueryWrapper);
    }

    @Override
    public List<Role> getRolesFromUser(Integer userId) {
        return roleMapper.getRolesFromUser(userId);
    }

    @Override
    public ResultVO<Object> addRole(Role role, String menus, String permissionIds) {
        save(role);
        roleMapper.roleAddMenu(role.getId(), StringUtils.DotStringToIntArray(menus));
        roleMapper.roleAddPermission(role.getId(), StringUtils.DotStringToIntArray(permissionIds));
        return ResultVO.success();
    }

    @Override
    public ResultVO<Object> updateRole(Role role, String menus, String permissionIds) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role.getRole());
//        queryWrapper.eq("status", role.getStatus());
        Role role1 = getOne(queryWrapper);
        if (role1 == null)
            return ResultVO.fail("该角色不存在");
        queryWrapper.clear();
        if (role.getName() != null && !role.getName().equals(role1.getName())) {
            queryWrapper.eq("name", role.getName());
            int count = count(queryWrapper);
            if (count > 0)
                return ResultVO.fail("该角色名已存在");
        }
        updateById(role);
        roleMapper.deleteMenusFromRoleId(role.getId());
        roleMapper.roleAddMenu(role.getId(), StringUtils.DotStringToIntArray(menus));
        roleMapper.deletePermissionsFromRoleId(role.getId());
        roleMapper.roleAddPermission(role.getId(), StringUtils.DotStringToIntArray(permissionIds));
        return ResultVO.success();
    }

    @Override
    public ResultVO<Object> deleteRole(String role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role);
        Role sysRole = getOne(queryWrapper);
        roleMapper.deleteMenusFromRoleId(sysRole.getId());
        roleMapper.deletePermissionsFromRoleId(sysRole.getId());
        remove(queryWrapper);
        return ResultVO.success();
    }

    @Override
    public List<Menu> getMenusFromRoles(Collection<String> roles) {
        List<Integer> roleIds = roleMapper.listRoleIdByRole(roles);
        return roleMapper.listMenuByRoleIds(roleIds);
    }
}
