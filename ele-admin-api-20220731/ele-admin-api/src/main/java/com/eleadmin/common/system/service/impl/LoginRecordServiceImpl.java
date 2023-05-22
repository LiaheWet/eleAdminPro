package com.eleadmin.common.system.service.impl;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.entity.LoginRecord;
import com.eleadmin.common.system.mapper.LoginRecordMapper;
import com.eleadmin.common.system.param.LoginRecordParam;
import com.eleadmin.common.system.service.LoginRecordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录日志Service实现
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:14
 */
@Service
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecord>
        implements LoginRecordService {

    @Override
    public PageResult<LoginRecord> pageRel(LoginRecordParam param) {
        PageParam<LoginRecord, LoginRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return new PageResult<>(baseMapper.selectPageRel(page, param), page.getTotal());
    }

    @Override
    public List<LoginRecord> listRel(LoginRecordParam param) {
        PageParam<LoginRecord, LoginRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(baseMapper.selectListRel(param));
    }

    @Override
    public LoginRecord getByIdRel(Integer id) {
        LoginRecordParam param = new LoginRecordParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Async
    @Override
    public void saveAsync(String username, Integer type, String comments, Integer tenantId,
                          HttpServletRequest request) {
        if (username == null) {
            return;
        }
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setUsername(username);
        loginRecord.setLoginType(type);
        loginRecord.setComments(comments);
        loginRecord.setTenantId(tenantId);
        UserAgent ua = UserAgentUtil.parse(ServletUtil.getHeaderIgnoreCase(request, "User-Agent"));
        if (ua != null) {
            if (ua.getPlatform() != null) {
                loginRecord.setOs(ua.getPlatform().toString());
            }
            if (ua.getOs() != null) {
                loginRecord.setDevice(ua.getOs().toString());
            }
            if (ua.getBrowser() != null) {
                loginRecord.setBrowser(ua.getBrowser().toString());
            }
        }
        loginRecord.setIp(ServletUtil.getClientIP(request));
        baseMapper.insert(loginRecord);
    }

}
