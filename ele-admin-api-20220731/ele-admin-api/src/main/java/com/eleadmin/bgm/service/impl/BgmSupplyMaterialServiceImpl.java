package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmSupplyMaterialMapper;
import com.eleadmin.bgm.service.BgmSupplyMaterialService;
import com.eleadmin.bgm.entity.BgmSupplyMaterial;
import com.eleadmin.bgm.param.BgmSupplyMaterialParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应的原材料(关联表;多对多)Service实现
 *
 * @author EleAdmin
 * @since 2023-05-17 12:15:09
 */
@Service
public class BgmSupplyMaterialServiceImpl extends ServiceImpl<BgmSupplyMaterialMapper, BgmSupplyMaterial> implements BgmSupplyMaterialService {

    @Autowired
    private BgmSupplyMaterialMapper bgmSupplyMaterialMapper;
    @Override
    public PageResult<BgmSupplyMaterial> pageRel(BgmSupplyMaterialParam param) {
        PageParam<BgmSupplyMaterial, BgmSupplyMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmSupplyMaterial> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmSupplyMaterial> listRel(BgmSupplyMaterialParam param) {
        List<BgmSupplyMaterial> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmSupplyMaterial, BgmSupplyMaterialParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmSupplyMaterial getByIdRel(Integer supplyMaterialId) {
        BgmSupplyMaterialParam param = new BgmSupplyMaterialParam();
        param.setSupplyMaterialId(supplyMaterialId);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public PageResult<BgmSupplyMaterialParam> pageList(BgmSupplyMaterialParam param) {
        PageParam<BgmSupplyMaterialParam, BgmSupplyMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmSupplyMaterialParam> list = bgmSupplyMaterialMapper.pageList(page,param);
        return new PageResult<>(list, page.getTotal());
    }

}
