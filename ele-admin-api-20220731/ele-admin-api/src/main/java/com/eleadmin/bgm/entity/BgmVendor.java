package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商
 *
 * @author EleAdmin
 * @since 2023-04-25 16:31:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmVendor对象", description = "供应商")
public class BgmVendor implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "vendor_id", type = IdType.AUTO)
    private Integer vendorId;

    @ApiModelProperty(value = "供应商名称")
    private String vendorName;

    @ApiModelProperty(value = "供应商单位类别")
    private String vendorType;

    @ApiModelProperty(value = "供应商法人代表")
    private String vendorRepresent;

    @ApiModelProperty(value = "供应商电花")
    private String vendorPhone;

    @ApiModelProperty(value = "供应商传真")
    private String vendorFax;

    @ApiModelProperty(value = "供应商邮箱")
    private String vendorEmail;

    @ApiModelProperty(value = "供应商地址")
    private String vendorAddress;

    @ApiModelProperty(value = "供应商邮编")
    private String vendorPostal;

    @ApiModelProperty(value = "状态")
    private Integer vendorStatus;

    @ApiModelProperty(value = "采购员")
    private String buyer;

    @ApiModelProperty(value = "纳税号")
    private String taxId;

    @ApiModelProperty(value = "信誉度")
    private Integer credit;

    @ApiModelProperty(value = "不合格")
    private Integer unqualified;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "关注")
    private Integer focus;

}
