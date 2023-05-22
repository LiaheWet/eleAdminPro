package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.entity.BgmMaterialType;
import com.eleadmin.bgm.mapper.BgmMaterialMapper;
import com.eleadmin.bgm.param.BgmMaterialTypeParam;
import com.eleadmin.bgm.service.BgmMaterialService;
import com.eleadmin.bgm.entity.BgmMaterial;
import com.eleadmin.bgm.param.BgmMaterialParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service实现
 *
 * @author EleAdmin
 * @since 2023-04-15 12:46:34
 */
@Service
public class BgmMaterialServiceImpl extends ServiceImpl<BgmMaterialMapper, BgmMaterial> implements BgmMaterialService {

    @Override
    public PageResult<BgmMaterial> pageRel(BgmMaterialParam param) {
        PageParam<BgmMaterial, BgmMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmMaterial> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmMaterial> listRel(BgmMaterialParam param) {
        List<BgmMaterial> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmMaterial, BgmMaterialParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmMaterial getByIdRel(Integer materialId) {
        BgmMaterialParam param = new BgmMaterialParam();
        param.setMaterialId(materialId);
        return param.getOne(baseMapper.selectListRel(param));
    }




}
