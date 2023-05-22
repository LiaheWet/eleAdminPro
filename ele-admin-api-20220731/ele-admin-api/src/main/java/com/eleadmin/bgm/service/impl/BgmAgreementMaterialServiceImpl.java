package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmAgreementMaterialMapper;
import com.eleadmin.bgm.service.BgmAgreementMaterialService;
import com.eleadmin.bgm.entity.BgmAgreementMaterial;
import com.eleadmin.bgm.param.BgmAgreementMaterialParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 协议订购材料具体数据Service实现
 *
 * @author EleAdmin
 * @since 2023-05-16 11:17:57
 */
@Service
public class BgmAgreementMaterialServiceImpl extends ServiceImpl<BgmAgreementMaterialMapper, BgmAgreementMaterial> implements BgmAgreementMaterialService {

    @Autowired
    private BgmAgreementMaterialMapper bgmAgreementMaterialMapper;
    @Override
    public PageResult<BgmAgreementMaterial> pageRel(BgmAgreementMaterialParam param) {
        PageParam<BgmAgreementMaterial, BgmAgreementMaterialParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmAgreementMaterial> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmAgreementMaterial> listRel(BgmAgreementMaterialParam param) {
        List<BgmAgreementMaterial> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmAgreementMaterial, BgmAgreementMaterialParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmAgreementMaterial getByIdRel(Integer amId) {
        BgmAgreementMaterialParam param = new BgmAgreementMaterialParam();
        param.setAmId(amId);
        return param.getOne(baseMapper.selectListRel(param));
    }



    @Override
    public boolean hasRemove(String agreementNumber) {
        BgmAgreementMaterial param = new BgmAgreementMaterial();
        param.setAmNumber(agreementNumber);
        int count = bgmAgreementMaterialMapper.selectCount(new QueryWrapper<>(param));
        if(count>0){
            if(bgmAgreementMaterialMapper.removeAll(agreementNumber)){
                return true;
            }else return false;
        }
        return false;
    }

}
