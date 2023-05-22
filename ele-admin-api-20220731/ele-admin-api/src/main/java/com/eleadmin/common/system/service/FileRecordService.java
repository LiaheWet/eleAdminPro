package com.eleadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.entity.FileRecord;
import com.eleadmin.common.system.param.FileRecordParam;

import java.io.File;
import java.util.List;

/**
 * 文件上传记录Service
 *
 * @author EleAdmin
 * @since 2021-08-30 11:20:15
 */
public interface FileRecordService extends IService<FileRecord> {

    /**
     * 关联分页查询
     *
     * @param param 查询参数
     * @return PageResult<FileRecord>
     */
    PageResult<FileRecord> pageRel(FileRecordParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<FileRecord>
     */
    List<FileRecord> listRel(FileRecordParam param);

    /**
     * 根据id查询
     *
     * @param id id
     * @return FileRecord
     */
    FileRecord getByIdRel(Integer id);

    /**
     * 根据path查询
     *
     * @param path 文件路径
     * @return FileRecord
     */
    FileRecord getByIdPath(String path);

    /**
     * 异步删除文件
     *
     * @param files 文件数组
     */
    void deleteFileAsync(List<File> files);

}
