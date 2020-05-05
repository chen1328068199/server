package com.stan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.entity.Role;
import com.stan.server.model.MyUserDetails;
import com.stan.server.model.User;
import com.stan.server.mapper.UserMapper;
import com.stan.server.model.vo.UserVO;
import com.stan.server.service.RoleService;
import com.stan.server.service.UserService;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.SecurityAuthUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public User getUserInfoByName(String userName) {
        return userMapper.getUserInfoByName(userName);
    }

    @Override
    public UserVO getUserInfoByOpenId(String openId) {
        return userMapper.getUserInfoByOpenId(openId);
    }

    @Override
    @Transactional()
    public void updateUser(User user, String roleIds) {
        updateById(user);
        if (roleIds != null && !roleIds.trim().equals(""))
            updateRoleFromUser(user.getId(), roleIds);
    }

    @Override
    @Transactional()
    public void addUser(User user, String roleIds) {
        int id = userMapper.insert(user);
        if (roleIds != null && !roleIds.trim().equals(""))
            updateRoleFromUser(id, roleIds);
    }

    @Override
    @Transactional()
    public void delete(Integer userId) {
        super.removeById(userId);
        userMapper.deleteRoleFromUser(userId);
    }

    @Override
    public ResultVO<Object> updateCurrentUserPassword(String oldPassword, String newPassword) {
        int id = SecurityAuthUtil.getLoginId();
        User user = getById(id);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(oldPassword, user.getUserPassword())) {
            return ResultVO.result(233, "密码错误");
        }
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("user_password", passwordEncoder.encode(newPassword));
        userUpdateWrapper.eq("id", id);
        update(userUpdateWrapper);
        return ResultVO.success();
    }

    private void updateRoleFromUser(Integer userId, String roleIds) {
        if (userId == null || roleIds == null || roleIds.trim().equals(""))
            return;
        userMapper.deleteRoleFromUser(userId);
        String[] roleIdStrArr = StringUtils.split(roleIds, ",");
        int[] roleIdsArr = new int[roleIdStrArr.length];
        for (int i = 0; i < roleIdStrArr.length; i++) {
            roleIdsArr[i] = Integer.parseInt(roleIdStrArr[i]);
        }
        userMapper.updateUserRole(userId, roleIdsArr);
    }


}
