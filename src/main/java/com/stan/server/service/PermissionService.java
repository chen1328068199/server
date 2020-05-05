package com.stan.server.service;

import com.stan.server.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.model.vo.MenuVO;
import com.stan.server.utils.ResultVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
public interface PermissionService extends IService<Permission> {

    ResultVO<Object> updatePermission(Permission permission);
    List<MenuVO> listPermission();
    List<MenuVO> listPermission(Collection<Integer> menuIds, Collection<Integer> permissionIds);

    String listPermissionsFromUser(Integer userId);
}
