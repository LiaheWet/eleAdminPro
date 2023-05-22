package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 采购人
 *
 * @author EleAdmin
 * @since 2023-05-06 16:40:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmPurchaser对象", description = "采购人")
public class BgmPurchaser implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "采购人名字")
    private String purchaserName;

}
