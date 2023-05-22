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
 * 登录日志查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 19:09:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "登录日志查询参数")
public class LoginRecordParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("设备名")
    private String device;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("ip地址")
    private String ip;

    @QueryField(type = QueryType.EQ)
    @ApiModelProperty("操作类型, 0登录成功, 1登录失败, 2退出登录, 3续签token")
    private Integer loginType;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("用户id")
    @TableField(exist = false)
    private Integer userId;

    @ApiModelProperty("用户昵称")
    @TableField(exist = false)
    private String nickname;

}
