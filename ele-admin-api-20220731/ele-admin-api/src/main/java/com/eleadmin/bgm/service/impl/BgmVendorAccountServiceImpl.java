package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmVendorAccountMapper;
import com.eleadmin.bgm.service.BgmVendorAccountService;
import com.eleadmin.bgm.entity.BgmVendorAccount;
import com.eleadmin.bgm.param.BgmVendorAccountParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商账户信息Service实现
 *
 * @author EleAdmin
 * @since 2023-06-03 16:13:47
 */
@Service
public class BgmVendorAccountServiceImpl extends ServiceImpl<BgmVendorAccountMapper, BgmVendorAccount> implements BgmVendorAccountService {

    @Override
    public PageResult<BgmVendorAccount> pageRel(BgmVendorAccountParam param) {
        PageParam<BgmVendorAccount, BgmVendorAccountParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmVendorAccount> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmVendorAccount> listRel(BgmVendorAccountParam param) {
        List<BgmVendorAccount> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmVendorAccount, BgmVendorAccountParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmVendorAccount getByIdRel(Integer id) {
        BgmVendorAccountParam param = new BgmVendorAccountParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public List<BgmVendorAccount> listByVendorId(Integer vendorId) {
        LambdaQueryWrapper<BgmVendorAccount> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(BgmVendorAccount::getVendorId, vendorId);
        return baseMapper.selectList(wrapper);
    }

}
