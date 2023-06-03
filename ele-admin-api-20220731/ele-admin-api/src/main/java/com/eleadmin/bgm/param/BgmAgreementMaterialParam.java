package com.eleadmin.bgm.param;

import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 协议订购材料具体数据查询参数
 *
 * @author EleAdmin
 * @since 2023-05-30 17:36:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmAgreementMaterialParam对象", description = "协议订购材料具体数据查询参数")
public class BgmAgreementMaterialParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer amId;

    @ApiModelProperty(value = "协议编号")
    private String amNumber;

    @ApiModelProperty(value = "供应材料id")
    @QueryField(type = QueryType.EQ)
    private Integer smId;

    @ApiModelProperty(value = "供应原材料")
    private String supplyName;

    @ApiModelProperty(value = "平台原材料")
    private String materialName;

    @ApiModelProperty(value = "规格")
    private String specs;

    @ApiModelProperty(value = "计量单位")
    private String measurement;

    @ApiModelProperty(value = "计划数量")
    @QueryField(type = QueryType.EQ)
    private Integer number;

    @ApiModelProperty(value = "单价")
    @QueryField(type = QueryType.EQ)
    private BigDecimal price;

    @ApiModelProperty(value = "总金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal totalPrice;

}
