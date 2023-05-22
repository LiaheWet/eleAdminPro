package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应的原材料(关联表;多对多)
 *
 * @author EleAdmin
 * @since 2023-05-17 12:15:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmSupplyMaterial对象", description = "供应的原材料(关联表;多对多)")
public class BgmSupplyMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "供应的原材料id")
    @TableId(value = "supply_material_id", type = IdType.AUTO)
    private Integer supplyMaterialId;

    @ApiModelProperty(value = "供应的原材料类别id")
    private Integer smTypeId;

    @ApiModelProperty(value = "供应的原材料类别名字")
    private String smTypeName;

    @ApiModelProperty(value = "供应的原材料规格")
    private String smSpecs;

    @ApiModelProperty(value = "供应的原材料名称")
    private String smName;

    @ApiModelProperty(value = "供应的原材料备注")
    private String smRemark;

    @ApiModelProperty(value = "状态")
    private Integer smStatus;

}
