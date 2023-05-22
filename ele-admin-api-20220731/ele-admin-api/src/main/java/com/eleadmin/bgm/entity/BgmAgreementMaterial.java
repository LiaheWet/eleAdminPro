package com.eleadmin.bgm.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 协议订购材料具体数据
 *
 * @author EleAdmin
 * @since 2023-05-16 11:17:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmAgreementMaterial对象", description = "协议订购材料具体数据")
public class BgmAgreementMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "am_id", type = IdType.AUTO)
    private Integer amId;

    @ApiModelProperty(value = "协议编号")
    private String amNumber;

    @ApiModelProperty(value = "制定时间")
    private String createTime;

    @ApiModelProperty(value = "供应原材料")
    private String supplyName;

    @ApiModelProperty(value = "平台原材料")
    private String materialName;

    @ApiModelProperty(value = "计量单位")
    private String measurement;

    @ApiModelProperty(value = "计划数量")
    private Integer number;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalPrice;

}
