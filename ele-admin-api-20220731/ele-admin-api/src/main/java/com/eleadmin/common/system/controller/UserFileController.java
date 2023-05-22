package com.eleadmin.common.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.eleadmin.common.core.utils.FileServerUtil;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.common.system.service.UserFileService;
import com.eleadmin.common.system.entity.UserFile;
import com.eleadmin.common.system.param.UserFileParam;
import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户文件控制器
 *
 * @author EleAdmin
 * @since 2022-07-21 14:34:40
 */
@Api(tags = "用户文件管理")
@RestController
@RequestMapping("/api/system/user-file")
public class UserFileController extends BaseController {
    @Resource
    private UserFileService userFileService;

    @OperationLog
    @ApiOperation("分页查询用户文件")
    @GetMapping("/page")
    public ApiResult<PageResult<UserFile>> page(UserFileParam param, HttpServletRequest request) {
        param.setUserId(getLoginUserId());
        PageParam<UserFile, UserFileParam> page = new PageParam<>(param);
        page.setDefaultOrder("is_directory desc");
        PageParam<UserFile, UserFileParam> result = userFileService.page(page, page.getWrapper());
        List<UserFile> records = result.getRecords();
        String requestURL = StrUtil.removeSuffix(request.getRequestURL(), "/system/user-file") + "/file";
        for (UserFile record : records) {
            if (StrUtil.isNotBlank(record.getPath())) {
                record.setUrl(requestURL + "/" + record.getPath());
                if (FileServerUtil.isImage(record.getContentType())) {
                    record.setThumbnail(requestURL + "/thumbnail/" + record.getPath());
                }
                record.setDownloadUrl(requestURL + "/download/" + record.getPath());
            }
        }
        return success(records, result.getTotal());
    }

    @OperationLog
    @ApiOperation("查询全部用户文件")
    @GetMapping()
    public ApiResult<List<UserFile>> list(UserFileParam param, HttpServletRequest request) {
        param.setUserId(getLoginUserId());
        PageParam<UserFile, UserFileParam> page = new PageParam<>(param);
        page.setDefaultOrder("is_directory desc");
        List<UserFile> records = userFileService.list(page.getOrderWrapper());
        String requestURL = StrUtil.removeSuffix(request.getRequestURL(), "/system/user-file") + "/file";
        for (UserFile record : records) {
            if (StrUtil.isNotBlank(record.getPath())) {
                record.setUrl(requestURL + "/" + record.getPath());
                if (FileServerUtil.isImage(record.getContentType())) {
                    record.setThumbnail(requestURL + "/thumbnail/" + record.getPath());
                }
                record.setDownloadUrl(requestURL + "/download/" + record.getPath());
            }
        }
        return success(records);
    }

    @PreAuthorize("hasAuthority('sys:auth:user')")
    @OperationLog
    @ApiOperation("添加用户文件")
    @PostMapping()
    public ApiResult<?> save(@RequestBody UserFile userFile) {
        userFile.setUserId(getLoginUserId());
        if (userFile.getParentId() == null) {
            userFile.setParentId(0);
        }
        if (userFile.getIsDirectory() != null && userFile.getIsDirectory().equals(1)) {
            if (userFileService.count(new LambdaQueryWrapper<UserFile>()
                    .eq(UserFile::getName, userFile.getName())
                    .eq(UserFile::getParentId, userFile.getParentId())
                    .eq(UserFile::getUserId, userFile.getUserId())) > 0) {
                return fail("文件夹名称已存在");
            }
        }
        if (userFileService.save(userFile)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:auth:user')")
    @OperationLog
    @ApiOperation("修改用户文件")
    @PutMapping()
    public ApiResult<?> update(@RequestBody UserFile userFile) {
        Integer loginUserId = getLoginUserId();
        UserFile old = userFileService.getById(userFile.getId());
        UserFile entity = new UserFile();
        if (StrUtil.isNotBlank(userFile.getName())) {
            entity.setName(userFile.getName());
        }
        if (userFile.getParentId() != null) {
            entity.setParentId(userFile.getParentId());
        }
        if (!old.getUserId().equals(loginUserId) ||
                (entity.getName() == null && entity.getParentId() == null)) {
            return fail("修改失败");
        }
        if (old.getIsDirectory() != null && old.getIsDirectory().equals(1)) {
            if (userFileService.count(new LambdaQueryWrapper<UserFile>()
                    .eq(UserFile::getName, entity.getName() == null ? old.getName() : entity.getName())
                    .eq(UserFile::getParentId, entity.getParentId() == null ? old.getParentId() : entity.getParentId())
                    .eq(UserFile::getUserId, loginUserId)
                    .ne(UserFile::getId, old.getId())) > 0) {
                return fail("文件夹名称已存在");
            }
        }
        if (userFileService.update(entity, new LambdaUpdateWrapper<UserFile>()
                .eq(UserFile::getId, userFile.getId())
                .eq(UserFile::getUserId, loginUserId))) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:auth:user')")
    @OperationLog
    @ApiOperation("删除用户文件")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (userFileService.remove(new LambdaUpdateWrapper<UserFile>()
                .eq(UserFile::getId, id)
                .eq(UserFile::getUserId, getLoginUserId()))) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:auth:user')")
    @OperationLog
    @ApiOperation("批量删除用户文件")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (userFileService.remove(new LambdaUpdateWrapper<UserFile>()
                .in(UserFile::getId, ids)
                .eq(UserFile::getUserId, getLoginUserId()))) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
