package com.eleadmin.bgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.bgm.entity.BgmVendorAccount;
import com.eleadmin.bgm.param.BgmVendorAccountParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商账户信息Mapper
 *
 * @author EleAdmin
 * @since 2023-06-03 16:13:47
 */
public interface BgmVendorAccountMapper extends BaseMapper<BgmVendorAccount> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<BgmVendorAccount>
     */
    List<BgmVendorAccount> selectPageRel(@Param("page") IPage<BgmVendorAccount> page,
                             @Param("param") BgmVendorAccountParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<BgmVendorAccount> selectListRel(@Param("param") BgmVendorAccountParam param);

}
