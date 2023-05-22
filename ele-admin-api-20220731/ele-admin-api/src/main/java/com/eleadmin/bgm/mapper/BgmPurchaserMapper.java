package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmPurchaser;
import com.eleadmin.bgm.param.BgmPurchaserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购人Mapper
 *
 * @author EleAdmin
 * @since 2023-05-06 16:40:53
 */
public interface BgmPurchaserMapper extends BaseMapper<BgmPurchaser> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmPurchaser>
     */
    List<BgmPurchaser> selectPageRel(@Param("page") IPage<BgmPurchaser> page,
                             @Param("param") BgmPurchaserParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmPurchaser> selectListRel(@Param("param") BgmPurchaserParam param);

}
