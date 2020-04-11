package com.stan.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.bean.Menu;
import com.stan.server.bean.Role;
import com.stan.server.mapper.MenuMapper;
import com.stan.server.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stan.server.service.RoleService;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.SecurityAuthUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @Override
    public ResultVO<String> getMenuFromRole() {
        HashSet<GrantedAuthority> authorities = (HashSet<GrantedAuthority>) SecurityAuthUtil.getAuthenticationUser().getAuthorities();
        Set<String> roleSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            roleSet.add(authority.getAuthority());
        }
        List<Menu> menusFromRoles = roleService.getMenusFromRoles(roleSet);
        HashMap<Integer, JSONArray> menuMap = new HashMap<>();
        while (true) {
            HashSet<Integer> parentIds = new HashSet<>(menusFromRoles.size());
            for (Menu menusFromRole : menusFromRoles) {
                Integer parentId = menusFromRole.getParentId();
                parentIds.add(parentId);
                String name = menusFromRole.getName();
                int i = name.indexOf("_");
                if (i == 0)
                    menusFromRole.setPath("/");
                else
                    menusFromRole.setPath("/" + name);
                String s = JSONObject.toJSONString(menusFromRole);
                JSONObject jsonObject = JSONObject.parseObject(s);
                jsonObject.remove("childrenId");
                jsonObject.remove("parentId");
                JSONObject metaObject = new JSONObject();
                metaObject.put("title", jsonObject.remove("title"));
                metaObject.put("hideInMenu", jsonObject.remove("hideInMenu"));
                metaObject.put("icon", jsonObject.remove("icon"));
                jsonObject.put("meta", metaObject);
                JSONArray jsonArray = menuMap.get(parentId);
                if (jsonArray == null) {
                    jsonArray = new JSONArray();
                    menuMap.put(parentId, jsonArray);
                }
                jsonArray.add(jsonObject);
            }
            if (parentIds.size() == 1 && parentIds.contains(0))
                break;
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", parentIds);
            menusFromRoles = menuService.list(queryWrapper);
        }
        JSONArray jsonArray = menuMap.remove(0);
        if (jsonArray == null)
            return ResultVO.success();
        LinkedList<JSONObject> list = new LinkedList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            list.add(jsonObject);
        }
        while (list.size() != 0) {
            JSONObject jsonObject = list.poll();
            JSONArray array = menuMap.remove(jsonObject.getIntValue("id"));
            if (array != null)
                for (Object o : array) {
                    JSONObject jsonObject1 = (JSONObject) o;
                    list.add(jsonObject1);
                }
            jsonObject.put("children", array);
            jsonObject.remove("id");
        }
        return ResultVO.success(jsonArray.toJSONString());
    }
}
