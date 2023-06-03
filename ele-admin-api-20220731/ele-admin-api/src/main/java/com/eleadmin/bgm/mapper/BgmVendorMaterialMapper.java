package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmVendorMaterial;
import com.eleadmin.bgm.param.BgmVendorMaterialParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商供应材料Mapper
 *
 * @author EleAdmin
 * @since 2023-06-03 18:58:27
 */
public interface BgmVendorMaterialMapper extends BaseMapper<BgmVendorMaterial> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmVendorMaterial>
     */
    List<BgmVendorMaterial> selectPageRel(@Param("page") IPage<BgmVendorMaterial> page,
                             @Param("param") BgmVendorMaterialParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmVendorMaterialParam> selectListRel(@Param("param") BgmVendorMaterialParam param);

}
