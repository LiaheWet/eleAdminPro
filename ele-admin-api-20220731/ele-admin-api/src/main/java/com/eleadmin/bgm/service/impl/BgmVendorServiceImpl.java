package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmVendorMapper;
import com.eleadmin.bgm.service.BgmVendorService;
import com.eleadmin.bgm.entity.BgmVendor;
import com.eleadmin.bgm.param.BgmVendorParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商Service实现
 *
 * @author EleAdmin
 * @since 2023-04-25 16:31:08
 */
@Service
public class BgmVendorServiceImpl extends ServiceImpl<BgmVendorMapper, BgmVendor> implements BgmVendorService {

    @Autowired
    private BgmVendorMapper bgmVendorMapper;
    @Override
    public PageResult<BgmVendor> pageRel(BgmVendorParam param) {
        PageParam<BgmVendor, BgmVendorParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmVendor> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmVendor> listRel(BgmVendorParam param) {
        List<BgmVendor> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmVendor, BgmVendorParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmVendor getByIdRel(Integer vendorId) {
        BgmVendorParam param = new BgmVendorParam();
        param.setVendorId(vendorId);
        return param.getOne(baseMapper.selectListRel(param));
    }

/*    @Override
    public PageResult<BgmVendorParam> pageList(BgmVendorParam param) {
        PageParam<BgmVendorParam,BgmVendorParam>pageParam=new PageParam<>(param);
        List<BgmVendorParam>list=bgmVendorMapper.pageList(pageParam,param);
        return new PageResult<>(list,pageParam.getTotal());
    }*/

    @Override
    public BgmVendorParam selectPro(BgmVendorParam param) {
        PageParam<BgmVendorParam,BgmVendorParam>pageParam=new PageParam<>(param);
        return (bgmVendorMapper.pageList(pageParam,param));
    }

}
