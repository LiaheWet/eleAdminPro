package com.eleadmin.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.demo.entity.Students;
import com.eleadmin.demo.param.StudentsParam;

import java.util.List;

/**
 * Service
 *
 * @author EleAdmin
 * @since 2023-03-06 22:00:02
 */
public interface StudentsService extends IService<Students> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<Students>
     */
    PageResult<Students> pageRel(StudentsParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<Students>
     */
    List<Students> listRel(StudentsParam param);

    /**
     * 根据id查询
     *
     * @param studentId 
     * @return Students
     */
    Students getByIdRel(Integer studentId);

}
