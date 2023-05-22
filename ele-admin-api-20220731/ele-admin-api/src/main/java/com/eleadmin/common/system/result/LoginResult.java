package com.eleadmin.common.system.result;

import com.eleadmin.common.system.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录返回结果
 *
 * @author EleAdmin
 * @since 2021-08-30 17:35:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录返回结果")
public class LoginResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("access_token")
    private String access_token;

    @ApiModelProperty("用户信息")
    private User user;

}
