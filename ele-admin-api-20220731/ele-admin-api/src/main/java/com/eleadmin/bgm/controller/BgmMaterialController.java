package com.eleadmin.bgm.controller;

import com.eleadmin.bgm.entity.BgmMaterialType;
import com.eleadmin.bgm.service.BgmMaterialTypeService;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmMaterialService;
import com.eleadmin.bgm.entity.BgmMaterial;
import com.eleadmin.bgm.param.BgmMaterialParam;
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
 * 控制器
 *
 * @author EleAdmin
 * @since 2023-04-15 12:46:34
 */
@Api(tags = "管理")
@RestController
@RequestMapping("/api/bgm/bgmMaterial")
public class BgmMaterialController extends BaseController {
    @Resource
    private BgmMaterialService bgmMaterialService;

    @Resource
    private BgmMaterialTypeService bgmMaterialTypeService;

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:list')")
    @OperationLog
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmMaterial>> page(BgmMaterialParam param) {
        PageParam<BgmMaterial, BgmMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmMaterialService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmMaterialService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:list')")
    @OperationLog
    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<BgmMaterial>> list(BgmMaterialParam param) {
        PageParam<BgmMaterial, BgmMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmMaterialService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmMaterialService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:list')")
    @OperationLog
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public ApiResult<BgmMaterial> get(@PathVariable("id") Integer id) {
        return success(bgmMaterialService.getById(id));
        // 使用关联查询
        //return success(bgmMaterialService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:save')")
    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmMaterial bgmMaterial) {
        System.out.println(bgmMaterial);
        bgmMaterial.setMaterialMeasurement("千克");
        int typeID=bgmMaterial.getMaterialTypeId();
        bgmMaterialTypeService.getByIdRel(bgmMaterial.getMaterialTypeId());
        String ziName=bgmMaterialTypeService.getByIdRel(bgmMaterial.getMaterialTypeId()).getMaterialTypeName();
        Integer i=bgmMaterialTypeService.getByIdRel(bgmMaterial.getMaterialTypeId()).getMaterialTypePid();
        String fName=bgmMaterialTypeService.getByIdRel(i).getMaterialTypeName();
        bgmMaterial.setMaterialType(fName+" "+ziName);
        System.out.println(bgmMaterial.getMaterialType());
        if (bgmMaterialService.save(bgmMaterial)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:update')")
    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmMaterial bgmMaterial) {

        bgmMaterial.setMaterialMeasurement("千克");
        int typeID=bgmMaterial.getMaterialTypeId();
        bgmMaterialTypeService.getByIdRel(bgmMaterial.getMaterialTypeId());
        String ziName=bgmMaterialTypeService.getByIdRel(bgmMaterial.getMaterialTypeId()).getMaterialTypeName();
        Integer i=bgmMaterialTypeService.getByIdRel(bgmMaterial.getMaterialTypeId()).getMaterialTypePid();
        String fName=bgmMaterialTypeService.getByIdRel(i).getMaterialTypeName();
        bgmMaterial.setMaterialType(fName+" "+ziName);
        if (bgmMaterialService.updateById(bgmMaterial)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:remove')")
    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmMaterialService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:save')")
    @OperationLog
    @ApiOperation("批量添加")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmMaterial> list) {
        if (bgmMaterialService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:update')")
    @OperationLog
    @ApiOperation("批量修改")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmMaterial> batchParam) {
        if (batchParam.update(bgmMaterialService, "material_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterial:remove')")
    @OperationLog
    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmMaterialService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
