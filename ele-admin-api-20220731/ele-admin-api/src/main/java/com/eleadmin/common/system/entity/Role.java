package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:01
 */
@Data
@ApiModel(description = "角色")
@TableName("sys_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty("角色标识")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private Integer userId;

}
