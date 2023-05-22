package com.eleadmin.bgm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: heng
 * @DATE:2023/4/12 11:54
 **/
public class BgmMaterialTypeNode {

    @ApiModelProperty(value = "id")
    @TableId(value = "material_type_id", type = IdType.AUTO)
    private Integer materialTypeId;

    @ApiModelProperty(value = "材料类别名称 ")
    private String materialTypeName;

    @ApiModelProperty(value = "父子id")
    private Integer materialTypePid;
    private List<BgmMaterialTypeNode> children;

    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public Integer getMaterialTypePid() {
        return materialTypePid;
    }

    public void setMaterialTypePid(Integer materialTypePid) {
        this.materialTypePid = materialTypePid;
    }

    public List<BgmMaterialTypeNode> getChildren() {
        return children;
    }

    public void setChildren(List<BgmMaterialTypeNode> children) {
        this.children = children;
    }
    public void addChild(BgmMaterialTypeNode child) {
        if (child == null) {
            return;
        }
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "BgmMaterialTypeNode{" +
                "materialTypeId=" + materialTypeId +
                ", materialTypeName='" + materialTypeName + '\'' +
                ", materialTypePid=" + materialTypePid +
                ", children=" + children +
                '}';
    }
}