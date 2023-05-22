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
 * 查询参数
 *
 * @author EleAdmin
 * @since 2023-04-15 12:46:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmMaterialParam对象", description = "查询参数")
public class BgmMaterialParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer materialId;

    @QueryField(type = QueryType.EQ)
    private Integer materialTypeId;

    private String materialName;

    private String materialType;

    private String materialSpecs;

    private String materialMeasurement;

    @QueryField(type = QueryType.EQ)
    private Integer materialStatus;

    private String materialRemark;

}
