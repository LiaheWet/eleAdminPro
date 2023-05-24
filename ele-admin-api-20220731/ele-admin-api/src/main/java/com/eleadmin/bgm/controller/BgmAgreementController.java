package com.eleadmin.bgm.controller;

import com.eleadmin.bgm.entity.BgmAgreementMaterial;
import com.eleadmin.bgm.service.BgmAgreementMaterialService;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.bgm.service.BgmAgreementService;
import com.eleadmin.bgm.entity.BgmAgreement;
import com.eleadmin.bgm.param.BgmAgreementParam;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 采购协议控制器
 *
 * @author EleAdmin
 * @since 2023-04-26 21:05:21
 */
@Api(tags = "采购协议管理")
@RestController
@RequestMapping("/api/bgm/bgmAgreement")
public class BgmAgreementController extends BaseController {
    @Resource
    private BgmAgreementService bgmAgreementService;
    @Resource
    private BgmAgreementMaterialService bgmAgreementMaterialService;

    /*    @PreAuthorize("hasAuthority('bgm:bgmAgreement:list')")
        @OperationLog
        @ApiOperation("查询一个采购协议ByAgreementID")
        @GetMapping("/byId")
        public ApiResult<List<BgmAgreementParam>> selectPro(BgmAgreementParam param) {
            //pro
            //PageResult<BgmAgreementParam> list = bgmAgreementService.pageList(param);
            List<BgmAgreementParam>list=bgmAgreementService.listPro(param);
            return success(list);
        }*/
    @PreAuthorize("hasAuthority('bgm:bgmAgreement:list')")
    @OperationLog
    @ApiOperation("查询一个采购协议")
    @GetMapping("/selectPro")
    public ApiResult<BgmAgreementParam> selectPro(BgmAgreementParam param) {
        return success(bgmAgreementService.selectPro(param));
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:save')")
    @OperationLog
    @ApiOperation("添加采购协议")
    @PostMapping("/savePro")
    public ApiResult<?> savePro(@RequestBody BgmAgreementParam bgmAgreementParam) {
        List<BgmAgreementMaterial> materialList = new ArrayList<>();
        String agreementNumber = bgmAgreementParam.getAgreementNumber();
        for (BgmAgreementMaterial param : bgmAgreementParam.getSupplyMaterialList()) {
            BgmAgreementMaterial material = new BgmAgreementMaterial();
            material.setAmNumber(agreementNumber);
            material.setCreateTime(param.getCreateTime());
            material.setSupplyName(param.getSupplyName());
            material.setMaterialName(param.getMaterialName());
            material.setMeasurement(param.getMeasurement());
            material.setPrice(param.getPrice());
            material.setNumber(param.getNumber());
            material.setTotalPrice(param.getTotalPrice());
            materialList.add(material);
        }
        if (bgmAgreementMaterialService.saveBatch(materialList)) {
            if (bgmAgreementService.savePro(bgmAgreementParam)) {
                return success("添加成功");
            }
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:update')")
    @OperationLog
    @ApiOperation("修改采购协议")
    @PutMapping("/updatePro")
    public ApiResult<?> updatePro(@RequestBody BgmAgreementParam bgmAgreementParam) {
        bgmAgreementMaterialService.hasRemove(bgmAgreementParam.getAgreementNumber());
        List<BgmAgreementMaterial> materialList = new ArrayList<>();
        for (BgmAgreementMaterial param : bgmAgreementParam.getSupplyMaterialList()) {
            BgmAgreementMaterial material = new BgmAgreementMaterial();
            material.setAmNumber(bgmAgreementParam.getAgreementNumber());
            material.setCreateTime(param.getCreateTime());
            material.setSupplyName(param.getSupplyName());
            material.setMaterialName(param.getMaterialName());
            material.setMeasurement(param.getMeasurement());
            material.setPrice(param.getPrice());
            material.setNumber(param.getNumber());
            material.setTotalPrice(param.getTotalPrice());
            materialList.add(material);
        }
        if (bgmAgreementMaterialService.saveBatch(materialList)) {
            if (bgmAgreementService.updatePro(bgmAgreementParam)) {
                return success("添加成功");
            }
        }

        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:remove')")
    @OperationLog
    @ApiOperation("删除采购协议")
    @DeleteMapping("/{id}")
    public ApiResult<?> removePro(@PathVariable("id") Integer id) {
        BgmAgreement bgmAgreement = bgmAgreementService.getByIdRel(id);
        bgmAgreementMaterialService.hasRemove(bgmAgreement.getAgreementNumber());
        if (bgmAgreementService.removeById(id)) {
            return success("删除成功");
        }

        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:list')")
    @OperationLog
    @ApiOperation("分页查询采购协议")
    @GetMapping("/page")
    public ApiResult<PageResult<BgmAgreement>> page(BgmAgreementParam param) throws ParseException {
        //PageParam<BgmAgreement, BgmAgreementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        //return success(bgmAgreementService.page(page, page.getWrapper()));
        // 使用关联查询
        return success(bgmAgreementService.pageRel(param));
    }
    @PreAuthorize("hasAuthority('bgm:bgmAgreement:list')")
    @OperationLog
    @ApiOperation("查询全部采购协议")
    @GetMapping()
    public ApiResult<List<BgmAgreement>> list(BgmAgreementParam param) {
        PageParam<BgmAgreement, BgmAgreementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(bgmAgreementService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(bgmAgreementService.listRel(param));
    }

/*    @PreAuthorize("hasAuthority('bgm:bgmAgreement:list')")
    @OperationLog
    @ApiOperation("根据id查询采购协议")
    @GetMapping("/{id}")
    public ApiResult<BgmAgreement> get(@PathVariable("id") Integer id) {
        return success(bgmAgreementService.getById(id));
        // 使用关联查询
        //return success(bgmAgreementService.getByIdRel(id));
    }*/

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:save')")
    @OperationLog
    @ApiOperation("添加采购协议")
    @PostMapping()
    public ApiResult<?> save(@RequestBody BgmAgreement bgmAgreement) {
        if (bgmAgreementService.save(bgmAgreement)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:update')")
    @OperationLog
    @ApiOperation("修改采购协议")
    @PutMapping()
    public ApiResult<?> update(@RequestBody BgmAgreement bgmAgreement) {
        if (bgmAgreementService.updateById(bgmAgreement)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }


    @PreAuthorize("hasAuthority('bgm:bgmAgreement:save')")
    @OperationLog
    @ApiOperation("批量添加采购协议")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<BgmAgreement> list) {
        if (bgmAgreementService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:update')")
    @OperationLog
    @ApiOperation("批量修改采购协议")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<BgmAgreement> batchParam) {
        if (batchParam.update(bgmAgreementService, "agreement_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('bgm:bgmAgreement:remove')")
    @OperationLog
    @ApiOperation("批量删除采购协议")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        System.out.println("删除" + ids);
        /*if (bgmAgreementService.removeByIds(ids)) {
            return success("删除成功");
        }*/
        return fail("删除失败");
    }



}
