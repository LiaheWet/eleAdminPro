package com.eleadmin.common.system.param;

import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户文件查询参数
 *
 * @author EleAdmin
 * @since 2022-07-21 14:34:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "UserFileParam对象", description = "用户文件查询参数")
public class UserFileParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "上级id")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

}
