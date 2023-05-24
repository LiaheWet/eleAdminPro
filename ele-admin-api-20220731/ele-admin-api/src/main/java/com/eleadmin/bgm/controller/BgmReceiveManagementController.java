package com.eleadmin.bgm.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmReceiveManagementService;
import com.eleadmin.bgm.entity.BgmReceiveManagement;
import com.eleadmin.bgm.param.BgmReceiveManagementParam;
import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.BatchParam;
import com.eleadmin.common.core.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收料管理表控制器
 *
 * @author EleAdmin
 * @since 2023-05-24 21:13:24
 */
@Api(tags = "收料管理表管理")
@RestController
@RequestMapping("/api/bgm/bgm-receive-management")
public class BgmReceiveManagementController extends BaseController {
    @Resource
    private BgmReceiveManagementService bgmReceiveManagementService;

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:list')")
    @OperationLog
    @ApiOperation("分页查询收料管理表")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmReceiveManagement>> page(BgmReceiveManagementParam param) {
        PageParam<BgmReceiveManagement, BgmReceiveManagementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmReceiveManagementService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmReceiveManagementService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:list')")
    @OperationLog
    @ApiOperation("查询全部收料管理表")
    @GetMapping()
    public ApiResult<List<BgmReceiveManagement>> list(BgmReceiveManagementParam param) {
        PageParam<BgmReceiveManagement, BgmReceiveManagementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmReceiveManagementService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmReceiveManagementService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:list')")
    @OperationLog
    @ApiOperation("根据id查询收料管理表")
    @GetMapping("/{id}")
    public ApiResult<BgmReceiveManagement> get(@PathVariable("id") Integer id) {
        return success(bgmReceiveManagementService.getById(id));
        // 使用关联查询
        //return success(bgmReceiveManagementService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:save')")
    @OperationLog
    @ApiOperation("添加收料管理表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmReceiveManagement bgmReceiveManagement) {
        if (bgmReceiveManagementService.save(bgmReceiveManagement)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:update')")
    @OperationLog
    @ApiOperation("修改收料管理表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmReceiveManagement bgmReceiveManagement) {
        if (bgmReceiveManagementService.updateById(bgmReceiveManagement)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:remove')")
    @OperationLog
    @ApiOperation("删除收料管理表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmReceiveManagementService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:save')")
    @OperationLog
    @ApiOperation("批量添加收料管理表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmReceiveManagement> list) {
        if (bgmReceiveManagementService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:update')")
    @OperationLog
    @ApiOperation("批量修改收料管理表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmReceiveManagement> batchParam) {
        if (batchParam.update(bgmReceiveManagementService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmReceiveManagement:remove')")
    @OperationLog
    @ApiOperation("批量删除收料管理表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmReceiveManagementService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
