package com.eleadmin.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eleadmin.demo.entity.Students;
import com.eleadmin.demo.param.StudentsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper
 *
 * @author EleAdmin
 * @since 2023-03-06 22:00:02
 */
public interface StudentsMapper extends BaseMapper<Students> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<Students>
     */
    List<Students> selectPageRel(@Param("page") IPage<Students> page,
                             @Param("param") StudentsParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<Students> selectListRel(@Param("param") StudentsParam param);

}
