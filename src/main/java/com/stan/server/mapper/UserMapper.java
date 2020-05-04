package com.stan.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stan.server.model.User;
import com.stan.server.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    UserVO getUserInfoById(Integer id);

    User getUserInfoByName(String userName);

    UserVO getUserInfoByOpenId(String openId);

    void updateUserRole(@Param("userId") Integer id, @Param("roleIds") int[] roleIds);

    void deleteRoleFromUser(@Param("userId") Integer id);

    List<Integer> listUserIdByDepartment(@Param("departmentId") Integer departmentId);
}