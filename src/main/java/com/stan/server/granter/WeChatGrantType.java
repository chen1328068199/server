//package com.stan.server.granter;
//
//import com.stan.server.bean.SysRole;
//import com.stan.server.mapper.UserMapper;
//import com.stan.server.model.MyUserDetails;
//import com.stan.server.model.vo.UserVO;
//import com.stan.server.service.SysRoleService;
//import org.minbox.framework.api.boot.plugin.oauth.exception.ApiBootTokenException;
//import org.minbox.framework.api.boot.plugin.oauth.grant.ApiBootOauthTokenGranter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//
///**
// * 新增GrantType，用于根据openId获取token
// */
//@Component
//public class WeChatGrantType implements ApiBootOauthTokenGranter {
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private SysRoleService sysRoleService;
//
//    @Override
//    public String grantType() {
//        return "weChat";
//    }
//
//    @Override
//    public UserDetails loadByParameter(Map<String, String> map) throws ApiBootTokenException {
//        String openId = map.get("open_id");
//        if (StringUtils.isEmpty(openId)) {
//            throw new InvalidGrantException("无效的openId");
//        }
//        // 判断成功后返回用户细节
//        UserVO userVO = userMapper.getUserInfoByOpenId(openId);
//        //角色
//        List<SysRole> roles = sysRoleService.getRolesFromUser(userVO.getId());
//        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
//        for (SysRole role : roles) {
//            collection.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        return new MyUserDetails(userVO.getId(), userVO.getOpenId(), "", collection);
//    }
//}
