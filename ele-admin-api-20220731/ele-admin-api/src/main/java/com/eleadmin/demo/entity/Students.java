package com.eleadmin.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author EleAdmin
 * @since 2023-03-06 22:00:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Students对象", description = "")
public class Students implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    private String studentName;

    private String studentNumber;

    private String major;

    private String sex;

}
