package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商账户信息

 *
 * @author EleAdmin
 * @since 2023-06-03 15:43:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmVendorAccount对象", description = "供应商账户信息
")
public class BgmVendorAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id
")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer vendorId;

    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "开户银行")
    private String accountBank;

    @ApiModelProperty(value = "银行账号")
    private String accountNumber;

}
