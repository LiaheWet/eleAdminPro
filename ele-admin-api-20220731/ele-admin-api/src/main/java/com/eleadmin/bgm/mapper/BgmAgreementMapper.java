package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmAgreement;
import com.eleadmin.bgm.param.BgmAgreementParam;
import com.eleadmin.common.core.web.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购协议Mapper
 *
 * @author EleAdmin
 * @since 2023-05-04 16:55:57
 */
@Mapper
public interface BgmAgreementMapper extends BaseMapper<BgmAgreement> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmAgreement>
     */
    List<BgmAgreement> selectPageRel(@Param("page") IPage<BgmAgreement> page,
                                     @Param("param") BgmAgreementParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmAgreement> selectListRel(@Param("param") BgmAgreementParam param);

    //List<BgmAgreementParam> pageList(PageParam<BgmAgreementParam, BgmAgreementParam> pageParam, BgmAgreementParam param);
    BgmAgreementParam pageList(PageParam<BgmAgreementParam, BgmAgreementParam> pageParam, BgmAgreementParam param);
}
