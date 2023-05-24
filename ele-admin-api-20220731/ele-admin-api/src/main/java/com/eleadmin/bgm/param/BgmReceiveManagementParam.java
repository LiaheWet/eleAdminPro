package com.eleadmin.bgm.param;

import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收料管理表查询参数
 *
 * @author EleAdmin
 * @since 2023-05-24 21:13:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmReceiveManagementParam对象", description = "收料管理表查询参数")
public class BgmReceiveManagementParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "收料管理编号")
    private String managementNumber;

    @ApiModelProperty(value = "采购协议编号")
    private String agreementNumber;

    @ApiModelProperty(value = "供应商iD")
    @QueryField(type = QueryType.EQ)
    private Integer vendorId;

    @ApiModelProperty(value = "供应材料id")
    @QueryField(type = QueryType.EQ)
    private Integer smId;

    @ApiModelProperty(value = "过毛时间")
    private String fleecetime;

    @ApiModelProperty(value = "实收重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal collectedweight;

    @ApiModelProperty(value = "入库重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal warehousingweight;

    @ApiModelProperty(value = "实收数量")
    private String collectednumber;

    @ApiModelProperty(value = "累计扣杂")
    @QueryField(type = QueryType.EQ)
    private BigDecimal claspweight;

    @ApiModelProperty(value = "储料仓")
    private String storagebin;

    @ApiModelProperty(value = "过皮时间")
    private String peelingtime;

    @ApiModelProperty(value = "毛重")
    @QueryField(type = QueryType.EQ)
    private BigDecimal grossweight;

    @ApiModelProperty(value = "皮重")
    @QueryField(type = QueryType.EQ)
    private BigDecimal tareweight;

    @ApiModelProperty(value = "净重")
    @QueryField(type = QueryType.EQ)
    private BigDecimal netweight;

    @ApiModelProperty(value = "运输车辆")
    private String transportvehicle;

    @ApiModelProperty(value = "供货数量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal supplynumber;

    @ApiModelProperty(value = "过毛人员")
    private String overHairedPersonnel;

    @ApiModelProperty(value = "过皮人员")
    private String overSkinnedPersonnel;

    @ApiModelProperty(value = "作废原因")
    private String cancellation;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer managementStatus;

}
