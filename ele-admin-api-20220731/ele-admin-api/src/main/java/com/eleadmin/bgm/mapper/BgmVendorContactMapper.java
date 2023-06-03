package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmVendorContact;
import com.eleadmin.bgm.param.BgmVendorContactParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 联系人信息Mapper
 *
 * @author EleAdmin
 * @since 2023-06-03 16:15:33
 */
public interface BgmVendorContactMapper extends BaseMapper<BgmVendorContact> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmVendorContact>
     */
    List<BgmVendorContact> selectPageRel(@Param("page") IPage<BgmVendorContact> page,
                             @Param("param") BgmVendorContactParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmVendorContact> selectListRel(@Param("param") BgmVendorContactParam param);

}
