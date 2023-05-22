package com.eleadmin.common.system.controller;

import com.eleadmin.common.core.annotation.OperationLog;
import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.entity.LoginRecord;
import com.eleadmin.common.system.param.LoginRecordParam;
import com.eleadmin.common.system.service.LoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:31
 */
@Api(tags = "登录日志")
@RestController
@RequestMapping("/api/system/login-record")
public class LoginRecordController extends BaseController {
    @Resource
    private LoginRecordService loginRecordService;

    @PreAuthorize("hasAuthority('sys:login-record:list')")
    @OperationLog
    @ApiOperation("分页查询登录日志")
    @GetMapping("/page")
    public ApiResult<PageResult<LoginRecord>> page(LoginRecordParam param) {
        return success(loginRecordService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:login-record:list')")
    @OperationLog
    @ApiOperation("查询全部登录日志")
    @GetMapping()
    public ApiResult<List<LoginRecord>> list(LoginRecordParam param) {
        return success(loginRecordService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:login-record:list')")
    @OperationLog
    @ApiOperation("根据id查询登录日志")
    @GetMapping("/{id}")
    public ApiResult<LoginRecord> get(@PathVariable("id") Integer id) {
        return success(loginRecordService.getByIdRel(id));
    }

}
