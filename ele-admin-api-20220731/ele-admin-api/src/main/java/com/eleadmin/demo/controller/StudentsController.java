package com.eleadmin.demo.controller;

import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.demo.service.StudentsService;
import com.eleadmin.demo.entity.Students;
import com.eleadmin.demo.param.StudentsParam;
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
 * @since 2023-03-06 22:00:02
 */
@Api(tags = "管理")
@RestController
@RequestMapping("/api/demo/students")
public class StudentsController extends BaseController {
    @Resource
    private StudentsService studentsService;

    @PreAuthorize("hasAuthority('demo:students:list')")
    @OperationLog
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<Students>> page(StudentsParam param) {
        PageParam<Students, StudentsParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(studentsService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(studentsService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('demo:students:list')")
    @OperationLog
    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<Students>> list(StudentsParam param) {
        PageParam<Students, StudentsParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return success(studentsService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(studentsService.listRel(param));
    }

    @PreAuthorize("hasAuthority('demo:students:list')")
    @OperationLog
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public ApiResult<Students> get(@PathVariable("id") Integer id) {
        return success(studentsService.getById(id));
        // 使用关联查询
        //return success(studentsService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('demo:students:save')")
    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Students students) {
        if (studentsService.save(students)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('demo:students:update')")
    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Students students) {
        if (studentsService.updateById(students)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('demo:students:remove')")
    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (studentsService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('demo:students:save')")
    @OperationLog
    @ApiOperation("批量添加")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Students> list) {
        if (studentsService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('demo:students:update')")
    @OperationLog
    @ApiOperation("批量修改")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<Students> batchParam) {
        if (batchParam.update(studentsService, "student_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('demo:students:remove')")
    @OperationLog
    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (studentsService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
