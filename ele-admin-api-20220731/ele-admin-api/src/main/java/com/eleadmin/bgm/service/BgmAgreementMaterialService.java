package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmAgreementMaterial;
import com.eleadmin.bgm.param.BgmAgreementMaterialParam;

import java.util.List;

/**
 * 协议订购材料具体数据Service
 *
 * @author EleAdmin
 * @since 2023-05-16 11:17:57
 */
public interface BgmAgreementMaterialService extends IService<BgmAgreementMaterial> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmAgreementMaterial>
     */
    PageResult<BgmAgreementMaterial> pageRel(BgmAgreementMaterialParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmAgreementMaterial>
     */
    List<BgmAgreementMaterial> listRel(BgmAgreementMaterialParam param);

    /**
     * 根据id查询
     *
     * @param amId 
     * @return BgmAgreementMaterial
     */
    BgmAgreementMaterial getByIdRel(Integer amId);


    boolean hasRemove(String agreementNumber);
}
