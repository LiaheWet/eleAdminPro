package com.eleadmin.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.common.system.entity.LoginRecord;
import com.eleadmin.common.system.param.LoginRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录日志Mapper
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:11
 */
public interface LoginRecordMapper extends BaseMapper<LoginRecord> {

    /**
     * 添加, 排除租户拦截
     *
     * @param entity LoginRecord
     * @return int
     */
    @Override
    @InterceptorIgnore(tenantLine = "true")
    int insert(LoginRecord entity);

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<LoginRecord>
     */
    List<LoginRecord> selectPageRel(@Param("page") IPage<LoginRecord> page,
                                    @Param("param") LoginRecordParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<LoginRecord>
     */
    List<LoginRecord> selectListRel(@Param("param") LoginRecordParam param);

}
