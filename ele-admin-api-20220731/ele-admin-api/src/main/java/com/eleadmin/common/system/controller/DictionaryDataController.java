package com.eleadmin.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eleadmin.common.core.annotation.OperationLog;
import com.eleadmin.common.core.web.*;
import com.eleadmin.common.system.entity.DictionaryData;
import com.eleadmin.common.system.param.DictionaryDataParam;
import com.eleadmin.common.system.service.DictionaryDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据控制器
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:04
 */
@Api(tags = "字典数据管理")
@RestController
@RequestMapping("/api/system/dictionary-data")
public class DictionaryDataController extends BaseController {
    @Resource
    private DictionaryDataService dictionaryDataService;

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @OperationLog
    @ApiOperation("分页查询字典数据")
    @GetMapping("/page")
    public ApiResult<PageResult<DictionaryData>> page(DictionaryDataParam param) {
        return success(dictionaryDataService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @OperationLog
    @ApiOperation("查询全部字典数据")
    @GetMapping()
    public ApiResult<List<DictionaryData>> list(DictionaryDataParam param) {
        return success(dictionaryDataService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @OperationLog
    @ApiOperation("根据id查询字典数据")
    @GetMapping("/{id}")
    public ApiResult<DictionaryData> get(@PathVariable("id") Integer id) {
        return success(dictionaryDataService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @OperationLog
    @ApiOperation("添加字典数据")
    @PostMapping()
    public ApiResult<?> add(@RequestBody DictionaryData dictionaryData) {
        if (dictionaryDataService.count(new LambdaQueryWrapper<DictionaryData>()
                .eq(DictionaryData::getDictId, dictionaryData.getDictId())
                .eq(DictionaryData::getDictDataName, dictionaryData.getDictDataName())) > 0) {
            return fail("字典数据名称已存在");
        }
        if (dictionaryDataService.count(new LambdaQueryWrapper<DictionaryData>()
                .eq(DictionaryData::getDictId, dictionaryData.getDictId())
                .eq(DictionaryData::getDictDataCode, dictionaryData.getDictDataCode())) > 0) {
            return fail("字典数据标识已存在");
        }
        if (dictionaryDataService.save(dictionaryData)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:update')")
    @OperationLog
    @ApiOperation("修改字典数据")
    @PutMapping()
    public ApiResult<?> update(@RequestBody DictionaryData dictionaryData) {
        if (dictionaryDataService.count(new LambdaQueryWrapper<DictionaryData>()
                .eq(DictionaryData::getDictId, dictionaryData.getDictId())
                .eq(DictionaryData::getDictDataName, dictionaryData.getDictDataName())
                .ne(DictionaryData::getDictDataId, dictionaryData.getDictDataId())) > 0) {
            return fail("字典数据名称已存在");
        }
        if (dictionaryDataService.count(new LambdaQueryWrapper<DictionaryData>()
                .eq(DictionaryData::getDictId, dictionaryData.getDictId())
                .eq(DictionaryData::getDictDataCode, dictionaryData.getDictDataCode())
                .ne(DictionaryData::getDictDataId, dictionaryData.getDictDataId())) > 0) {
            return fail("字典数据标识已存在");
        }
        if (dictionaryDataService.updateById(dictionaryData)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @OperationLog
    @ApiOperation("删除字典数据")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (dictionaryDataService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @OperationLog
    @ApiOperation("批量添加字典数据")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<DictionaryData> dictDataList) {
        if (dictionaryDataService.saveBatch(dictDataList)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @OperationLog
    @ApiOperation("批量删除字典数据")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (dictionaryDataService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
