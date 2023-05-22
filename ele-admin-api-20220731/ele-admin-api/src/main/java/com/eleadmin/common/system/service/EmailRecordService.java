package com.eleadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.system.entity.EmailRecord;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * 邮件发送记录Service
 *
 * @author EleAdmin
 * @since 2019-06-19 04:07:02
 */
public interface EmailRecordService extends IService<EmailRecord> {

    /**
     * 发送普通邮件
     *
     * @param title    标题
     * @param content  内容
     * @param toEmails 收件人
     */
    void sendTextEmail(String title, String content, String[] toEmails);

    /**
     * 发送富文本邮件
     *
     * @param title    标题
     * @param html     富文本
     * @param toEmails 收件人
     * @throws MessagingException MessagingException
     */
    void sendFullTextEmail(String title, String html, String[] toEmails) throws MessagingException;

    /**
     * 发送模板邮件
     *
     * @param title    标题
     * @param path     模板路径
     * @param map      填充数据
     * @param toEmails 收件人
     * @throws MessagingException MessagingException
     * @throws IOException        IOException
     */
    void sendHtmlEmail(String title, String path, Map<String, Object> map, String[] toEmails)
            throws MessagingException, IOException;

}
