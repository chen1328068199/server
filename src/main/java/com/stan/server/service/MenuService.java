package com.stan.server.service;

import com.stan.server.bean.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stan.server.utils.ResultVO;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
public interface MenuService extends IService<Menu> {
    ResultVO<String> getMenuFromRole();
}
