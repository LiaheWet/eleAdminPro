package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmMaterialType;
import com.eleadmin.bgm.param.BgmMaterialTypeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 材料类别Mapper
 *
 * @author EleAdmin
 * @since 2023-04-12 10:41:31
 */
public interface BgmMaterialTypeMapper extends BaseMapper<BgmMaterialType> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmMaterialType>
     */
    List<BgmMaterialType> selectPageRel(@Param("page") IPage<BgmMaterialType> page,
                             @Param("param") BgmMaterialTypeParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmMaterialType> selectListRel(@Param("param") BgmMaterialTypeParam param);


     List<BgmMaterialType> getAllNodes();
}
