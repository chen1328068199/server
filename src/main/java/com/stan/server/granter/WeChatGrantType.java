package com.stan.server.granter;

import com.stan.server.mapper.UserMapper;
import com.stan.server.model.vo.UserVO;
import org.minbox.framework.api.boot.plugin.oauth.exception.ApiBootTokenException;
import org.minbox.framework.api.boot.plugin.oauth.grant.ApiBootOauthTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * 新增GrantType，用于根据openId获取token
 */
@Component
public class WeChatGrantType implements ApiBootOauthTokenGranter {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String grantType() {
        return "wechat";
    }

    @Override
    public UserDetails loadByParameter(Map<String, String> map) throws ApiBootTokenException {
        String openId = map.get("open_id");
        if (StringUtils.isEmpty(openId)) {
            throw new InvalidGrantException("无效的openId");
        }
        // 判断成功后返回用户细节
        //return new User(phone, "", AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user,root"));
        UserVO userVO = userMapper.getUserInfoByOpenId(openId);
        //角色
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        String[] roles = userVO.getRoles().split(",");
        for (int i = 0; i < roles.length; i++) {
            collection.add(new SimpleGrantedAuthority(roles[i]));
        }
        return new User(userVO.getUserName(), "", collection);
    }
}
