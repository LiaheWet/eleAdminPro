package com.eleadmin.common.system.controller;

import com.eleadmin.common.core.annotation.OperationLog;
import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.BaseController;
import com.eleadmin.common.system.entity.EmailRecord;
import com.eleadmin.common.system.service.EmailRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;

/**
 * 邮件功能控制器
 *
 * @author EleAdmin
 * @since 2020-03-21 00:37:11
 */
@Api(tags = "邮件功能")
@RestController
@RequestMapping("/api/system/email")
public class EmailController extends BaseController {
    @Resource
    private EmailRecordService emailRecordService;

    @PreAuthorize("hasAuthority('sys:email:send')")
    @OperationLog
    @ApiOperation("发送邮件")
    @PostMapping()
    public ApiResult<?> send(@RequestBody EmailRecord emailRecord) {
        try {
            emailRecordService.sendFullTextEmail(emailRecord.getTitle(), emailRecord.getContent(),
                    emailRecord.getReceiver().split(","));
            emailRecord.setCreateUserId(getLoginUserId());
            emailRecordService.save(emailRecord);
            return success("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return fail("发送失败");
    }

}
