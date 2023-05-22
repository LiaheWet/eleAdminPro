package com.eleadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.entity.OperationRecord;
import com.eleadmin.common.system.param.OperationRecordParam;

import java.util.List;

/**
 * 操作日志Service
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:01
 */
public interface OperationRecordService extends IService<OperationRecord> {

    /**
     * 关联分页查询
     *
     * @param param 查询参数
     * @return PageResult<OperationRecord>
     */
    PageResult<OperationRecord> pageRel(OperationRecordParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<OperationRecord>
     */
    List<OperationRecord> listRel(OperationRecordParam param);

    /**
     * 根据id查询
     *
     * @param id id
     * @return OperationRecord
     */
    OperationRecord getByIdRel(Integer id);

    /**
     * 异步添加
     *
     * @param operationRecord OperationRecord
     */
    void saveAsync(OperationRecord operationRecord);

}
