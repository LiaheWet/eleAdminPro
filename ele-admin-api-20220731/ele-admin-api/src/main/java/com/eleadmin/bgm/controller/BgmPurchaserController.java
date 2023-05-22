package com.eleadmin.bgm.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmPurchaserService;
import com.eleadmin.bgm.entity.BgmPurchaser;
import com.eleadmin.bgm.param.BgmPurchaserParam;
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
 * 采购人控制器
 *
 * @author EleAdmin
 * @since 2023-05-06 16:40:54
 */
@Api(tags = "采购人管理")
@RestController
@RequestMapping("/api/bgm/bgmPurchaser")
public class BgmPurchaserController extends BaseController {
    @Resource
    private BgmPurchaserService bgmPurchaserService;

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:list')")
    @OperationLog
    @ApiOperation("分页查询采购人")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmPurchaser>> page(BgmPurchaserParam param) {
        PageParam<BgmPurchaser, BgmPurchaserParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmPurchaserService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmPurchaserService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:list')")
    @OperationLog
    @ApiOperation("查询全部采购人")
    @GetMapping()
    public ApiResult<List<BgmPurchaser>> list(BgmPurchaserParam param) {
        PageParam<BgmPurchaser, BgmPurchaserParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmPurchaserService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmPurchaserService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:list')")
    @OperationLog
    @ApiOperation("根据id查询采购人")
    @GetMapping("/{id}")
    public ApiResult<BgmPurchaser> get(@PathVariable("id") Integer id) {
        return success(bgmPurchaserService.getById(id));
        // 使用关联查询
        //return success(bgmPurchaserService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:save')")
    @OperationLog
    @ApiOperation("添加采购人")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmPurchaser bgmPurchaser) {
        if (bgmPurchaserService.save(bgmPurchaser)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:update')")
    @OperationLog
    @ApiOperation("修改采购人")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmPurchaser bgmPurchaser) {
        if (bgmPurchaserService.updateById(bgmPurchaser)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:remove')")
    @OperationLog
    @ApiOperation("删除采购人")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmPurchaserService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:save')")
    @OperationLog
    @ApiOperation("批量添加采购人")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmPurchaser> list) {
        if (bgmPurchaserService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:update')")
    @OperationLog
    @ApiOperation("批量修改采购人")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmPurchaser> batchParam) {
        if (batchParam.update(bgmPurchaserService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmPurchaser:remove')")
    @OperationLog
    @ApiOperation("批量删除采购人")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmPurchaserService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
