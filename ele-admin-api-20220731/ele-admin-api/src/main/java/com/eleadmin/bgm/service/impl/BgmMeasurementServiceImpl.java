package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmMeasurementMapper;
import com.eleadmin.bgm.service.BgmMeasurementService;
import com.eleadmin.bgm.entity.BgmMeasurement;
import com.eleadmin.bgm.param.BgmMeasurementParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 计量单位Service实现
 *
 * @author EleAdmin
 * @since 2023-04-09 14:15:51
 */
@Service
public class BgmMeasurementServiceImpl extends ServiceImpl<BgmMeasurementMapper, BgmMeasurement> implements BgmMeasurementService {

    @Override
    public PageResult<BgmMeasurement> pageRel(BgmMeasurementParam param) {
        PageParam<BgmMeasurement, BgmMeasurementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmMeasurement> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmMeasurement> listRel(BgmMeasurementParam param) {
        List<BgmMeasurement> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmMeasurement, BgmMeasurementParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmMeasurement getByIdRel(Integer measurementId) {
        BgmMeasurementParam param = new BgmMeasurementParam();
        param.setMeasurementId(measurementId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
