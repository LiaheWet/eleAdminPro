package com.eleadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典数据
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:04
 */
@Data
@ApiModel(description = "字典数据")
@TableName("sys_dictionary_data")
public class DictionaryData implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典数据id")
    @TableId(type = IdType.AUTO)
    private Integer dictDataId;

    @ApiModelProperty(value = "字典id")
    private Integer dictId;

    @ApiModelProperty(value = "字典数据标识")
    private String dictDataCode;

    @ApiModelProperty(value = "字典数据名称")
    private String dictDataName;

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

    @ApiModelProperty(value = "字典代码")
    @TableField(exist = false)
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @TableField(exist = false)
    private String dictName;

}
