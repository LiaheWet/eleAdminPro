package com.eleadmin.bgm.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmSupplyMaterialService;
import com.eleadmin.bgm.entity.BgmSupplyMaterial;
import com.eleadmin.bgm.param.BgmSupplyMaterialParam;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 供应的原材料(关联表;多对多)控制器
 *
 * @author EleAdmin
 * @since 2023-05-17 12:15:09
 */
@Api(tags = "供应的原材料(关联表;多对多)管理")
@RestController
@RequestMapping("/api/bgm/bgmSupplyMaterial")
public class BgmSupplyMaterialController extends BaseController {
    @Resource
    private BgmSupplyMaterialService bgmSupplyMaterialService;

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:list')")
    @OperationLog
    @ApiOperation("分页查询供应的原材料(关联表;多对多)")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmSupplyMaterialParam>> page(BgmSupplyMaterialParam param) {
        PageResult<BgmSupplyMaterialParam> listTemp=bgmSupplyMaterialService.pageList(param);
        //Collections.sort(listTemp.getList(), Comparator.comparingInt(BgmSupplyMaterialParam::getSmTypeId));
        /*return success(bgmSupplyMaterialService.pageList(param));*/
        return success(listTemp);
        // 使用关联查询
        //return success(bgmSupplyMaterialService.pageRel(param));
    }
    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:list')")
    @OperationLog
    @ApiOperation("分页查询供应的原材料(关联表;多对多)")
    @GetMapping("/page1")
    public ApiResult<PageResult<BgmSupplyMaterial>> page1(BgmSupplyMaterialParam param) {
        PageParam<BgmSupplyMaterial, BgmSupplyMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        page.setDefaultOrder("sm_type_id ASC");
        return success(bgmSupplyMaterialService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmSupplyMaterialService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:list')")
    @OperationLog
    @ApiOperation("查询全部供应的原材料(关联表;多对多)")
    @GetMapping()
    public ApiResult<List<BgmSupplyMaterial>> list(BgmSupplyMaterialParam param) {
        PageParam<BgmSupplyMaterial, BgmSupplyMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmSupplyMaterialService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmSupplyMaterialService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:list')")
    @OperationLog
    @ApiOperation("根据id查询供应的原材料(关联表;多对多)")
    @GetMapping("/{id}")
    public ApiResult<BgmSupplyMaterial> get(@PathVariable("id") Integer id) {
        return success(bgmSupplyMaterialService.getById(id));
        // 使用关联查询
        //return success(bgmSupplyMaterialService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:save')")
    @OperationLog
    @ApiOperation("添加供应的原材料(关联表;多对多)")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmSupplyMaterial bgmSupplyMaterial) {
        if (bgmSupplyMaterialService.save(bgmSupplyMaterial)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:update')")
    @OperationLog
    @ApiOperation("修改供应的原材料(关联表;多对多)")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmSupplyMaterial bgmSupplyMaterial) {
        if (bgmSupplyMaterialService.updateById(bgmSupplyMaterial)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:remove')")
    @OperationLog
    @ApiOperation("删除供应的原材料(关联表;多对多)")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmSupplyMaterialService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:save')")
    @OperationLog
    @ApiOperation("批量添加供应的原材料(关联表;多对多)")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmSupplyMaterial> list) {
        if (bgmSupplyMaterialService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:update')")
    @OperationLog
    @ApiOperation("批量修改供应的原材料(关联表;多对多)")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmSupplyMaterial> batchParam) {
        if (batchParam.update(bgmSupplyMaterialService, "supply_material_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmSupplyMaterial:remove')")
    @OperationLog
    @ApiOperation("批量删除供应的原材料(关联表;多对多)")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmSupplyMaterialService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
