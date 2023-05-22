package com.eleadmin.common.system.param;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户导入参数
 *
 * @author EleAdmin
 * @since 2011-10-15 17:33:34
 */
@Data
public class UserImportParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "账号")
    private String username;

    @Excel(name = "密码")
    private String password;

    @Excel(name = "昵称")
    private String nickname;

    @Excel(name = "手机号")
    private String phone;

    @Excel(name = "邮箱")
    private String email;

    @Excel(name = "组织机构")
    private String organizationName;

    @Excel(name = "性别")
    private String sexName;

    @Excel(name = "角色")
    private String roleName;

}
