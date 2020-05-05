package com.stan.server.mapper;
 
import com.stan.server.entity.Menu;
import com.stan.server.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
  * 角色表 Mapper 接口
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> listRolesFromUser(Integer userId);

    void roleAddPermission(@Param("roleId") Integer id, @Param("permissionIds") int[] permissionIds);

    void deletePermissionsFromRoleId(@Param("roleId") Integer id);

    void roleAddMenu(@Param("roleId") Integer id,  @Param("menuIds") int[] menuIds);

    void deleteMenusFromRoleId(@Param("roleId") Integer id);

    List<Integer> listRoleIdByRole(@Param("roles") Collection<String> roles);

    List<Menu> listMenusByRoleIds(@Param("roleIds") Collection<Integer> roleIds);

    List<Menu> listMenusByRoleId(@Param("roleId") Integer roleId);

    List<Integer> listPermissionIdsByRoleId(@Param("roleId") Integer id);

    List<Role> listSysRolesFromUser(@Param("userId") Integer userId);
}