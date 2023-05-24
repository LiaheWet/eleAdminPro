package com.eleadmin.bgm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.bgm.entity.BgmReceiveManagement;
import com.eleadmin.bgm.param.BgmReceiveManagementParam;

import java.util.List;

/**
 * 收料管理表Service
 *
 * @author EleAdmin
 * @since 2023-05-24 21:13:24
 */
public interface BgmReceiveManagementService extends IService<BgmReceiveManagement> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<BgmReceiveManagement>
     */
    PageResult<BgmReceiveManagement> pageRel(BgmReceiveManagementParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<BgmReceiveManagement>
     */
    List<BgmReceiveManagement> listRel(BgmReceiveManagementParam param);

    /**
     * 根据id查询
     *
     * @param id id
     * @return BgmReceiveManagement
     */
    BgmReceiveManagement getByIdRel(Integer id);

}
