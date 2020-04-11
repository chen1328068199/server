package com.stan.server.mapper;
 
import com.stan.server.bean.Menu;
import com.stan.server.bean.Role;
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

    List<Role> getRolesFromUser(Integer userId);

    void roleAddPermission(@Param("roleId") Integer id, @Param("permissionIds") int[] permissionIds);

    void deletePermissionsFromRoleId(@Param("roleId") Integer id);

    void roleAddMenu(@Param("roleId") Integer id,  @Param("menuIds") int[] menuIds);

    void deleteMenusFromRoleId(@Param("roleId") Integer id);

    List<Integer> listRoleIdByRole(@Param("roles") Collection<String> roles);

    List<Menu> listMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);
}