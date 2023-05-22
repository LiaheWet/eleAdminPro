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
 * 登录日志
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:41
 */
@Data
@ApiModel(description = "登录日志")
@TableName("sys_login_record")
public class LoginRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int TYPE_LOGIN = 0;  // 登录成功
    public static final int TYPE_ERROR = 1;  // 登录失败
    public static final int TYPE_LOGOUT = 2;  // 退出登录
    public static final int TYPE_REFRESH = 3;  // 续签token

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("设备名称")
    private String device;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("操作类型, 0登录成功, 1登录失败, 2退出登录, 3续签token")
    private Integer loginType;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("操作时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("用户id")
    @TableField(exist = false)
    private Integer userId;

    @ApiModelProperty("用户昵称")
    @TableField(exist = false)
    private String nickname;

}
