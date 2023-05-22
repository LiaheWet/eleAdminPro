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
 * 操作日志参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "操作日志参数")
public class OperationRecordParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty("用户id")
    @QueryField(type = QueryType.EQ)
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

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("状态, 0成功, 1异常")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty("用户账号")
    @TableField(exist = false)
    private String username;

    @ApiModelProperty("用户昵称")
    @TableField(exist = false)
    private String nickname;

}
