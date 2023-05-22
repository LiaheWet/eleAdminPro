package com.eleadmin.bgm.param;

import com.eleadmin.bgm.entity.BgmAgreementMaterial;
import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 采购协议查询参数
 *
 * @author EleAdmin
 * @since 2023-05-04 16:55:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmAgreementParam对象", description = "采购协议查询参数")
public class BgmAgreementParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @QueryField(type = QueryType.EQ)
    private Integer agreementId;

    @ApiModelProperty(value = "采购编号")
    private String agreementNumber;

    @ApiModelProperty(value = "供应商id")
    @QueryField(type = QueryType.EQ)
    private Integer agreementVendorId;

    @ApiModelProperty(value = "供应商名字")
    private String agreementVendorName;

    @ApiModelProperty(value = "供应方式")
    private String agreementMethod;

    @ApiModelProperty(value = "联系人")
    private String agreementContact;

    @ApiModelProperty(value = "联系方式")
    private String agreementPhone;

    @ApiModelProperty(value = "开始采购时间")
    private String agreementCreateTime;

    @ApiModelProperty(value = "开始采购时间")
    private String agreementBuyTime;

    @ApiModelProperty(value = "采购地址")
    private String agreementAddress;

    @ApiModelProperty(value = "异常处理")
    private String agreementException;

    @ApiModelProperty(value = "违约责任")
    private String agreementBreach;

    @ApiModelProperty(value = "备注")
    private String agreementRemark;

    @ApiModelProperty(value = "采购人")
    private String agreementPurchaser;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer agreementStatus;

    @ApiModelProperty(value = "采购材料具体数据")
    private List<BgmAgreementMaterial> supplyMaterialList;

    @QueryField(type = QueryType.GE)
    private Date time1;

    @QueryField(type = QueryType.LE)
    private Date time2;

}
