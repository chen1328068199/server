package com.stan.server.service;

import com.stan.server.bean.Menu;
import com.stan.server.bean.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.utils.ResultVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
public interface RoleService extends IService<Role> {
    Role getSysRoleByRole(String role);

    List<Role> getRolesFromUser(Integer userId);

    ResultVO<Object> addRole(Role role, String menus, String permissionIds);

    ResultVO<Object> updateRole(Role role, String menus, String permissionIds);

    ResultVO<Object> deleteRole(String role);

    List<Menu> getMenusFromRoles(Collection<String> roles);
}
