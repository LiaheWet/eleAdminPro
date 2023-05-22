package com.eleadmin.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据查询参数
 *
 * @author EleAdmin
 * @since 2021-08-28 22:12:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "字典数据查询参数")
public class DictionaryDataParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典数据id")
    @QueryField(type = QueryType.EQ)
    private Integer dictDataId;

    @ApiModelProperty(value = "字典id")
    @QueryField(type = QueryType.EQ)
    private Integer dictId;

    @ApiModelProperty(value = "字典数据标识")
    private String dictDataCode;

    @ApiModelProperty(value = "字典数据名称")
    private String dictDataName;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "字典代码")
    @TableField(exist = false)
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @TableField(exist = false)
    private String dictName;

    @ApiModelProperty(value = "字典数据代码或字典数据名称")
    @TableField(exist = false)
    private String keywords;

}
