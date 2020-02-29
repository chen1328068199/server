package com.stan.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.model.SysUser;
import com.stan.server.model.vo.UserVO;

public interface UserService extends IService<SysUser> {
    void deleteBySb(Integer id);
    UserVO getUserInfoById(Integer id);
    UserVO getUserInfoByName(String userName);
}
