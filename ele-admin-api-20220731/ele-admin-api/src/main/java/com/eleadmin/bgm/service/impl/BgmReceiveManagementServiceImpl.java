package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmReceiveManagementMapper;
import com.eleadmin.bgm.service.BgmReceiveManagementService;
import com.eleadmin.bgm.entity.BgmReceiveManagement;
import com.eleadmin.bgm.param.BgmReceiveManagementParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收料管理表Service实现
 *
 * @author EleAdmin
 * @since 2023-05-24 21:13:24
 */
@Service
public class BgmReceiveManagementServiceImpl extends ServiceImpl<BgmReceiveManagementMapper, BgmReceiveManagement> implements BgmReceiveManagementService {

    @Override
    public PageResult<BgmReceiveManagement> pageRel(BgmReceiveManagementParam param) {
        PageParam<BgmReceiveManagement, BgmReceiveManagementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmReceiveManagement> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmReceiveManagement> listRel(BgmReceiveManagementParam param) {
        List<BgmReceiveManagement> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmReceiveManagement, BgmReceiveManagementParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmReceiveManagement getByIdRel(Integer id) {
        BgmReceiveManagementParam param = new BgmReceiveManagementParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
