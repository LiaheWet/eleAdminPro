package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmVendorMaterial;
import com.eleadmin.bgm.param.BgmVendorMaterialParam;

import java.util.List;

/**
 * 供应商供应材料Service
 *
 * @author EleAdmin
 * @since 2023-06-03 18:58:27
 */
public interface BgmVendorMaterialService extends IService<BgmVendorMaterial> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmVendorMaterial>
     */
    PageResult<BgmVendorMaterial> pageRel(BgmVendorMaterialParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmVendorMaterial>
     */
    List<BgmVendorMaterialParam> listRel(BgmVendorMaterialParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return BgmVendorMaterial
     */
    BgmVendorMaterialParam getByIdRel(Integer id);

}
