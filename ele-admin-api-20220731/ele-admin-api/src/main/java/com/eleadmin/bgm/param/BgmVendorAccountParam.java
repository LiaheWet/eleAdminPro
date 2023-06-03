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
 * 供应商账户信息查询参数
 *
 * @author EleAdmin
 * @since 2023-06-03 16:13:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmVendorAccountParam对象", description = "供应商账户信息查询参数")
public class BgmVendorAccountParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @QueryField(type = QueryType.EQ)
    private Integer vendorId;

    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "开户银行")
    private String accountBank;

    @ApiModelProperty(value = "银行账号")
    private String accountNumber;

}
