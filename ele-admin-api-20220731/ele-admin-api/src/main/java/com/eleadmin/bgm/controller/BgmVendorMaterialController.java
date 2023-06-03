package com.eleadmin.bgm.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmVendorMaterialService;
import com.eleadmin.bgm.entity.BgmVendorMaterial;
import com.eleadmin.bgm.param.BgmVendorMaterialParam;
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
 * 供应商供应材料控制器
 *
 * @author EleAdmin
 * @since 2023-06-03 18:58:27
 */
@Api(tags = "供应商供应材料管理")
@RestController
@RequestMapping("/api/bgm/bgm-vendor-material")
public class BgmVendorMaterialController extends BaseController {
    @Resource
    private BgmVendorMaterialService bgmVendorMaterialService;

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:list')")
    @OperationLog
    @ApiOperation("分页查询供应商供应材料")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmVendorMaterial>> page(BgmVendorMaterialParam param) {
        PageParam<BgmVendorMaterial, BgmVendorMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorMaterialService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmVendorMaterialService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:list')")
    @OperationLog
    @ApiOperation("查询全部供应商供应材料")
    @GetMapping()
    public ApiResult<List<BgmVendorMaterial>> list(BgmVendorMaterialParam param) {
        PageParam<BgmVendorMaterial, BgmVendorMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorMaterialService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmVendorMaterialService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:list')")
    @OperationLog
    @ApiOperation("根据id查询供应商供应材料")
    @GetMapping("/{id}")
    public ApiResult<List<BgmVendorMaterialParam>> get(@PathVariable("id") Integer id) {
        BgmVendorMaterialParam param=new BgmVendorMaterialParam();
        param.setVendorId(id);
        return success(bgmVendorMaterialService.listRel(param));
        // 使用关联查询
        //return success(bgmVendorMaterialService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:save')")
    @OperationLog
    @ApiOperation("添加供应商供应材料")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmVendorMaterial bgmVendorMaterial) {
        if (bgmVendorMaterialService.save(bgmVendorMaterial)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:update')")
    @OperationLog
    @ApiOperation("修改供应商供应材料")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmVendorMaterial bgmVendorMaterial) {
        if (bgmVendorMaterialService.updateById(bgmVendorMaterial)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:remove')")
    @OperationLog
    @ApiOperation("删除供应商供应材料")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmVendorMaterialService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:save')")
    @OperationLog
    @ApiOperation("批量添加供应商供应材料")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmVendorMaterial> list) {
        if (bgmVendorMaterialService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:update')")
    @OperationLog
    @ApiOperation("批量修改供应商供应材料")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmVendorMaterial> batchParam) {
        if (batchParam.update(bgmVendorMaterialService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorMaterial:remove')")
    @OperationLog
    @ApiOperation("批量删除供应商供应材料")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmVendorMaterialService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
