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
 * 菜单查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 19:36:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "菜单查询参数")
public class MenuParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    @QueryField(type = QueryType.EQ)
    private Integer menuId;

    @ApiModelProperty("上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单路由关键字")
    private String path;

    @ApiModelProperty("菜单组件地址")
    private String component;

    @ApiModelProperty("菜单类型, 0菜单, 1按钮")
    @QueryField(type = QueryType.EQ)
    private Integer menuType;

    @ApiModelProperty("权限标识")
    private String authority;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("是否隐藏, 0否, 1是(仅注册路由不显示左侧菜单)")
    @QueryField(type = QueryType.EQ)
    private Integer hide;

}
