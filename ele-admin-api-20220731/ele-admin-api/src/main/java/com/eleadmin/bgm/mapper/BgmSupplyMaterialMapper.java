package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmSupplyMaterial;
import com.eleadmin.bgm.param.BgmSupplyMaterialParam;
import com.eleadmin.common.core.web.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应的原材料(关联表;多对多)Mapper
 *
 * @author EleAdmin
 * @since 2023-05-17 12:15:09
 */
@Mapper
public interface BgmSupplyMaterialMapper extends BaseMapper<BgmSupplyMaterial> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmSupplyMaterial>
     */
    List<BgmSupplyMaterial> selectPageRel(@Param("page") IPage<BgmSupplyMaterial> page,
                             @Param("param") BgmSupplyMaterialParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmSupplyMaterial> selectListRel(@Param("param") BgmSupplyMaterialParam param);

    List<BgmSupplyMaterialParam> pageList(PageParam<BgmSupplyMaterialParam, BgmSupplyMaterialParam> page, BgmSupplyMaterialParam param);
}
