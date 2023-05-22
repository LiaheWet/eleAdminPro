package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:13
 */
@Data
@ApiModel(description = "用户")
@TableName("sys_user")
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("性别, 字典标识")
    private String sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("邮箱是否验证, 0否, 1是")
    private Integer emailVerified;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty("个人简介")
    private String introduction;

    @ApiModelProperty("机构id")
    private Integer organizationId;

    @ApiModelProperty("状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("注册时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String sexName;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<Menu> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != null && this.status == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
