package com.eleadmin.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.eleadmin.common.core.annotation.OperationLog;
import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.common.core.exception.BusinessException;
import com.eleadmin.common.system.entity.Menu;
import com.eleadmin.common.system.entity.RoleMenu;
import com.eleadmin.common.system.service.MenuService;
import com.eleadmin.common.system.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:01
 */
@Api(tags = "角色菜单管理")
@RestController
@RequestMapping("/api/system/role-menu")
public class RoleMenuController extends BaseController {
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private MenuService menuService;

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperationLog
    @ApiOperation("查询角色菜单")
    @GetMapping("/{id}")
    public ApiResult<List<Menu>> list(@PathVariable("id") Integer roleId) {
        List<Menu> menus = menuService.list(new LambdaQueryWrapper<Menu>().orderByAsc(Menu::getSortNumber));
        List<RoleMenu> roleMenus = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getRoleId, roleId));
        for (Menu menu : menus) {
            menu.setChecked(roleMenus.stream().anyMatch((d) -> d.getMenuId().equals(menu.getMenuId())));
        }
        return success(menus);
    }

    @Transactional(rollbackFor = {Exception.class})
    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperationLog
    @ApiOperation("修改角色菜单")
    @PutMapping("/{id}")
    public ApiResult<?> update(@PathVariable("id") Integer roleId, @RequestBody List<Integer> menuIds) {
        roleMenuService.remove(new LambdaUpdateWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        if (menuIds != null && menuIds.size() > 0) {
            List<RoleMenu> roleMenuList = new ArrayList<>();
            for (Integer menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuList.add(roleMenu);
            }
            if (!roleMenuService.saveBatch(roleMenuList)) {
                throw new BusinessException("保存失败");
            }
        }
        return success("保存成功");
    }

    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperationLog
    @ApiOperation("添加角色菜单")
    @PostMapping("/{id}")
    public ApiResult<?> addRoleAuth(@PathVariable("id") Integer roleId, @RequestBody Integer menuId) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        if (roleMenuService.save(roleMenu)) {
            return success();
        }
        return fail();
    }

    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperationLog
    @ApiOperation("移除角色菜单")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer roleId, @RequestBody Integer menuId) {
        if (roleMenuService.remove(new LambdaUpdateWrapper<RoleMenu>()
                .eq(RoleMenu::getRoleId, roleId).eq(RoleMenu::getMenuId, menuId))) {
            return success();
        }
        return fail();
    }

}
