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
 * @DATE:2023/5/10 17:02
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmVendorContact对象", description = "")
public class BgmVendorContact implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "供应商id")
    private Integer vendorId;

    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    @ApiModelProperty(value = "联系人职务")
    private String contactPost;

    @ApiModelProperty(value = "供应商电花")
    private String contactPhone;
}
