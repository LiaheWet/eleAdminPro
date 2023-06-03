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
import java.util.Date;
import java.util.List;

/**
 * 收料管理表查询参数
 *
 * @author EleAdmin
 * @since 2023-05-26 09:37:03
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

    @ApiModelProperty(value = "收料方式")
    private String receiveMethod;

    @ApiModelProperty(value = "过毛时间")
    private String fleeceTime;

    @ApiModelProperty(value = "实收重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal collectedWeight;

    @ApiModelProperty(value = "入库重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal warehousingWeight;

    @ApiModelProperty(value = "实收数量")
    private String collectedNumber;

    @ApiModelProperty(value = "累计扣杂")
    @QueryField(type = QueryType.EQ)
    private BigDecimal claspWeight;

    @ApiModelProperty(value = "储料仓")
    private String storageBin;

    @ApiModelProperty(value = "过皮时间")
    private String peelingTime;

    @ApiModelProperty(value = "毛重")
    @QueryField(type = QueryType.EQ)
    private BigDecimal grossWeight;

    @ApiModelProperty(value = "皮重")
    @QueryField(type = QueryType.EQ)
    private BigDecimal tareWeight;

    @ApiModelProperty(value = "净重")
    @QueryField(type = QueryType.EQ)
    private BigDecimal netWeight;

    @ApiModelProperty(value = "明扣重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal buckleWeight;

    @ApiModelProperty(value = "运输车辆")
    private String transportVehicle;

    @ApiModelProperty(value = "司机名字")
    private String driverName;

    @ApiModelProperty(value = "供货数量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal supplyNumber;

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

    @ApiModelProperty(value = "供应原材料")
    private String smName;

    @ApiModelProperty(value = "平台原材料")
    private String smTypeName;

    @ApiModelProperty(value = "规格")
    private String smSpecs;

    @ApiModelProperty(value = "供应商")
    private String vendorName;

    @QueryField(type = QueryType.GE)
    private Date time1;

    @QueryField(type = QueryType.LE)
    private Date time2;

    @ApiModelProperty(value = "状态数组")
    private List<Integer> managementStatusList;


}
