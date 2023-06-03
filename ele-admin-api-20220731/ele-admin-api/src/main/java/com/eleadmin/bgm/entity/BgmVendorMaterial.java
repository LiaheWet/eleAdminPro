package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商供应材料
 *
 * @author EleAdmin
 * @since 2023-06-03 18:58:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmVendorMaterial对象", description = "供应商供应材料")
public class BgmVendorMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "供应商id")
    private Integer vendorId;

    @ApiModelProperty(value = "供应原材料id")
    private Integer supplyMaterialId;

}
