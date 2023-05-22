package com.eleadmin.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "用户查询参数")
public class UserParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别(字典)")
    @QueryField(type = QueryType.EQ)
    private String sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("邮箱是否验证, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer emailVerified;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("机构id")
    @QueryField(type = QueryType.EQ)
    private Integer organizationId;

    @ApiModelProperty("状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("角色id")
    @TableField(exist = false)
    private Integer roleId;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String sexName;

    @ApiModelProperty("搜索关键字")
    @TableField(exist = false)
    private String keywords;

}
