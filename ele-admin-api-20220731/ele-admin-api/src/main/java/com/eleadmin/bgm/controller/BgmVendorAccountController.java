package com.eleadmin.bgm.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmVendorAccountService;
import com.eleadmin.bgm.entity.BgmVendorAccount;
import com.eleadmin.bgm.param.BgmVendorAccountParam;
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
 * 供应商账户信息
 控制器
 *
 * @author EleAdmin
 * @since 2023-06-03 15:43:11
 */
@Api(tags = "供应商账户信息
        管理")
        @RestController
@RequestMapping("/api/bgm/bgm-vendor-account")
        public class BgmVendorAccountController extends BaseController {
        @Resource
        private BgmVendorAccountService bgmVendorAccountService;

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:list')")
        @OperationLog
@ApiOperation("分页查询供应商账户信息
        ")
        @GetMapping("/page")
        public ApiResult<PageResult<BgmVendorAccount>> page(BgmVendorAccountParam param) {
        PageParam<BgmVendorAccount, BgmVendorAccountParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorAccountService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmVendorAccountService.pageRel(param));
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:list')")
        @OperationLog
@ApiOperation("查询全部供应商账户信息
        ")
        @GetMapping()
        public ApiResult<List<BgmVendorAccount>> list(BgmVendorAccountParam param) {
        PageParam<BgmVendorAccount, BgmVendorAccountParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorAccountService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmVendorAccountService.listRel(param));
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:list')")
        @OperationLog
@ApiOperation("根据id查询供应商账户信息
        ")
        @GetMapping("/{id}")
        public ApiResult<BgmVendorAccount> get(@PathVariable("id") Integer id) {
        return success(bgmVendorAccountService.getById(id));
        // 使用关联查询
        //return success(bgmVendorAccountService.getByIdRel(id));
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:save')")
        @OperationLog
@ApiOperation("添加供应商账户信息
        ")
        @PostMapping()
        public ApiResult<?> save(@RequestBody BgmVendorAccount bgmVendorAccount) {
        if (bgmVendorAccountService.save(bgmVendorAccount)) {
        return success("添加成功");
        }
        return fail("添加失败");
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:update')")
        @OperationLog
@ApiOperation("修改供应商账户信息
        ")
        @PutMapping()
        public ApiResult<?> update(@RequestBody BgmVendorAccount bgmVendorAccount) {
        if (bgmVendorAccountService.updateById(bgmVendorAccount)) {
        return success("修改成功");
        }
        return fail("修改失败");
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:remove')")
        @OperationLog
@ApiOperation("删除供应商账户信息
        ")
        @DeleteMapping("/{id}")
        public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmVendorAccountService.removeById(id)) {
        return success("删除成功");
        }
        return fail("删除失败");
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:save')")
        @OperationLog
@ApiOperation("批量添加供应商账户信息
        ")
        @PostMapping("/batch")
        public ApiResult<?> saveBatch(@RequestBody List<BgmVendorAccount> list) {
        if (bgmVendorAccountService.saveBatch(list)) {
        return success("添加成功");
        }
        return fail("添加失败");
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:update')")
        @OperationLog
@ApiOperation("批量修改供应商账户信息
        ")
        @PutMapping("/batch")
        public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmVendorAccount> batchParam) {
        if (batchParam.update(bgmVendorAccountService, "id")) {
        return success("修改成功");
        }
        return fail("修改失败");
        }

        @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:remove')")
        @OperationLog
@ApiOperation("批量删除供应商账户信息
        ")
        @DeleteMapping("/batch")
        public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmVendorAccountService.removeByIds(ids)) {
        return success("删除成功");
        }
        return fail("删除失败");
        }

        }
