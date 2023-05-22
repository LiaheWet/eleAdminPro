package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author EleAdmin
 * @since 2023-04-15 12:46:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmMaterial对象", description = "")
public class BgmMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "material_id", type = IdType.AUTO)
    private Integer materialId;

    private Integer materialTypeId;

    private String materialName;

    private String materialType;

    private String materialSpecs;

    private String materialMeasurement;

    private Integer materialStatus;

    private String materialRemark;

}
