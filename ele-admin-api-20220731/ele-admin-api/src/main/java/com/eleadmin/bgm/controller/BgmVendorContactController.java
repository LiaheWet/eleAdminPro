package com.eleadmin.bgm.controller;

import com.eleadmin.bgm.entity.BgmVendorAccount;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmVendorContactService;
import com.eleadmin.bgm.entity.BgmVendorContact;
import com.eleadmin.bgm.param.BgmVendorContactParam;
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
 * @since 2023-04-12 17:35:39
 */
@Api(tags = "管理")
@RestController
@RequestMapping("/api/bgm/bgm-vendor-contact")
public class BgmVendorContactController extends BaseController {
    @Resource
    private BgmVendorContactService bgmVendorContactService;

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:list')")
    @OperationLog
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmVendorContact>> page(BgmVendorContactParam param) {
        PageParam<BgmVendorContact, BgmVendorContactParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorContactService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(bgmVendorContactService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('B ')")
    @OperationLog
    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<BgmVendorContact>> list(BgmVendorContactParam param) {
        PageParam<BgmVendorContact, BgmVendorContactParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmVendorContactService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmVendorContactService.listRel(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:list')")
    @OperationLog
    @ApiOperation("根据vendorId查询")
    @GetMapping("/{id}")
    public ApiResult<List<BgmVendorContact>> listByVendorId(@PathVariable("id")Integer vendorId) {
        System.out.println("#### cha xun BgmVendorContact VendorId ####"+vendorId);
        List<BgmVendorContact> list = bgmVendorContactService.listByVendorId(vendorId);
        return success(list);
    }

//    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:list')")
//    @OperationLog
//    @ApiOperation("根据id查询")
//    @GetMapping("/{id}")
//    public ApiResult<BgmVendorContact> get(@PathVariable("id") Integer id) {
//        return success(bgmVendorContactService.getById(id));
//        // 使用关联查询
//        //return success(bgmVendorContactService.getByIdRel(id));
//    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:save')")
    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmVendorContact bgmVendorContact) {
        if (bgmVendorContactService.save(bgmVendorContact)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:update')")
    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmVendorContact bgmVendorContact) {
        if (bgmVendorContactService.updateById(bgmVendorContact)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:remove')")
    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (bgmVendorContactService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:save')")
    @OperationLog
    @ApiOperation("批量添加")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmVendorContact> contactList) {
        if (bgmVendorContactService.saveOrUpdateBatch(contactList)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

//    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:save')")
//    @OperationLog
//    @ApiOperation("批量添加")
//    @PostMapping("/batch")
//    public ApiResult<?> saveBatch(@RequestBody List<BgmVendorContact> list) {
//        if (bgmVendorContactService.saveBatch(list)) {
//            return success("添加成功");
//        }
//        return fail("添加失败");
//    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:update')")
    @OperationLog
    @ApiOperation("批量修改")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmVendorContact> batchParam) {
        if (batchParam.update(bgmVendorContactService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorContact:remove')")
    @OperationLog
    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (bgmVendorContactService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
