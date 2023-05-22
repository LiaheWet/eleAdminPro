package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmMeasurement;
import com.eleadmin.bgm.param.BgmMeasurementParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 计量单位Mapper
 *
 * @author EleAdmin
 * @since 2023-04-09 14:15:51
 */
@Mapper
public interface BgmMeasurementMapper extends BaseMapper<BgmMeasurement> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmMeasurement>
     */
    List<BgmMeasurement> selectPageRel(@Param("page") IPage<BgmMeasurement> page,
                             @Param("param") BgmMeasurementParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmMeasurement> selectListRel(@Param("param") BgmMeasurementParam param);

}
