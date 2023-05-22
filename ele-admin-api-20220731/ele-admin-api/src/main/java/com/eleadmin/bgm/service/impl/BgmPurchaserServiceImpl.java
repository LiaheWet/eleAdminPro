package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmPurchaserMapper;
import com.eleadmin.bgm.service.BgmPurchaserService;
import com.eleadmin.bgm.entity.BgmPurchaser;
import com.eleadmin.bgm.param.BgmPurchaserParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购人Service实现
 *
 * @author EleAdmin
 * @since 2023-05-06 16:40:53
 */
@Service
public class BgmPurchaserServiceImpl extends ServiceImpl<BgmPurchaserMapper, BgmPurchaser> implements BgmPurchaserService {

    @Override
    public PageResult<BgmPurchaser> pageRel(BgmPurchaserParam param) {
        PageParam<BgmPurchaser, BgmPurchaserParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmPurchaser> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmPurchaser> listRel(BgmPurchaserParam param) {
        List<BgmPurchaser> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmPurchaser, BgmPurchaserParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmPurchaser getByIdRel(Integer id) {
        BgmPurchaserParam param = new BgmPurchaserParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
