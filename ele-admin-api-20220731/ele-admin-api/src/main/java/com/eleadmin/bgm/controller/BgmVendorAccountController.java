package com.eleadmin.bgm.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 *
 * @author EleAdmin
 * @since 2023-05-01 22:03:18
 */
@Api(tags = "管理")
@RestController
@RequestMapping("/api/bgm/bgm-vendor-account")
public class BgmVendorAccountController extends BaseController {
    @Resource
    private BgmVendorAccountService bgmVendorAccountService;

    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:list')")
    @OperationLog
    @ApiOperation("分页查询")
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
    @ApiOperation("查询全部")
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
    @ApiOperation("根据vendorId查询")
    @GetMapping("/{id}")
    public ApiResult<List<BgmVendorAccount>> listByVendorId(@PathVariable("id")Integer vendorId) {
        System.out.println("#### cha xun BgmVendorAccount VendorId ####"+vendorId);
        List<BgmVendorAccount> list = bgmVendorAccountService.listByVendorId(vendorId);
        return success(list);
    }

//    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:list')")
//    @OperationLog
//    @ApiOperation("根据id查询")
//    @GetMapping("/{id}")
//    public ApiResult<BgmVendorAccount> get(@PathVariable("id") Integer VendorId) {
//        System.out.println("#### VendorId ####"+VendorId);
//        return success(bgmVendorAccountService.getById(VendorId));
//        // 使用关联查询
//        //return success(bgmVendorAccountService.getByIdRel(id));
//    }


//    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:save')")
//    @OperationLog
//    @ApiOperation("添加")
//    @PostMapping()
//    public ApiResult<?> saveOrUpdate(@RequestBody @PathVariable("accountList") ArrayList<BgmVendorAccount> accountList) {
//        for (BgmVendorAccount b:accountList
//             ) {
//            bgmVendorAccountService.saveOrUpdate(b);
//        }
////        if (bgmVendorAccountService.save(bgmVendorAccount)) {
////            return success("添加成功");
////        }
////        return fail("添加失败");
//
//
//        return null;
//    }

   /* @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:save')")
    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmVendorAccount bgmVendorAccount) {
        if (bgmVendorAccountService.save(bgmVendorAccount)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }*/

    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:update')")
    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmVendorAccount bgmVendorAccount) {
        if (bgmVendorAccountService.updateById(bgmVendorAccount)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:remove')")
    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        System.out.println("removing account " + id);
        if (bgmVendorAccountService.removeById(id)) {
            System.out.println("remove successfully " + id);
            return success("删除成功");
        }
        return fail("删除失败");
    }

//

    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:save')")
    @OperationLog
    @ApiOperation("批量添加")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody  ArrayList<BgmVendorAccount> accountList) {
        if (bgmVendorAccountService.saveOrUpdateBatch(accountList)) {
            return success("添加成功");
        }
        return fail("添加失败");

    }

//    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:save')")
//    @OperationLog
//    @ApiOperation("批量添加")
//    @PostMapping("/batch")
//    public ApiResult<?> saveBatch(@RequestBody List<BgmVendorAccount> list) {
//        if (bgmVendorAccountService.saveOrUpdateBatch(list)) {
//            return success("添加成功");
//        }
//        return fail("添加失败");
//    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:update')")
    @OperationLog
    @ApiOperation("批量修改")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmVendorAccount> batchParam) {
        if (batchParam.update(bgmVendorAccountService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmVendorAccount:remove')")
    @OperationLog
    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody ArrayList<Integer> accountDeleteList) {
        System.out.println(" ### shan chu"+accountDeleteList);
        if (bgmVendorAccountService.removeByIds(accountDeleteList)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
