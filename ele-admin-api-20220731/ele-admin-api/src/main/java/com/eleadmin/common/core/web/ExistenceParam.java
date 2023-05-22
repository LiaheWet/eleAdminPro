package com.eleadmin.common.core.web;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 检查是否存在通用参数
 *
 * @author EleAdmin
 * @since 2021-09-07 22:24:39
 */
@Data
public class ExistenceParam<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检查的字段")
    private String field;

    @ApiModelProperty(value = "字段的值")
    private String value;

    @ApiModelProperty(value = "修改时的主键")
    private Integer id;

    /**
     * 检查是否存在
     *
     * @param service IService
     * @param idField 修改时的主键字段
     * @return boolean
     */
    public boolean isExistence(IService<T> service, String idField) {
        return isExistence(service, idField, true);
    }

    /**
     * 检查是否存在
     *
     * @param service           IService
     * @param idField           修改时的主键字段
     * @param isToUnderlineCase 是否需要把field转为下划线格式
     * @return boolean
     */
    public boolean isExistence(IService<T> service, String idField, boolean isToUnderlineCase) {
        if (StrUtil.hasBlank(this.field, this.value)) {
            return false;
        }
        String fieldName = isToUnderlineCase ? StrUtil.toUnderlineCase(field) : field;
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(fieldName, value);
        if (id != null) {
            wrapper.ne(idField, id);
        }
        return service.count(wrapper) > 0;
    }

    /**
     * 检查是否存在
     *
     * @param service IService
     * @param idField 修改时的主键字段
     * @return boolean
     */
    public boolean isExistence(IService<T> service, SFunction<T, ?> idField) {
        return isExistence(service, idField, true);
    }

    /**
     * 检查是否存在
     *
     * @param service           IService
     * @param idField           修改时的主键字段
     * @param isToUnderlineCase 是否需要把field转为下划线格式
     * @return boolean
     */
    public boolean isExistence(IService<T> service, SFunction<T, ?> idField, boolean isToUnderlineCase) {
        if (StrUtil.hasBlank(this.field, this.value)) {
            return false;
        }
        String fieldName = isToUnderlineCase ? StrUtil.toUnderlineCase(field) : field;
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply(fieldName + " = {0}", value);
        if (id != null) {
            wrapper.ne(idField, id);
        }
        return service.count(wrapper) > 0;
    }

}
