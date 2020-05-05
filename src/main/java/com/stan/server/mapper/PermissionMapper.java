package com.stan.server.mapper;
 
import com.stan.server.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> listPermissionsFromRoles(@Param("roleIds") Collection<Integer> roleIds);
}