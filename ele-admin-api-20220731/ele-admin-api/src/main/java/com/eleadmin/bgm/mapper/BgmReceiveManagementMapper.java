package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmReceiveManagement;
import com.eleadmin.bgm.param.BgmReceiveManagementParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收料管理表Mapper
 *
 * @author EleAdmin
 * @since 2023-05-24 21:13:24
 */
public interface BgmReceiveManagementMapper extends BaseMapper<BgmReceiveManagement> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmReceiveManagement>
     */
    List<BgmReceiveManagement> selectPageRel(@Param("page") IPage<BgmReceiveManagement> page,
                             @Param("param") BgmReceiveManagementParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmReceiveManagement> selectListRel(@Param("param") BgmReceiveManagementParam param);

}
