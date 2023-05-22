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
 * 采购人查询参数
 *
 * @author EleAdmin
 * @since 2023-05-06 16:40:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmPurchaserParam对象", description = "采购人查询参数")
public class BgmPurchaserParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "采购人名字")
    private String purchaserName;

}
