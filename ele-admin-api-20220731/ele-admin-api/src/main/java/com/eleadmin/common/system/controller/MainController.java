package com.eleadmin.common.system.controller;

import cn.hutool.core.util.StrUtil;
import com.eleadmin.common.core.annotation.OperationLog;
import com.eleadmin.common.core.config.ConfigProperties;
import com.eleadmin.common.core.security.JwtSubject;
import com.eleadmin.common.core.security.JwtUtil;
import com.eleadmin.common.core.utils.CommonUtil;
import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.common.system.entity.LoginRecord;
import com.eleadmin.common.system.entity.Menu;
import com.eleadmin.common.system.entity.User;
import com.eleadmin.common.system.result.CaptchaResult;
import com.eleadmin.common.system.param.LoginParam;
import com.eleadmin.common.system.result.LoginResult;
import com.eleadmin.common.system.param.UpdatePasswordParam;
import com.eleadmin.common.system.service.LoginRecordService;
import com.eleadmin.common.system.service.RoleMenuService;
import com.eleadmin.common.system.service.UserService;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 登录认证控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:11
 */
@Api(tags = "登录认证")
@RestController
@RequestMapping("/api")
public class MainController extends BaseController {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private LoginRecordService loginRecordService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ApiResult<LoginResult> login(@RequestBody LoginParam param, HttpServletRequest request) {
        String username = param.getUsername();
        Integer tenantId = param.getTenantId();
        User user = userService.getByUsername(username, tenantId);
        if (user == null) {
            String message = "账号不存在";
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, tenantId, request);
            return fail(message, null);
        }
        if (!user.getStatus().equals(0)) {
            String message = "账号被冻结";
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, tenantId, request);
            return fail(message, null);
        }
        if (!userService.comparePassword(user.getPassword(), param.getPassword())) {
            String message = "密码错误";
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, tenantId, request);
            return fail(message, null);
        }
        loginRecordService.saveAsync(username, LoginRecord.TYPE_LOGIN, null, tenantId, request);
        // 签发token
        String access_token = JwtUtil.buildToken(new JwtSubject(username, tenantId),
                configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        return success("登录成功", new LoginResult(access_token, user));
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/auth/user")
    public ApiResult<User> userInfo() {
        return success(userService.getByIdRel(getLoginUserId()));
    }

    @ApiOperation("获取登录用户菜单")
    @GetMapping("/auth/menu")
    public ApiResult<List<Menu>> userMenu() {
        List<Menu> menus = roleMenuService.listMenuByUserId(getLoginUserId(), Menu.TYPE_MENU);
        return success(CommonUtil.toTreeData(menus, 0, Menu::getParentId, Menu::getMenuId, Menu::setChildren));
    }

    @PreAuthorize("hasAuthority('sys:auth:user')")
    @OperationLog
    @ApiOperation("修改个人信息")
    @PutMapping("/auth/user")
    public ApiResult<User> updateInfo(@RequestBody User user) {
        user.setUserId(getLoginUserId());
        // 不能修改的字段
        user.setUsername(null);
        user.setPassword(null);
        user.setEmailVerified(null);
        user.setOrganizationId(null);
        user.setStatus(null);
        if (userService.updateById(user)) {
            return success(userService.getByIdRel(user.getUserId()));
        }
        return fail("保存失败", null);
    }

    @PreAuthorize("hasAuthority('sys:auth:password')")
    @OperationLog
    @ApiOperation("修改自己密码")
    @PutMapping("/auth/password")
    public ApiResult<?> updatePassword(@RequestBody UpdatePasswordParam param) {
        if (StrUtil.hasBlank(param.getOldPassword(), param.getPassword())) {
            return fail("参数不能为空");
        }
        Integer userId = getLoginUserId();
        if (userId == null) {
            return fail("未登录");
        }
        if (!userService.comparePassword(userService.getById(userId).getPassword(), param.getOldPassword())) {
            return fail("原密码输入不正确");
        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(userService.encodePassword(param.getPassword()));
        if (userService.updateById(user)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("图形验证码")
    @GetMapping("/captcha")
    public ApiResult<CaptchaResult> captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        return success(new CaptchaResult(specCaptcha.toBase64(), specCaptcha.text().toLowerCase()));
    }

}
