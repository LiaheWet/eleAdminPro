package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:03
 */
@Data
@ApiModel(description = "字典")
@TableName("sys_dictionary")
public class Dictionary implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典id")
    @TableId(type = IdType.AUTO)
    private Integer dictId;

    @ApiModelProperty(value = "字典标识")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
