package com.eleadmin.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.common.system.entity.Organization;
import com.eleadmin.common.system.param.OrganizationParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织机构Mapper
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:04
 */
public interface OrganizationMapper extends BaseMapper<Organization> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<Organization>
     */
    List<Organization> selectPageRel(@Param("page") IPage<Organization> page,
                                     @Param("param") OrganizationParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<Organization>
     */
    List<Organization> selectListRel(@Param("param") OrganizationParam param);

}
