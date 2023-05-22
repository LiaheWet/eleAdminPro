package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传记录
 *
 * @author EleAdmin
 * @since 2021-08-29 12:36:32
 */
@Data
@ApiModel(description = "文件上传记录")
@TableName("sys_file_record")
public class FileRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件存储路径")
    private String path;

    @ApiModelProperty("文件大小")
    private Long length;

    @ApiModelProperty("文件类型")
    private String contentType;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("创建人")
    private Integer createUserId;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
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

    @ApiModelProperty("创建人账号")
    @TableField(exist = false)
    private String createUsername;

    @ApiModelProperty("创建人名称")
    @TableField(exist = false)
    private String createNickname;

}
