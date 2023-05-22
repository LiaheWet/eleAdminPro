package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmVendor;
import com.eleadmin.bgm.param.BgmVendorParam;
import com.eleadmin.common.core.web.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商Mapper
 *
 * @author EleAdmin
 * @since 2023-04-25 16:31:08
 */
@Mapper
public interface BgmVendorMapper extends BaseMapper<BgmVendor> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmVendor>
     */
    List<BgmVendor> selectPageRel(@Param("page") IPage<BgmVendor> page,
                             @Param("param") BgmVendorParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmVendor> selectListRel(@Param("param") BgmVendorParam param);

    /**
     * 查询供应商全部信息
     *
     * @param param 查询参数
     * @return pageList<User>
     */
    BgmVendorParam pageList(PageParam<BgmVendorParam,BgmVendorParam>pageParam,BgmVendorParam param);
}
