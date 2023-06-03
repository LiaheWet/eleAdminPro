package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmVendorAccount;
import com.eleadmin.bgm.param.BgmVendorAccountParam;

import java.util.List;

/**
 * 供应商账户信息Service
 *
 * @author EleAdmin
 * @since 2023-06-03 16:13:47
 */
public interface BgmVendorAccountService extends IService<BgmVendorAccount> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmVendorAccount>
     */
    PageResult<BgmVendorAccount> pageRel(BgmVendorAccountParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmVendorAccount>
     */
    List<BgmVendorAccount> listRel(BgmVendorAccountParam param);

    /**
     * 根据id查询
     *
     * @param id id

     * @return BgmVendorAccount
     */
    BgmVendorAccount getByIdRel(Integer id);

    List<BgmVendorAccount> listByVendorId(Integer vendorId);

}
