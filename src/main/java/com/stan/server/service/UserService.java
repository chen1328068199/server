package com.stan.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.model.User;
import com.stan.server.model.vo.UserVO;
import com.stan.server.utils.ResultVO;

public interface UserService extends IService<User> {
    User getUserInfoByName(String userName);
    UserVO getUserInfoByOpenId(String openId);

    void updateUser(User user, String roleIds);

    void addUser(User user, String roleIds);

    void delete(Integer userId);

    ResultVO<Object> updateCurrentUserPassword(String oldPassword, String newPassword);
}
