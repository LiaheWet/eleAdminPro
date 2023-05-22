package com.eleadmin.common.system.param;

import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典查询参数
 *
 * @author EleAdmin
 * @since 2021-08-28 22:12:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "字典查询参数")
public class DictionaryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    @ApiModelProperty(value = "字典id")
    private Integer dictId;

    @ApiModelProperty(value = "字典标识")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "备注")
    private String comments;

}
