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
 * 供应商供应材料查询参数
 *
 * @author EleAdmin
 * @since 2023-06-03 18:58:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmVendorMaterialParam对象", description = "供应商供应材料查询参数")
public class BgmVendorMaterialParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "供应商id")
    @QueryField(type = QueryType.EQ)
    private Integer vendorId;

    @ApiModelProperty(value = "供应原材料id")
    @QueryField(type = QueryType.EQ)
    private Integer supplyMaterialId;

    @ApiModelProperty(value = "供应的原材料类别id")
    @QueryField(type = QueryType.EQ)
    private Integer smTypeId;

    @ApiModelProperty(value = "供应的原材料类别名字")
    private String smTypeName;

    @ApiModelProperty(value = "供应的原材料规格")
    private String smSpecs;

    @ApiModelProperty(value = "供应的原材料名称")
    private String smName;

    @ApiModelProperty(value = "供应的原材料备注")
    private String smRemark;

}
