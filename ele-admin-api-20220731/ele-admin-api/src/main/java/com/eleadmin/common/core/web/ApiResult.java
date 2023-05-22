package com.eleadmin.common.core.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author EleAdmin
 * @since 2017-06-10 10:10:50
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "状态信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    @ApiModelProperty(value = "错误信息")
    private String error;

    public ApiResult() {
    }

    public ApiResult(Integer code) {
        this(code, null);
    }

    public ApiResult(Integer code, String message) {
        this(code, message, null);
    }

    public ApiResult(Integer code, String message, T data) {
        this(code, message, data, null);
    }

    public ApiResult(Integer code, String message, T data, String error) {
        setCode(code);
        setMessage(message);
        setData(data);
        setError(error);
    }

    public Integer getCode() {
        return this.code;
    }

    public ApiResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getError() {
        return this.error;
    }

    public ApiResult<T> setError(String error) {
        this.error = error;
        return this;
    }

}
