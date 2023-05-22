package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: heng
 * @DATE:2023/5/10 17:06
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmVendorAccount对象", description = "")
public class BgmVendorAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "供应商id")
    private Integer vendorId;

    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "开户银行")
    private String accountBank;

    @ApiModelProperty(value = "银行账号")
    private String accountNumber;

}
