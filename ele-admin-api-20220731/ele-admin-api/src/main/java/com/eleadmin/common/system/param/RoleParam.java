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
 * 角色查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "角色查询参数")
public class RoleParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    @QueryField(type = QueryType.EQ)
    private Integer roleId;

    @ApiModelProperty("角色标识")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("备注")
    private String comments;

}
