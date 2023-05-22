package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户
 *
 * @author EleAdmin
 * @since 2021-08-28 11:31:06
 */
@Data
@ApiModel(description = "租户")
@TableName("sys_tenant")
public class Tenant implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("租户id")
    @TableId(type = IdType.AUTO)
    private Integer tenantId;

    @ApiModelProperty("租户名称")
    private String tenantName;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

}
