package com.eleadmin.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码参数
 *
 * @author EleAdmin
 * @since 2021-08-30 17:35:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "修改密码参数")
public class UpdatePasswordParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("原始密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String password;

}
