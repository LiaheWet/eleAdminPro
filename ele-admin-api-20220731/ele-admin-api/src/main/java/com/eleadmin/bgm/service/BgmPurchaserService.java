package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmPurchaser;
import com.eleadmin.bgm.param.BgmPurchaserParam;

import java.util.List;

/**
 * 采购人Service
 *
 * @author EleAdmin
 * @since 2023-05-06 16:40:53
 */
public interface BgmPurchaserService extends IService<BgmPurchaser> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmPurchaser>
     */
    PageResult<BgmPurchaser> pageRel(BgmPurchaserParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmPurchaser>
     */
    List<BgmPurchaser> listRel(BgmPurchaserParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return BgmPurchaser
     */
    BgmPurchaser getByIdRel(Integer id);

}
