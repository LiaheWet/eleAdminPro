package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmVendorMaterialMapper;
import com.eleadmin.bgm.service.BgmVendorMaterialService;
import com.eleadmin.bgm.entity.BgmVendorMaterial;
import com.eleadmin.bgm.param.BgmVendorMaterialParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商供应材料Service实现
 *
 * @author EleAdmin
 * @since 2023-06-03 18:58:27
 */
@Service
public class BgmVendorMaterialServiceImpl extends ServiceImpl<BgmVendorMaterialMapper, BgmVendorMaterial> implements BgmVendorMaterialService {

    @Override
    public PageResult<BgmVendorMaterial> pageRel(BgmVendorMaterialParam param) {
        PageParam<BgmVendorMaterial, BgmVendorMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmVendorMaterial> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmVendorMaterialParam> listRel(BgmVendorMaterialParam param) {
        List<BgmVendorMaterialParam> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmVendorMaterialParam, BgmVendorMaterialParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmVendorMaterialParam getByIdRel(Integer id) {
        BgmVendorMaterialParam param = new BgmVendorMaterialParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
