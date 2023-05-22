package com.eleadmin.bgm.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmAgreementMaterialService;
import com.eleadmin.bgm.entity.BgmAgreementMaterial;
import com.eleadmin.bgm.param.BgmAgreementMaterialParam;
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
 * 协议订购材料具体数据控制器
 *
 * @author EleAdmin
 * @since 2023-05-16 11:17:57
 */
@Api(tags = "协议订购材料具体数据管理")
@RestController
@RequestMapping("/api/bgm/bgm-agreement-material")
public class BgmAgreementMaterialController extends BaseController {
    @Resource
    private BgmAgreementMaterialService bgmAgreementMaterialService;

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:list')")
    @OperationLog
    @ApiOperation("分页查询协议订购材料具体数据")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmAgreementMaterial>> page(BgmAgreementMaterialParam param) {
        PageParam<BgmAgreementMaterial, BgmAgreementMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmAgreementMaterialService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmAgreementMaterialService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:list')")
    @OperationLog
    @ApiOperation("查询全部协议订购材料具体数据")
    @GetMapping()
    public ApiResult<List<BgmAgreementMaterial>> list(BgmAgreementMaterialParam param) {
        PageParam<BgmAgreementMaterial, BgmAgreementMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmAgreementMaterialService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmAgreementMaterialService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:list')")
    @OperationLog
    @ApiOperation("根据id查询协议订购材料具体数据")
    @GetMapping("/{id}")
    public ApiResult<BgmAgreementMaterial> get(@PathVariable("id") Integer id) {
        return success(bgmAgreementMaterialService.getById(id));
        // 使用关联查询
        //return success(bgmAgreementMaterialService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:save')")
    @OperationLog
    @ApiOperation("添加协议订购材料具体数据")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmAgreementMaterial bgmAgreementMaterial) {
        if (bgmAgreementMaterialService.save(bgmAgreementMaterial)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:update')")
    @OperationLog
    @ApiOperation("修改协议订购材料具体数据")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmAgreementMaterial bgmAgreementMaterial) {
        if (bgmAgreementMaterialService.updateById(bgmAgreementMaterial)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:remove')")
    @OperationLog
    @ApiOperation("删除协议订购材料具体数据")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmAgreementMaterialService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:save')")
    @OperationLog
    @ApiOperation("批量添加协议订购材料具体数据")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmAgreementMaterial> list) {
        if (bgmAgreementMaterialService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:update')")
    @OperationLog
    @ApiOperation("批量修改协议订购材料具体数据")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmAgreementMaterial> batchParam) {
        if (batchParam.update(bgmAgreementMaterialService, "am_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreementMaterial:remove')")
    @OperationLog
    @ApiOperation("批量删除协议订购材料具体数据")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmAgreementMaterialService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
