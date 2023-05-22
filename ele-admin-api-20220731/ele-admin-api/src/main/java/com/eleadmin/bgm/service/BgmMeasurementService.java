package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmMeasurement;
import com.eleadmin.bgm.param.BgmMeasurementParam;

import java.util.List;

/**
 * 计量单位Service
 *
 * @author EleAdmin
 * @since 2023-04-09 14:15:51
 */
public interface BgmMeasurementService extends IService<BgmMeasurement> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmMeasurement>
     */
    PageResult<BgmMeasurement> pageRel(BgmMeasurementParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmMeasurement>
     */
    List<BgmMeasurement> listRel(BgmMeasurementParam param);

    /**
     * 根据id查询
     *
     * @param measurementId 
     * @return BgmMeasurement
     */
    BgmMeasurement getByIdRel(Integer measurementId);

}
