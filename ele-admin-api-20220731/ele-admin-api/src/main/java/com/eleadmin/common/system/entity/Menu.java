package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;

/**
 * 菜单
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:17
 */
@Data
@ApiModel(description = "菜单")
@TableName("sys_menu")
public class Menu implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    public static final int TYPE_MENU = 0;  // 菜单类型
    public static final int TYPE_BTN = 1;  // 按钮类型

    @ApiModelProperty("菜单id")
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty("上级id, 0是顶级")
    private Integer parentId;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单路由地址")
    private String path;

    @ApiModelProperty("菜单组件地址")
    private String component;

    @ApiModelProperty("菜单类型, 0菜单, 1按钮")
    private Integer menuType;

    @ApiModelProperty("排序号")
    private Integer sortNumber;

    @ApiModelProperty("权限标识")
    private String authority;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("是否隐藏, 0否, 1是(仅注册路由不显示左侧菜单)")
    private Integer hide;

    @ApiModelProperty("路由元信息")
    private String meta;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    @ApiModelProperty("角色权限树选中状态")
    @TableField(exist = false)
    private Boolean checked;

}
