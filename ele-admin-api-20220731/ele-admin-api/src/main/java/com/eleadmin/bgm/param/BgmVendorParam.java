package com.eleadmin.bgm.param;

import com.eleadmin.bgm.controller.BgmVendorController;
import com.eleadmin.bgm.entity.BgmSupplyMaterial;
import com.eleadmin.bgm.entity.BgmVendorAccount;
import com.eleadmin.bgm.entity.BgmVendorContact;
import com.eleadmin.common.core.annotation.QueryField;
import com.eleadmin.common.core.annotation.QueryType;
import com.eleadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
/**
 * 供应商查询参数
 *
 * @author EleAdmin
 * @since 2023-04-25 16:31:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BgmVendorParam对象", description = "供应商查询参数")
public class BgmVendorParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer vendorId;

    @ApiModelProperty(value = "供应商名称")
    private String vendorName;

    @ApiModelProperty(value = "供应商单位类别")
    private String vendorType;

    @ApiModelProperty(value = "供应商法人代表")
    private String vendorRepresent;

    @ApiModelProperty(value = "供应商电花")
    private String vendorPhone;

    @ApiModelProperty(value = "供应商传真")
    private String vendorFax;

    @ApiModelProperty(value = "供应商邮箱")
    private String vendorEmail;

    @ApiModelProperty(value = "供应商地址")
    private String vendorAddress;

    @ApiModelProperty(value = "供应商邮编")
    private String vendorPostal;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer vendorStatus;

    @ApiModelProperty(value = "采购员")
    private String buyer;

    @ApiModelProperty(value = "纳税号")
    private String taxId;

    @ApiModelProperty(value = "信誉度")
    @QueryField(type = QueryType.EQ)
    private Integer credit;

    @ApiModelProperty(value = "不合格")
    @QueryField(type = QueryType.EQ)
    private Integer unqualified;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "关注")
    @QueryField(type = QueryType.EQ)
    private Integer focus;

    @ApiModelProperty(value = "供应商联系人信息")
    private List<BgmVendorContact> bgmVendorContactList ;

    @ApiModelProperty(value = "供应商联账户信息")
    private List<BgmVendorAccount> bgmVendorAccountList;

    @ApiModelProperty(value = "供应原材料信息")
    private List <BgmSupplyMaterial>bgmSupplyMaterialList;

}
