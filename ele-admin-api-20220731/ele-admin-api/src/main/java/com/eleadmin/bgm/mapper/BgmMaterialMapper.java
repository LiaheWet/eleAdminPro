package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmMaterial;
import com.eleadmin.bgm.param.BgmMaterialParam;
import com.eleadmin.bgm.param.BgmMaterialTypeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper
 *
 * @author EleAdmin
 * @since 2023-04-15 12:46:34
 */
public interface BgmMaterialMapper extends BaseMapper<BgmMaterial> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmMaterial>
     */
    List<BgmMaterial> selectPageRel(@Param("page") IPage<BgmMaterial> page,
                             @Param("param") BgmMaterialParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmMaterial> selectListRel(@Param("param") BgmMaterialParam param);

}
