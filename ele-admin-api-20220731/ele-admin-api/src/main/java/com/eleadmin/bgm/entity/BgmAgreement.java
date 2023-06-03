package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 采购协议
 *
 * @author EleAdmin
 * @since 2023-05-04 16:55:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmAgreement对象", description = "采购协议")
public class BgmAgreement implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "agreement_id", type = IdType.AUTO)
    private Integer agreementId;

    @ApiModelProperty(value = "采购编号")
    private String agreementNumber;

    @ApiModelProperty(value = "供应商id")
    private Integer agreementVendorId;

    @ApiModelProperty(value = "供应商名字")
    private String agreementVendorName;

    @ApiModelProperty(value = "供应方式")
    private String agreementMethod;

    @ApiModelProperty(value = "联系人")
    private String agreementContact;

    @ApiModelProperty(value = "联系方式")
    private String agreementPhone;

    @ApiModelProperty(value = "开始采购时间")
    private String agreementCreateTime;

    @ApiModelProperty(value = "开始采购时间")
    private String agreementBuyTime;

    @ApiModelProperty(value = "采购地址")
    private String agreementAddress;

    @ApiModelProperty(value = "异常处理")
    private String agreementException;

    @ApiModelProperty(value = "违约责任")
    private String agreementBreach;

    @ApiModelProperty(value = "备注")
    private String agreementRemark;

    @ApiModelProperty(value = "采购人")
    private String agreementPurchaser;

    @ApiModelProperty(value = "状态")
    private Integer agreementStatus;

}
