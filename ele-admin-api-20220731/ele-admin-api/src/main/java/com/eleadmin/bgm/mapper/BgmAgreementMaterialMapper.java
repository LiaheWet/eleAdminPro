package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmAgreementMaterial;
import com.eleadmin.bgm.param.BgmAgreementMaterialParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 协议订购材料具体数据Mapper
 *
 * @author EleAdmin
 * @since 2023-05-16 11:17:57
 */
@Mapper
public interface BgmAgreementMaterialMapper extends BaseMapper<BgmAgreementMaterial> {



    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmAgreementMaterial>
     */
    List<BgmAgreementMaterial> selectPageRel(@Param("page") IPage<BgmAgreementMaterial> page,
                                             @Param("param") BgmAgreementMaterialParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmAgreementMaterial> selectListRel(@Param("param") BgmAgreementMaterialParam param);

    /**
     * 查询全部所有编号为agreementNumber的数据
     */
    boolean removeAll(String agreementNumber);

}
