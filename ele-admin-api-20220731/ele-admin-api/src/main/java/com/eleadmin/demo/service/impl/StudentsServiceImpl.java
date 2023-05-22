package com.eleadmin.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.demo.mapper.StudentsMapper;
import com.eleadmin.demo.service.StudentsService;
import com.eleadmin.demo.entity.Students;
import com.eleadmin.demo.param.StudentsParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service实现
 *
 * @author EleAdmin
 * @since 2023-03-06 22:00:02
 */
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements StudentsService {

    @Override
    public PageResult<Students> pageRel(StudentsParam param) {
        PageParam<Students, StudentsParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<Students> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<Students> listRel(StudentsParam param) {
        List<Students> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<Students, StudentsParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public Students getByIdRel(Integer studentId) {
        StudentsParam param = new StudentsParam();
        param.setStudentId(studentId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
