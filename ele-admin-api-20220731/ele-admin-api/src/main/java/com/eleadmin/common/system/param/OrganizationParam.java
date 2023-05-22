package com.eleadmin.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织机构查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "组织机构查询参数")
public class OrganizationParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构id")
    @QueryField(type = QueryType.EQ)
    private Integer organizationId;

    @ApiModelProperty(value = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "机构名称")
    private String organizationName;

    @ApiModelProperty(value = "机构全称")
    private String organizationFullName;

    @ApiModelProperty(value = "机构代码")
    private String organizationCode;

    @ApiModelProperty(value = "机构类型(字典代码)")
    private String organizationType;

    @ApiModelProperty(value = "负责人id")
    @QueryField(type = QueryType.EQ)
    private Integer leaderId;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "机构类型名称")
    @TableField(exist = false)
    private String organizationTypeName;

    @ApiModelProperty(value = "负责人姓名")
    @TableField(exist = false)
    private String leaderNickname;

    @ApiModelProperty(value = "负责人账号")
    @TableField(exist = false)
    private String leaderUsername;

}
