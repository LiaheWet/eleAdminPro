package com.eleadmin.bgm.controller;

import com.eleadmin.bgm.entity.BgmVendorAccount;
import com.eleadmin.bgm.entity.BgmVendorContact;
import com.eleadmin.bgm.param.BgmAgreementParam;
import com.eleadmin.bgm.service.BgmVendorAccountService;
import com.eleadmin.bgm.service.BgmVendorContactService;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmVendorService;
import com.eleadmin.bgm.entity.BgmVendor;
import com.eleadmin.bgm.param.BgmVendorParam;
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
 * 供应商控制器
 *
 * @author EleAdmin
 * @since 2023-04-25 16:31:08
 */
@Api(tags = "供应商管理")
@RestController
@RequestMapping("/api/bgm/bgmVendor")
public class BgmVendorController extends BaseController {
    @Resource
    private BgmVendorService bgmVendorService;
    @Resource
    private BgmVendorAccountService bgmVendorAccountService;
    @Resource
    private BgmVendorContactService bgmVendorContactService;
    @PreAuthorize("hasAuthority('bgm:bgmVendor:list')")
    @OperationLog
    @ApiOperation("分页查询供应商")
    @GetMapping("/selectPro")
    public ApiResult<BgmVendorParam> selectPro(BgmVendorParam param) {

        return success(bgmVendorService.selectPro(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:list')")
    @OperationLog
    @ApiOperation("分页查询供应商")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmVendor>> page(BgmVendorParam param) {
        PageParam<BgmVendor, BgmVendorParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmVendorService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:list')")
    @OperationLog
    @ApiOperation("查询全部供应商")
    @GetMapping()
    public ApiResult<List<BgmVendor>> list(BgmVendorParam param) {
        PageParam<BgmVendor, BgmVendorParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmVendorService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:list')")
    @OperationLog
    @ApiOperation("根据id查询供应商")
    @GetMapping("/{id}")
    public ApiResult<BgmVendor> get(@PathVariable("id") Integer id) {
        return success(bgmVendorService.getById(id));
        // 使用关联查询
        //return success(bgmVendorService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:save')")
    @OperationLog
    @ApiOperation("添加供应商")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmVendor bgmVendor) {
        if (bgmVendorService.saveOrUpdate(bgmVendor)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }
    @PreAuthorize("hasAuthority('bgm:bgmVendor:remove')")
    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        List<BgmVendorAccount> bgmVendorAccountList = bgmVendorAccountService.listByVendorId(id);
        for (BgmVendorAccount b:bgmVendorAccountList
        ) {
            bgmVendorAccountService.removeById(b.getId());
        }

        List<BgmVendorContact> bgmVendorContactList = bgmVendorContactService.listByVendorId(id);
        for (BgmVendorContact b:bgmVendorContactList
        ) {
            bgmVendorContactService.removeById(b.getId());
        }

        if (bgmVendorService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:update')")
    @OperationLog
    @ApiOperation("修改供应商")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmVendor bgmVendor) {
        if (bgmVendorService.updateById(bgmVendor)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:save')")
    @OperationLog
    @ApiOperation("批量添加供应商")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmVendor> list) {
        if (bgmVendorService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:update')")
    @OperationLog
    @ApiOperation("批量修改供应商")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmVendor> batchParam) {
        if (batchParam.update(bgmVendorService, "vendor_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendor:remove')")
    @OperationLog
    @ApiOperation("批量删除供应商")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmVendorService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
