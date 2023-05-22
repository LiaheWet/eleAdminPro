package com.eleadmin.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.common.system.entity.FileRecord;
import com.eleadmin.common.system.param.FileRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件上传记录Mapper
 *
 * @author EleAdmin
 * @since 2021-08-30 11:18:04
 */
public interface FileRecordMapper extends BaseMapper<FileRecord> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<FileRecord>
     */
    List<FileRecord> selectPageRel(@Param("page") IPage<FileRecord> page,
                                   @Param("param") FileRecordParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<FileRecord>
     */
    List<FileRecord> selectListRel(@Param("param") FileRecordParam param);

    /**
     * 根据path查询
     *
     * @param path 文件路径
     * @return FileRecord
     */
    @InterceptorIgnore(tenantLine = "true")
    List<FileRecord> getByIdPath(@Param("path") String path);

}
