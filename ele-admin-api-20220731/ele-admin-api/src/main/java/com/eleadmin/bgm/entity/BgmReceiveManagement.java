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
 * 收料管理表
 *
 * @author EleAdmin
 * @since 2023-05-26 09:37:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmReceiveManagement对象", description = "收料管理表")
public class BgmReceiveManagement implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "收料管理编号")
    private String managementNumber;

    @ApiModelProperty(value = "采购协议编号")
    private String agreementNumber;

    @ApiModelProperty(value = "供应商iD")
    private Integer vendorId;

    @ApiModelProperty(value = "供应材料id")
    private Integer smId;

    @ApiModelProperty(value = "收料方式")
    private String receiveMethod;

    @ApiModelProperty(value = "过毛时间")
    private String fleeceTime;

    @ApiModelProperty(value = "实收重量")
    private BigDecimal collectedWeight;

    @ApiModelProperty(value = "入库重量")
    private BigDecimal warehousingWeight;

    @ApiModelProperty(value = "实收数量")
    private String collectedNumber;

    @ApiModelProperty(value = "累计扣杂")
    private BigDecimal claspWeight;

    @ApiModelProperty(value = "储料仓")
    private String storageBin;

    @ApiModelProperty(value = "过皮时间")
    private String peelingTime;

    @ApiModelProperty(value = "毛重")
    private BigDecimal grossWeight;

    @ApiModelProperty(value = "皮重")
    private BigDecimal tareWeight;

    @ApiModelProperty(value = "净重")
    private BigDecimal netWeight;

    @ApiModelProperty(value = "明扣重量")
    private BigDecimal buckleWeight;

    @ApiModelProperty(value = "运输车辆")
    private String transportVehicle;

    @ApiModelProperty(value = "司机名字")
    private String driverName;

    @ApiModelProperty(value = "供货数量")
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
    private Integer managementStatus;

}
