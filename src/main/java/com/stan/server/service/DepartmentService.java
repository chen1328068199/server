package com.stan.server.service;

import com.stan.server.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ren
 * @since 2020-04-06
 */
public interface DepartmentService extends IService<Department> {

    void delete(Integer id);
}
