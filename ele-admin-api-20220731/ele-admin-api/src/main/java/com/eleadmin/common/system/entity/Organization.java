package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织机构
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:04
 */
@Data
@ApiModel(description = "组织机构")
@TableName("sys_organization")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构id")
    @TableId(type = IdType.AUTO)
    private Integer organizationId;

    @ApiModelProperty(value = "上级id, 0是顶级")
    private Integer parentId;

    @ApiModelProperty(value = "机构名称")
    private String organizationName;

    @ApiModelProperty(value = "机构全称")
    private String organizationFullName;

    @ApiModelProperty(value = "机构代码")
    private String organizationCode;

    @ApiModelProperty(value = "机构类型, 字典标识")
    private String organizationType;

    @ApiModelProperty(value = "负责人id")
    private Integer leaderId;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

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
