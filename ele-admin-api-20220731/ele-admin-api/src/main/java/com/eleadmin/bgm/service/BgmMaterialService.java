package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmMaterial;
import com.eleadmin.bgm.param.BgmMaterialParam;

import java.util.List;

/**
 * Service
 *
 * @author EleAdmin
 * @since 2023-04-15 12:46:34
 */
public interface BgmMaterialService extends IService<BgmMaterial> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmMaterial>
     */
    PageResult<BgmMaterial> pageRel(BgmMaterialParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmMaterial>
     */
    List<BgmMaterial> listRel(BgmMaterialParam param);

    /**
     * 根据id查询
     *
     * @param materialId 
     * @return BgmMaterial
     */
    BgmMaterial getByIdRel(Integer materialId);



}
