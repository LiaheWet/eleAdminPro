package com.eleadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.system.entity.Menu;
import com.eleadmin.common.system.entity.RoleMenu;

import java.util.List;

/**
 * 角色菜单Service
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:44
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 查询用户对应的菜单
     *
     * @param userId   用户id
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    List<Menu> listMenuByUserId(Integer userId, Integer menuType);

    /**
     * 查询用户对应的菜单
     *
     * @param roleIds  角色id
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    List<Menu> listMenuByRoleIds(List<Integer> roleIds, Integer menuType);

}
