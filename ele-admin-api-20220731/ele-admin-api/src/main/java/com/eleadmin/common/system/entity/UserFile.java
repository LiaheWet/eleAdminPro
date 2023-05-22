package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户文件
 *
 * @author EleAdmin
 * @since 2022-07-21 14:34:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserFile对象", description = "用户文件")
@TableName("sys_user_file")
public class UserFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "是否是文件夹, 0否, 1是")
    private Integer isDirectory;

    @ApiModelProperty(value = "上级id")
    private Integer parentId;

    @ApiModelProperty(value = "文件路径")
    private String path;

    @ApiModelProperty(value = "文件大小")
    private Integer length;

    @ApiModelProperty("文件类型")
    private String contentType;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty("文件访问地址")
    @TableField(exist = false)
    private String url;

    @ApiModelProperty("文件缩略图访问地址")
    @TableField(exist = false)
    private String thumbnail;

    @ApiModelProperty("文件下载地址")
    @TableField(exist = false)
    private String downloadUrl;

}
