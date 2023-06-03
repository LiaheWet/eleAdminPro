package com.eleadmin.bgm.param;

import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联系人信息查询参数
 *
 * @author EleAdmin
 * @since 2023-06-03 16:15:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmVendorContactParam对象", description = "联系人信息查询参数")
public class BgmVendorContactParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @QueryField(type = QueryType.EQ)
    private Integer vendorId;

    @ApiModelProperty(value = "联系人名字")
    private String contactName;

    @ApiModelProperty(value = "联系人职务")
    private String contactPost;

    @ApiModelProperty(value = "联系人电画")
    private String contactPhone;

}
