package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:33
 */
@Data
@ApiModel(description = "操作日志")
@TableName("sys_operation_record")
public class OperationRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("操作模块")
    private String module;

    @ApiModelProperty("操作功能")
    private String description;

    @ApiModelProperty("请求地址")
    private String url;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("调用方法")
    private String method;

    @ApiModelProperty("请求参数")
    private String params;

    @ApiModelProperty("返回结果")
    private String result;

    @ApiModelProperty("异常信息")
    private String error;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("消耗时间, 单位毫秒")
    private Long spendTime;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("设备名称")
    private String device;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("状态, 0成功, 1异常")
    private Integer status;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("操作时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("用户昵称")
    @TableField(exist = false)
    private String nickname;

    @ApiModelProperty("用户账号")
    @TableField(exist = false)
    private String username;

}
