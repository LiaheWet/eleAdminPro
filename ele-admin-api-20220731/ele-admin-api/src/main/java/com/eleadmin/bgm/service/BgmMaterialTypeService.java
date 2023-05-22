package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.bgm.entity.BgmMaterialTypeNode;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmMaterialType;
import com.eleadmin.bgm.param.BgmMaterialTypeParam;

import java.util.List;

/**
 * 材料类别Service
 *
 * @author EleAdmin
 * @since 2023-04-12 10:41:31
 */
public interface BgmMaterialTypeService extends IService<BgmMaterialType> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmMaterialType>
     */
    PageResult<BgmMaterialType> pageRel(BgmMaterialTypeParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmMaterialType>
     */
    List<BgmMaterialType> listRel(BgmMaterialTypeParam param);

    /**
     * 根据id查询
     *
     * @param materialTypeId id
     * @return BgmMaterialType
     */
    BgmMaterialType getByIdRel(Integer materialTypeId);

    List<BgmMaterialTypeNode> integrate(List<BgmMaterialType> allBgmMaterialType);


}
