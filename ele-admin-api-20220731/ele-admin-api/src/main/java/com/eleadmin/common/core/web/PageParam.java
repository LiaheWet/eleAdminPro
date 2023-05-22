package com.eleadmin.common.core.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleadmin.common.core.Constants;
import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.utils.CommonUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页、排序、搜索参数封装
 *
 * @author EleAdmin
 * @since 2019-04-26 10:34:35
 */
public class PageParam<T, U extends BaseParam> extends Page<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 租户id字段名称
     */
    private static final String TENANT_ID_FIELD = "tenantId";

    /**
     * 查询条件
     */
    private final U where;

    /**
     * 是否把字段名称驼峰转下划线
     */
    private final boolean isToUnderlineCase;

    public PageParam() {
        this(null);
    }

    public PageParam(U where) {
        this(where, true);
    }

    public PageParam(U where, boolean isToUnderlineCase) {
        super();
        this.where = where;
        this.isToUnderlineCase = isToUnderlineCase;
        if (where != null) {
            // 获取分页页码
            if (where.getPage() != null) {
                setCurrent(where.getPage());
            }
            // 获取分页每页数量
            if (where.getLimit() != null) {
                setSize(where.getLimit());
            }
            // 获取排序方式
            if (where.getSort() != null) {
                if (sortIsSQL(where.getSort())) {
                    setOrders(parseOrderSQL(where.getSort()));
                } else {
                    List<OrderItem> orderItems = new ArrayList<>();
                    String column = this.isToUnderlineCase ? StrUtil.toUnderlineCase(where.getSort()) : where.getSort();
                    boolean asc = !Constants.ORDER_DESC_VALUE.equals(where.getOrder());
                    orderItems.add(new OrderItem(column, asc));
                    setOrders(orderItems);
                }
            }
        }
    }

    /**
     * 排序字段是否是sql
     */
    private boolean sortIsSQL(String sort) {
        return sort != null && (sort.contains(",") || sort.trim().contains(" "));
    }

    /**
     * 解析排序sql
     */
    private List<OrderItem> parseOrderSQL(String orderSQL) {
        List<OrderItem> orders = new ArrayList<>();
        if (StrUtil.isNotBlank(orderSQL)) {
            for (String item : orderSQL.split(",")) {
                String[] temp = item.trim().split(" ");
                if (!temp[0].isEmpty()) {
                    String column = this.isToUnderlineCase ? StrUtil.toUnderlineCase(temp[0]) : temp[0];
                    boolean asc = temp.length == 1 || !temp[temp.length - 1].equals(Constants.ORDER_DESC_VALUE);
                    orders.add(new OrderItem(column, asc));
                }
            }
        }
        return orders;
    }

    /**
     * 设置默认排序方式
     *
     * @param orderItems 排序方式
     * @return PageParam
     */
    public PageParam<T, U> setDefaultOrder(List<OrderItem> orderItems) {
        if (orders() == null || orders().size() == 0) {
            setOrders(orderItems);
        }
        return this;
    }

    /**
     * 设置默认排序方式
     *
     * @param orderSQL 排序方式
     * @return PageParam
     */
    public PageParam<T, U> setDefaultOrder(String orderSQL) {
        setDefaultOrder(parseOrderSQL(orderSQL));
        return this;
    }

    /**
     * 获取查询条件
     *
     * @param excludes 不包含的字段
     * @return QueryWrapper
     */
    public QueryWrapper<T> getWrapper(String... excludes) {
        return buildWrapper(null, Arrays.asList(excludes));
    }

    /**
     * 获取查询条件
     *
     * @param columns 只包含的字段
     * @return QueryWrapper
     */
    public QueryWrapper<T> getWrapperWith(String... columns) {
        return buildWrapper(Arrays.asList(columns), null);
    }

    /**
     * 构建QueryWrapper
     *
     * @param columns  包含的字段
     * @param excludes 排除的字段
     * @return QueryWrapper
     */
    private QueryWrapper<T> buildWrapper(List<String> columns, List<String> excludes) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(where, false, true);
        for (String fieldName : map.keySet()) {
            Object fieldValue = map.get(fieldName);
            Field field = ReflectUtil.getField(where.getClass(), fieldName);

            // 过滤不包含的字段
            if (columns != null && !columns.contains(fieldName)) {
                continue;
            }

            // 过滤排除的字段
            if (excludes != null && excludes.contains(fieldName)) {
                continue;
            }

            // 过滤逻辑删除字段
            if (field.getAnnotation(TableLogic.class) != null) {
                continue;
            }

            // 过滤租户id字段
            if (fieldName.equals(TENANT_ID_FIELD)) {
                continue;
            }

            // 获取注解指定的查询字段及查询方式
            QueryType queryType = QueryType.LIKE;
            QueryField queryField = field.getAnnotation(QueryField.class);
            if (queryField != null) {
                if (StrUtil.isNotEmpty(queryField.value())) {
                    fieldName = queryField.value();
                }
                if (queryField.type() != null) {
                    queryType = queryField.type();
                }
            } else {
                // 过滤非本表的字段
                TableField tableField = field.getAnnotation(TableField.class);
                if (tableField != null && !tableField.exist()) {
                    continue;
                }
            }

            // 字段名驼峰转下划线
            if (this.isToUnderlineCase) {
                fieldName = StrUtil.toUnderlineCase(fieldName);
            }

            //
            switch (queryType) {
                case EQ:
                    queryWrapper.eq(fieldName, fieldValue);
                    break;
                case NE:
                    queryWrapper.ne(fieldName, fieldValue);
                    break;
                case GT:
                    queryWrapper.gt(fieldName, fieldValue);
                    break;
                case GE:
                    queryWrapper.ge(fieldName, fieldValue);
                    break;
                case LT:
                    queryWrapper.lt(fieldName, fieldValue);
                    break;
                case LE:
                    queryWrapper.le(fieldName, fieldValue);
                    break;
                case LIKE:
                    queryWrapper.like(fieldName, fieldValue);
                    break;
                case NOT_LIKE:
                    queryWrapper.notLike(fieldName, fieldValue);
                    break;
                case LIKE_LEFT:
                    queryWrapper.likeLeft(fieldName, fieldValue);
                    break;
                case LIKE_RIGHT:
                    queryWrapper.likeRight(fieldName, fieldValue);
                    break;
                case IS_NULL:
                    queryWrapper.isNull(fieldName);
                    break;
                case IS_NOT_NULL:
                    queryWrapper.isNotNull(fieldName);
                    break;
                case IN:
                    queryWrapper.in(fieldName, fieldValue);
                    break;
                case NOT_IN:
                    queryWrapper.notIn(fieldName, fieldValue);
                    break;
                case IN_STR:
                    if (fieldValue instanceof String) {
                        queryWrapper.in(fieldName, Arrays.asList(((String) fieldValue).split(",")));
                    }
                    break;
                case NOT_IN_STR:
                    if (fieldValue instanceof String) {
                        queryWrapper.notIn(fieldName, Arrays.asList(((String) fieldValue).split(",")));
                    }
                    break;
            }
        }
        return queryWrapper;
    }

    /**
     * 获取包含排序的查询条件
     *
     * @return 包含排序的QueryWrapper
     */
    public QueryWrapper<T> getOrderWrapper() {
        return getOrderWrapper(getWrapper());
    }

    /**
     * 获取包含排序的查询条件
     *
     * @param queryWrapper 不含排序的QueryWrapper
     * @return 包含排序的QueryWrapper
     */
    public QueryWrapper<T> getOrderWrapper(QueryWrapper<T> queryWrapper) {
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        for (OrderItem orderItem : orders()) {
            if (orderItem.isAsc()) {
                queryWrapper.orderByAsc(orderItem.getColumn());
            } else {
                queryWrapper.orderByDesc(orderItem.getColumn());
            }
        }
        return queryWrapper;
    }

    /**
     * 获取集合中的第一条数据
     *
     * @param records 集合
     * @return 第一条数据
     */
    public T getOne(List<T> records) {
        return CommonUtil.listGetOne(records);
    }

    /**
     * 代码排序集合
     *
     * @param records 集合
     * @return 排序后的集合
     */
    public List<T> sortRecords(List<T> records) {
        List<OrderItem> orderItems = orders();
        if (records == null || records.size() < 2 || orderItems == null || orderItems.size() == 0) {
            return records;
        }
        Comparator<T> comparator = null;
        for (OrderItem item : orderItems) {
            if (item.getColumn() == null) {
                continue;
            }
            String field = this.isToUnderlineCase ? StrUtil.toCamelCase(item.getColumn()) : item.getColumn();
            Function keyExtractor = t -> ReflectUtil.getFieldValue(t, field);
            if (comparator == null) {
                if (item.isAsc()) {
                    comparator = Comparator.comparing(keyExtractor);
                } else {
                    comparator = Comparator.comparing(keyExtractor, Comparator.reverseOrder());
                }
            } else {
                if (item.isAsc()) {
                    comparator.thenComparing(keyExtractor);
                } else {
                    comparator.thenComparing(keyExtractor, Comparator.reverseOrder());
                }
            }
        }
        if (comparator != null) {
            return records.stream().sorted(comparator).collect(Collectors.toList());
        }
        return records;
    }

}
