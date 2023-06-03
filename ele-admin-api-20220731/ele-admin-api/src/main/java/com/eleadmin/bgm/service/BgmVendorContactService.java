package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmVendorContact;
import com.eleadmin.bgm.param.BgmVendorContactParam;

import java.util.List;

/**
 * 联系人信息Service
 *
 * @author EleAdmin
 * @since 2023-06-03 16:15:33
 */
public interface BgmVendorContactService extends IService<BgmVendorContact> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmVendorContact>
     */
    PageResult<BgmVendorContact> pageRel(BgmVendorContactParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmVendorContact>
     */
    List<BgmVendorContact> listRel(BgmVendorContactParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return BgmVendorContact
     */
    BgmVendorContact getByIdRel(Integer id);

    List<BgmVendorContact> listByVendorId(Integer vendorId);

}
