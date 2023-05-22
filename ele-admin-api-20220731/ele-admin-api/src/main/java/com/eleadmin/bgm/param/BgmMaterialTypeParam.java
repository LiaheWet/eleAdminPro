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
 * 材料类别查询参数
 *
 * @author EleAdmin
 * @since 2023-04-12 10:41:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmMaterialTypeParam对象", description = "材料类别查询参数")
public class BgmMaterialTypeParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @QueryField(type = QueryType.EQ)
    private Integer materialTypeId;

    @ApiModelProperty(value = "材料类别名称 ")
    private String materialTypeName;

    @ApiModelProperty(value = "父子id")
    @QueryField(type = QueryType.EQ)
    private Integer materialTypePid;

}
