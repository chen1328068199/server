package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.entity.Menu;
import com.stan.server.entity.Permission;
import com.stan.server.mapper.PermissionMapper;
import com.stan.server.model.vo.MenuVO;
import com.stan.server.model.vo.PermissionVO;
import com.stan.server.service.MenuService;
import com.stan.server.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private MenuService menuService;

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

    @Override
    public List<MenuVO> listPermission() {
        return listPermission(Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public List<MenuVO> listPermission(Collection<Integer> menuIds, Collection<Integer> permissionIds) {
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.in(menuIds != null && menuIds.size() > 0, "id", menuIds);
        List<Menu> menus = menuService.list(menuQueryWrapper);
        if (menus.size() == 0)
            return Collections.EMPTY_LIST;
        Map<Integer, MenuVO> map = new HashMap<>();
        for (Menu menu : menus) {
            String name = menu.getName();
            if (name.indexOf("_") == 0)
                continue;
            MenuVO menuVO = new MenuVO();
            menuVO.setId(menu.getId());
            menuVO.setName(name);
            menuVO.setTitle(menu.getTitle());
            map.put(menu.getId(), menuVO);
        }
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.in(menuIds != null && menuIds.size() > 0, "menu_id", menuIds);
        permissionQueryWrapper.in(permissionIds != null && permissionIds.size() > 0, "id", permissionIds);
        List<Permission> permissions = list(permissionQueryWrapper);
        for (Permission permission : permissions) {
            Integer menuId = permission.getMenuId();
            MenuVO menuVO = map.get(menuId);
            if (menuVO == null)
                continue;
            PermissionVO permissionVO = new PermissionVO();
            permissionVO.setId(permission.getId());
            permissionVO.setPermission(permission.getPermission());
            permissionVO.setTitle(permission.getTitle());
            menuVO.getPermissions().add(permissionVO);
        }
        return new ArrayList<>(map.values());
    }
}
