package com.eleadmin.bgm.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmMeasurementService;
import com.eleadmin.bgm.entity.BgmMeasurement;
import com.eleadmin.bgm.param.BgmMeasurementParam;
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
 * 计量单位控制器
 *
 * @author EleAdmin
 * @since 2023-04-09 14:15:51
 */
@Api(tags = "计量单位管理")
@RestController
@RequestMapping("/api/bgm/bgmMeasurement")
public class BgmMeasurementController extends BaseController {
    @Resource
    private BgmMeasurementService bgmMeasurementService;

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:list')")
    @OperationLog
    @ApiOperation("分页查询计量单位")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmMeasurement>> page(BgmMeasurementParam param) {
        PageParam<BgmMeasurement, BgmMeasurementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmMeasurementService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmMeasurementService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:list')")
    @OperationLog
    @ApiOperation("查询全部计量单位")
    @GetMapping()
    public ApiResult<List<BgmMeasurement>> list(BgmMeasurementParam param) {
        PageParam<BgmMeasurement, BgmMeasurementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmMeasurementService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmMeasurementService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:list')")
    @OperationLog
    @ApiOperation("根据id查询计量单位")
    @GetMapping("/{id}")
    public ApiResult<BgmMeasurement> get(@PathVariable("id") Integer id) {
        return success(bgmMeasurementService.getById(id));
        // 使用关联查询
        //return success(bgmMeasurementService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:save')")
    @OperationLog
    @ApiOperation("添加计量单位")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmMeasurement bgmMeasurement) {
        if (bgmMeasurementService.save(bgmMeasurement)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:update')")
    @OperationLog
    @ApiOperation("修改计量单位")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmMeasurement bgmMeasurement) {
        if (bgmMeasurementService.updateById(bgmMeasurement)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:remove')")
    @OperationLog
    @ApiOperation("删除计量单位")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        System.out.println("删除");
        if (bgmMeasurementService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:save')")
    @OperationLog
    @ApiOperation("批量添加计量单位")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmMeasurement> list) {
        if (bgmMeasurementService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:update')")
    @OperationLog
    @ApiOperation("批量修改计量单位")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmMeasurement> batchParam) {
        if (batchParam.update(bgmMeasurementService, "measurement_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmMeasurement:remove')")
    @OperationLog
    @ApiOperation("批量删除计量单位")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmMeasurementService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
