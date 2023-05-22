package com.eleadmin.common.core.annotation;

/**
 * 查询方式
 *
 * @author EleAdmin
 * @since 2021-09-01 20:48:16
 */
public enum QueryType {
    // 等于
    EQ,
    // 不等于
    NE,
    // 大于
    GT,
    // 大于等于
    GE,
    // 小于
    LT,
    // 小于等于
    LE,
    // 包含
    LIKE,
    // 不包含
    NOT_LIKE,
    // 结尾等于
    LIKE_LEFT,
    // 开头等于
    LIKE_RIGHT,
    // 为NULL
    IS_NULL,
    // 不为空
    IS_NOT_NULL,
    // IN
    IN,
    // NOT IN
    NOT_IN,
    // IN条件解析逗号分割
    IN_STR,
    // NOT IN条件解析逗号分割
    NOT_IN_STR
}
