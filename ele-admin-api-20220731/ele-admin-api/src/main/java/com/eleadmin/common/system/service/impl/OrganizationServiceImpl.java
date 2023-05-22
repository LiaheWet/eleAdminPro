package com.eleadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.entity.Organization;
import com.eleadmin.common.system.mapper.OrganizationMapper;
import com.eleadmin.common.system.param.OrganizationParam;
import com.eleadmin.common.system.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织机构Service实现
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:04
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization>
        implements OrganizationService {

    @Override
    public PageResult<Organization> pageRel(OrganizationParam param) {
        PageParam<Organization, OrganizationParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return new PageResult<>(baseMapper.selectPageRel(page, param), page.getTotal());
    }

    @Override
    public List<Organization> listRel(OrganizationParam param) {
        PageParam<Organization, OrganizationParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return page.sortRecords(baseMapper.selectListRel(param));
    }

    @Override
    public Organization getByIdRel(Integer organizationId) {
        OrganizationParam param = new OrganizationParam();
        param.setOrganizationId(organizationId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
