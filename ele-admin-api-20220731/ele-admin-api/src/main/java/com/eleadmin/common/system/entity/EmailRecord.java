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
 * 邮件发送记录
 *
 * @author EleAdmin
 * @since 2021-08-29 12:36:35
 */
@Data
@ApiModel(description = "邮件发送记录")
@TableName("sys_email_record")
public class EmailRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("邮件标题")
    private String title;

    @ApiModelProperty("邮件内容")
    private String content;

    @ApiModelProperty("收件邮箱")
    private String receiver;

    @ApiModelProperty("发件邮箱")
    private String sender;

    @ApiModelProperty("创建人")
    private Integer createUserId;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

}
