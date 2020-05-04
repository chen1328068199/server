package com.stan.server.service;

import com.stan.server.entity.Menu;
import com.stan.server.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.model.vo.MenuVO;
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

    List<Role> listSysRolesByRoles(Collection<String> roles);

    List<Role> listSysRolesFromUser(Integer userId);

    List<Role> getRolesFromUser(Integer userId);

    ResultVO<Object> addRole(Role role, String menus, String permissionIds);

    ResultVO<Object> updateRole(Role role, String menus, String permissionIds);

    ResultVO<Object> deleteRole(String role);

    List<Menu> listMenusFromRoles(Collection<String> roles);

    List<Menu> listMenusFromRoleIds(Collection<Integer> roleIds);

    List<String> listPermissionsByRole(Integer id);


}
