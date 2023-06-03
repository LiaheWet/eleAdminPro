package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmVendorContactMapper;
import com.eleadmin.bgm.service.BgmVendorContactService;
import com.eleadmin.bgm.entity.BgmVendorContact;
import com.eleadmin.bgm.param.BgmVendorContactParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系人信息Service实现
 *
 * @author EleAdmin
 * @since 2023-06-03 16:15:33
 */
@Service
public class BgmVendorContactServiceImpl extends ServiceImpl<BgmVendorContactMapper, BgmVendorContact> implements BgmVendorContactService {

    @Override
    public PageResult<BgmVendorContact> pageRel(BgmVendorContactParam param) {
        PageParam<BgmVendorContact, BgmVendorContactParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmVendorContact> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmVendorContact> listRel(BgmVendorContactParam param) {
        List<BgmVendorContact> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmVendorContact, BgmVendorContactParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmVendorContact getByIdRel(Integer id) {
        BgmVendorContactParam param = new BgmVendorContactParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }


    @Override
    public List<BgmVendorContact> listByVendorId(Integer vendorId) {
        LambdaQueryWrapper<BgmVendorContact> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(BgmVendorContact::getVendorId, vendorId);
        return baseMapper.selectList(wrapper);
    }

}
