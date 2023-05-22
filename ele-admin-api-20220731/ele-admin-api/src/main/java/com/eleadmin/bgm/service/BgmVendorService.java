package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmVendor;
import com.eleadmin.bgm.param.BgmVendorParam;

import java.util.List;

/**
 * 供应商Service
 *
 * @author EleAdmin
 * @since 2023-04-25 16:31:08
 */
public interface BgmVendorService extends IService<BgmVendor> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmVendor>
     */
    PageResult<BgmVendor> pageRel(BgmVendorParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmVendor>
     */
    List<BgmVendor> listRel(BgmVendorParam param);

    /**
     * 根据id查询
     *
     * @param vendorId 
     * @return BgmVendor
     */
    BgmVendor getByIdRel(Integer vendorId);

    /**
     * 查询所有供应商信息
     *
     * @param
     * @return BgmVendor
     */
    /*PageResult<BgmVendorParam> pageList(BgmVendorParam param);*/
    BgmVendorParam selectPro(BgmVendorParam param);
}
