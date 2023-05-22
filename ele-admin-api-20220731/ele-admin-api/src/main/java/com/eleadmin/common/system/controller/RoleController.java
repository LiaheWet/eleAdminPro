package com.eleadmin.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eleadmin.common.core.annotation.OperationLog;
import com.eleadmin.common.core.utils.CommonUtil;
import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.entity.Role;
import com.eleadmin.common.system.param.RoleParam;
import com.eleadmin.common.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:02
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/system/role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperationLog
    @ApiOperation("分页查询角色")
    @GetMapping("/page")
    public ApiResult<PageResult<Role>> page(RoleParam param) {
        PageParam<Role, RoleParam> page = new PageParam<>(param);
        return success(roleService.page(page, page.getWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperationLog
    @ApiOperation("查询全部角色")
    @GetMapping()
    public ApiResult<List<Role>> list(RoleParam param) {
        PageParam<Role, RoleParam> page = new PageParam<>(param);
        return success(roleService.list(page.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperationLog
    @ApiOperation("根据id查询角色")
    @GetMapping("/{id}")
    public ApiResult<Role> get(@PathVariable("id") Integer id) {
        return success(roleService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:role:save')")
    @OperationLog
    @ApiOperation("添加角色")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Role role) {
        if (roleService.count(new LambdaQueryWrapper<Role>().eq(Role::getRoleCode, role.getRoleCode())) > 0) {
            return fail("角色标识已存在");
        }
        if (roleService.count(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, role.getRoleName())) > 0) {
            return fail("角色名称已存在");
        }
        if (roleService.save(role)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperationLog
    @ApiOperation("修改角色")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Role role) {
        if (role.getRoleCode() != null && roleService.count(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleCode, role.getRoleCode())
                .ne(Role::getRoleId, role.getRoleId())) > 0) {
            return fail("角色标识已存在");
        }
        if (role.getRoleName() != null && roleService.count(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleName, role.getRoleName())
                .ne(Role::getRoleId, role.getRoleId())) > 0) {
            return fail("角色名称已存在");
        }
        if (roleService.updateById(role)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @OperationLog
    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (roleService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:role:save')")
    @OperationLog
    @ApiOperation("批量添加角色")
    @PostMapping("/batch")
    public ApiResult<List<String>> saveBatch(@RequestBody List<Role> list) {
        // 校验是否重复
        if (CommonUtil.checkRepeat(list, Role::getRoleName)) {
            return fail("角色名称存在重复", null);
        }
        if (CommonUtil.checkRepeat(list, Role::getRoleCode)) {
            return fail("角色标识存在重复", null);
        }
        // 校验是否存在
        List<Role> codeExists = roleService.list(new LambdaQueryWrapper<Role>().in(Role::getRoleCode,
                list.stream().map(Role::getRoleCode).collect(Collectors.toList())));
        if (codeExists.size() > 0) {
            return fail("角色标识已存在", codeExists.stream().map(Role::getRoleCode)
                    .collect(Collectors.toList())).setCode(2);
        }
        List<Role> nameExists = roleService.list(new LambdaQueryWrapper<Role>().in(Role::getRoleName,
                list.stream().map(Role::getRoleCode).collect(Collectors.toList())));
        if (nameExists.size() > 0) {
            return fail("角色标识已存在", nameExists.stream().map(Role::getRoleCode)
                    .collect(Collectors.toList())).setCode(3);
        }
        if (roleService.saveBatch(list)) {
            return success("添加成功", null);
        }
        return fail("添加失败", null);
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @OperationLog
    @ApiOperation("批量删除角色")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (roleService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
