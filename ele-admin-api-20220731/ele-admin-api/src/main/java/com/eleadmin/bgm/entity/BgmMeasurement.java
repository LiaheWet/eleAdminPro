package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 计量单位
 *
 * @author EleAdmin
 * @since 2023-04-09 14:15:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmMeasurement对象", description = "计量单位")
public class BgmMeasurement implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "measurement_id", type = IdType.AUTO)
    private Integer measurementId;

    private String measurementName;

    private String measurementSymbol;

    private Integer measurementStatus;

}
