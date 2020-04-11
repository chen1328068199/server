package com.stan.server.service;

import com.stan.server.bean.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.utils.ResultVO;

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
}
