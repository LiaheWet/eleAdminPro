package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmAgreement;
import com.eleadmin.bgm.param.BgmAgreementParam;

import java.util.List;

/**
 * 采购协议Service
 *
 * @author EleAdmin
 * @since 2023-05-04 16:55:57
 */
public interface BgmAgreementService extends IService<BgmAgreement> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmAgreement>
     */
    PageResult<BgmAgreement> pageRel(BgmAgreementParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmAgreement>
     */
    List<BgmAgreement> listRel(BgmAgreementParam param);

    /**
     * 根据id查询
     *
     * @param agreementId id
     * @return BgmAgreement
     */
    BgmAgreement getByIdRel(Integer agreementId);

    /**
     * 查询所有协议信息
     *
     * @param
     * @return BgmAgreement
     */



    boolean savePro(BgmAgreementParam bgmAgreementParam);

    boolean updatePro(BgmAgreementParam bgmAgreementParam);

    // List<BgmAgreementParam> listPro(BgmAgreementParam param);
    //  PageResult<BgmAgreementParam> pageList(BgmAgreementParam param);
    BgmAgreementParam selectPro(BgmAgreementParam param);
}
