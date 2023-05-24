package com.eleadmin.bgm.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收料管理表
 *
 * @author EleAdmin
 * @since 2023-05-24 21:13:23
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

    @ApiModelProperty(value = "过毛时间")
    @TableField("fleeceTime")
    private LocalDateTime fleecetime;

    @ApiModelProperty(value = "实收重量")
    @TableField("collectedWeight")
    private BigDecimal collectedweight;

    @ApiModelProperty(value = "入库重量")
    @TableField("warehousingWeight")
    private BigDecimal warehousingweight;

    @ApiModelProperty(value = "实收数量")
    @TableField("collectedNumber")
    private String collectednumber;

    @ApiModelProperty(value = "累计扣杂")
    @TableField("claspWeight")
    private BigDecimal claspweight;

    @ApiModelProperty(value = "储料仓")
    @TableField("storageBin")
    private String storagebin;

    @ApiModelProperty(value = "过皮时间")
    @TableField("peelingTime")
    private LocalDateTime peelingtime;

    @ApiModelProperty(value = "毛重")
    @TableField("grossWeight")
    private BigDecimal grossweight;

    @ApiModelProperty(value = "皮重")
    @TableField("tareWeight")
    private BigDecimal tareweight;

    @ApiModelProperty(value = "净重")
    @TableField("netWeight")
    private BigDecimal netweight;

    @ApiModelProperty(value = "运输车辆")
    @TableField("transportVehicle")
    private String transportvehicle;

    @ApiModelProperty(value = "供货数量")
    @TableField("supplyNumber")
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
    private Integer managementStatus;

}
