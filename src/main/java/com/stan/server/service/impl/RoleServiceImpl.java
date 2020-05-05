package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.entity.Menu;
import com.stan.server.entity.Role;
import com.stan.server.mapper.RoleMapper;
import com.stan.server.model.vo.MenuVO;
import com.stan.server.model.vo.PermissionVO;
import com.stan.server.service.PermissionService;
import com.stan.server.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private PermissionService permissionService;

    @Override
    public Role getSysRoleByRole(String role) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("role", role);
        return getOne(roleQueryWrapper);
    }

    @Override
    public List<Role> listSysRolesByRoles(Collection<String> roles) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.in("role", roles);
        return list(roleQueryWrapper);
    }

    @Override
    public List<Role> listSysRolesFromUser(Integer userId) {
        return roleMapper.listSysRolesFromUser(userId);
    }

    @Override
    public List<Role> listRolesFromUser(Integer userId) {
        return roleMapper.listRolesFromUser(userId);
    }

    @Override
    public ResultVO<Object> addRole(Role role, String menus, String permissionIds) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role.getRole());
        int count = count(queryWrapper);
        if (count > 0)
            return ResultVO.fail("该角色已存在");
        save(role);
        if (menus != null && !menus.trim().equals(""))
            roleMapper.roleAddMenu(role.getId(), StringUtils.DotStringToIntArray(menus));
        if (permissionIds != null && !permissionIds.trim().equals(""))
            roleMapper.roleAddPermission(role.getId(), StringUtils.DotStringToIntArray(permissionIds));
        return ResultVO.success();
    }

    @Override
    @Transactional
    public ResultVO<Object> updateRole(Role role, String menuIds, String permissionIds) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", role.getId());
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
        if (role.getRole() != null && !role.getRole().equals(role1.getRole())) {
            queryWrapper.eq("role", role.getRole());
            int count = count(queryWrapper);
            if (count > 0)
                return ResultVO.fail("该角色已存在");
        }
        if (role.getName() != null || role.getDescribe() != null || role.getRole() != null)
            updateById(role);
        if (menuIds != null && !menuIds.trim().equals("")) {
            roleMapper.deleteMenusFromRoleId(role.getId());
            roleMapper.roleAddMenu(role.getId(), StringUtils.DotStringToIntArray(menuIds));
        }
        if (permissionIds != null && !permissionIds.trim().equals("")) {
            roleMapper.deletePermissionsFromRoleId(role.getId());
            roleMapper.roleAddPermission(role.getId(), StringUtils.DotStringToIntArray(permissionIds));
        }
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
    public List<Menu> listMenusFromRoles(Collection<String> roles) {
        List<Integer> roleIds = roleMapper.listRoleIdByRole(roles);
        List<Menu> menus = roleMapper.listMenusByRoleIds(roleIds);
        Set<Integer> set = new HashSet<>();
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Integer menuId = iterator.next().getId();
            if (set.contains(menuId))
                iterator.remove();
            else
                set.add(menuId);
        }
        return menus;
    }

    @Override
    public List<Menu> listMenusFromRoleIds(Collection<Integer> roleIds) {
        return roleMapper.listMenusByRoleIds(roleIds);
    }

    @Override
    public List<String> listPermissionsByRole(Integer id) {
        List<Integer> permissionIds = roleMapper.listPermissionIdsByRoleId(id);
        List<Menu> menus = roleMapper.listMenusByRoleId(id);
        List<Integer> menuIds = menus.stream().map(Menu::getId).collect(Collectors.toList());
        List<MenuVO> vos = permissionService.listPermission(menuIds, permissionIds);
        List<String> list = new LinkedList<>();
        for (MenuVO vo : vos) {
            StringBuilder menuStr = new StringBuilder("menuId-");
            list.add(menuStr.append(vo.getId()).toString());
            List<PermissionVO> permissions = vo.getPermissions();
            if (permissions != null)
                for (PermissionVO permission : permissions) {
                    StringBuilder permissionStr = new StringBuilder("permissionId-");
                    list.add(permissionStr.append(permission.getId()).toString());
                }
        }
        return list;
    }
}
