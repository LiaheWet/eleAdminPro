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
 * 计量单位查询参数
 *
 * @author EleAdmin
 * @since 2023-04-09 14:15:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmMeasurementParam对象", description = "计量单位查询参数")
public class BgmMeasurementParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer measurementId;

    private String measurementName;

    private String measurementSymbol;

    @QueryField(type = QueryType.EQ)
    private Integer measurementStatus;

}
