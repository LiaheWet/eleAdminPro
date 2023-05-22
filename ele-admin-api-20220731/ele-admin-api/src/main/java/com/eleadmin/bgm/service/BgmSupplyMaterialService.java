package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmSupplyMaterial;
import com.eleadmin.bgm.param.BgmSupplyMaterialParam;

import java.util.List;

/**
 * 供应的原材料(关联表;多对多)Service
 *
 * @author EleAdmin
 * @since 2023-05-17 12:15:09
 */
public interface BgmSupplyMaterialService extends IService<BgmSupplyMaterial> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmSupplyMaterial>
     */
    PageResult<BgmSupplyMaterial> pageRel(BgmSupplyMaterialParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmSupplyMaterial>
     */
    List<BgmSupplyMaterial> listRel(BgmSupplyMaterialParam param);

    /**
     * 根据id查询
     *
     * @param supplyMaterialId 供应的原材料id
     * @return BgmSupplyMaterial
     */
    BgmSupplyMaterial getByIdRel(Integer supplyMaterialId);

    PageResult<BgmSupplyMaterialParam> pageList(BgmSupplyMaterialParam param);
}
