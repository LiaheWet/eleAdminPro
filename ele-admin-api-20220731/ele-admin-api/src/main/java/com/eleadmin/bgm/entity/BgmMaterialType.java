package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 材料类别
 *
 * @author EleAdmin
 * @since 2023-04-12 10:41:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmMaterialType对象", description = "材料类别")
public class BgmMaterialType implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "material_type_id", type = IdType.AUTO)
    private Integer materialTypeId;

    @ApiModelProperty(value = "材料类别名称 ")
    private String materialTypeName;

    @ApiModelProperty(value = "父子id")
    private Integer materialTypePid;

}
