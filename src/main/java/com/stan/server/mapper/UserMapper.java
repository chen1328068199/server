package com.stan.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stan.server.model.SysUser;
import com.stan.server.model.vo.UserVO;

public interface UserMapper extends BaseMapper<SysUser> {
    int deleteBySb(Integer id);

    UserVO getUserInfoById(Integer id);

    UserVO getUserInfoByName(String userName);

    UserVO getUserInfoByOpenId(String openId);
}
