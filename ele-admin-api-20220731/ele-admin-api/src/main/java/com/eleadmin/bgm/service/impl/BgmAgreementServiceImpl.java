package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.entity.BgmAgreementMaterial;
import com.eleadmin.bgm.mapper.BgmAgreementMapper;
import com.eleadmin.bgm.mapper.BgmAgreementMaterialMapper;
import com.eleadmin.bgm.service.BgmAgreementService;
import com.eleadmin.bgm.entity.BgmAgreement;
import com.eleadmin.bgm.param.BgmAgreementParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 采购协议Service实现
 *
 * @author EleAdmin
 * @since 2023-05-04 16:55:57
 */
@Service
public class BgmAgreementServiceImpl extends ServiceImpl<BgmAgreementMapper, BgmAgreement> implements BgmAgreementService {

    @Autowired
    private BgmAgreementMapper bgmAgreementMapper;

    @Override
    public PageResult<BgmAgreement> pageRel(BgmAgreementParam param) {
        PageParam<BgmAgreement, BgmAgreementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmAgreement> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmAgreement> listRel(BgmAgreementParam param) {
        List<BgmAgreement> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmAgreement, BgmAgreementParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmAgreement getByIdRel(Integer agreementId) {
        BgmAgreementParam param = new BgmAgreementParam();
        param.setAgreementId(agreementId);
        return param.getOne(baseMapper.selectListRel(param));
    }

/*     @Override
    public PageResult<BgmAgreementParam> pageList(BgmAgreementParam param) {
        PageParam<BgmAgreementParam, BgmAgreementParam> pageParam = new PageParam<>(param);
        List<BgmAgreementParam> list = bgmAgreementMapper.pageList(pageParam, param);
        return new PageResult<>(list, pageParam.getTotal());
    }
   @Override
    public List<BgmAgreementParam> listPro(BgmAgreementParam param) {
        PageParam<BgmAgreementParam, BgmAgreementParam> pageParam = new PageParam<>(param);
        List<BgmAgreementParam> list = bgmAgreementMapper.pageList(pageParam, param);
        return list;
    }*/

    @Override
    public BgmAgreementParam selectPro(BgmAgreementParam param) {
        PageParam<BgmAgreementParam, BgmAgreementParam> pageParam = new PageParam<>(param);
        return (bgmAgreementMapper.pageList(pageParam, param));
    }

    @Override
    public boolean savePro(BgmAgreementParam bgmAgreementParam) {
        BgmAgreement agreement = new BgmAgreement();
        BeanUtils.copyProperties(bgmAgreementParam, agreement);
        if (bgmAgreementMapper.insert(agreement)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePro(BgmAgreementParam bgmAgreementParam) {
        BgmAgreement agreement = new BgmAgreement();
        BeanUtils.copyProperties(bgmAgreementParam, agreement);
        if (bgmAgreementMapper.updateById(agreement)>0){
            return true;
        }
        return false;
    }



}
