package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联系人信息
 *
 * @author EleAdmin
 * @since 2023-06-03 16:15:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmVendorContact对象", description = "联系人信息")
public class BgmVendorContact implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer vendorId;

    @ApiModelProperty(value = "联系人名字")
    private String contactName;

    @ApiModelProperty(value = "联系人职务")
    private String contactPost;

    @ApiModelProperty(value = "联系人电画")
    private String contactPhone;

}
