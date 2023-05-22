package com.eleadmin.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.common.system.entity.OperationRecord;
import com.eleadmin.common.system.param.OperationRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志Mapper
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:03
 */
public interface OperationRecordMapper extends BaseMapper<OperationRecord> {

    /**
     * 添加, 排除租户拦截
     *
     * @param entity OperationRecord
     * @return int
     */
    @Override
    @InterceptorIgnore(tenantLine = "true")
    int insert(OperationRecord entity);

    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return List<OperationRecord>
     */
    List<OperationRecord> selectPageRel(@Param("page") IPage<OperationRecord> page,
                                        @Param("param") OperationRecordParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<OperationRecord>
     */
    List<OperationRecord> selectListRel(@Param("param") OperationRecordParam param);

}
