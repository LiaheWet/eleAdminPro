package com.eleadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.common.system.entity.EmailRecord;
import com.eleadmin.common.system.mapper.EmailRecordMapper;
import com.eleadmin.common.system.service.EmailRecordService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

/**
 * 邮件发送记录Service实现
 *
 * @author EleAdmin
 * @since 2019-06-19 04:07:54
 */
@Service
public class EmailRecordServiceImpl extends ServiceImpl<EmailRecordMapper, EmailRecord>
        implements EmailRecordService {
    // 发件邮箱
    @Value("${spring.mail.username}")
    private String formEmail;
    @Resource
    private JavaMailSender mailSender;

    @Override
    public void sendTextEmail(String title, String content, String[] toEmails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(formEmail);
        message.setTo(toEmails);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendFullTextEmail(String title, String html, String[] toEmails) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(formEmail);
        helper.setTo(toEmails);
        helper.setSubject(title);
        // 发送邮件
        helper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendHtmlEmail(String title, String path, Map<String, Object> map, String[] toEmails)
            throws MessagingException, IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("templates/");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate(path);  // 加载html模板
        t.binding(map);  // 填充数据
        String html = t.render();  // 获得渲染后的html
        sendFullTextEmail(title, html, toEmails);  // 发送邮件
    }

}
