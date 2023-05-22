package com.eleadmin.common.core.annotation;

import java.lang.annotation.*;

/**
 * 查询条件注解
 *
 * @author EleAdmin
 * @since 2021-09-01 20:48:16
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface QueryField {

    // 字段名称
    String value() default "";

    // 查询方式
    QueryType type() default QueryType.LIKE;

}
