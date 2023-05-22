package com.eleadmin.bgm.controller;

import com.eleadmin.bgm.entity.BgmMaterialTypeNode;
import com.eleadmin.bgm.entity.BgmMeasurement;
import com.eleadmin.bgm.param.BgmMeasurementParam;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmMaterialTypeService;
import com.eleadmin.bgm.entity.BgmMaterialType;
import com.eleadmin.bgm.param.BgmMaterialTypeParam;
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
 * 材料类别控制器
 *
 * @author EleAdmin
 * @since 2023-04-12 10:41:31
 */
@Api(tags = "材料类别管理")
@RestController
@RequestMapping("/api/bgm/bgmMaterialType")
public class BgmMaterialTypeController extends BaseController {
    @Resource
    private BgmMaterialTypeService bgmMaterialTypeService;

    @PreAuthorize("hasAuthority('bgm:bgmMaterialType:list')")
    @OperationLog
    @ApiOperation("整合材料类别")
    @GetMapping("/integrate")

    public ApiResult<List<BgmMaterialTypeNode>> integrate() {
        //获取所有材料类别
        List<BgmMaterialType> allBgmMaterialType = bgmMaterialTypeService.list();
        //整合材料类别.写成父子节点
        List<BgmMaterialTypeNode> typeList=bgmMaterialTypeService.integrate(allBgmMaterialType);

        return success(typeList);


    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterialType:list')")
    @OperationLog
    @ApiOperation("查询全部材料类别")
    @GetMapping()
    public ApiResult<List<BgmMaterialType>> list(BgmMaterialTypeParam param) {
        PageParam<BgmMaterialType, BgmMaterialTypeParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmMaterialTypeService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmMaterialTypeService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterialType:list')")
    @OperationLog
    @ApiOperation("根据id查询材料类别")
    @GetMapping("/{id}")
    public ApiResult<BgmMaterialType> get(@PathVariable("id") Integer id) {
        return success(bgmMaterialTypeService.getById(id));
        // 使用关联查询
        //return success(bgmMaterialTypeService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterialType:save')")
    @OperationLog
    @ApiOperation("添加材料类别")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmMaterialType bgmMaterialType) {
        if (bgmMaterialTypeService.save(bgmMaterialType)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterialType:update')")
    @OperationLog
    @ApiOperation("修改材料类别")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmMaterialType bgmMaterialType) {
        if (bgmMaterialTypeService.updateById(bgmMaterialType)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMaterialType:remove')")
    @OperationLog
    @ApiOperation("删除材料类别")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmMaterialTypeService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
