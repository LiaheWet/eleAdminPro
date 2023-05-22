package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色菜单
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:54
 */
@Data
@ApiModel(description = "角色权限")
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

}
