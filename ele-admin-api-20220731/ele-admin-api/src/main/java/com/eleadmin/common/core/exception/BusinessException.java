package com.eleadmin.common.core.exception;

import com.eleadmin.common.core.Constants;

/**
 * 自定义业务异常
 *
 * @author EleAdmin
 * @since 2018-02-22 11:29:28
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    public BusinessException() {
        this(Constants.RESULT_ERROR_MSG);
    }

    public BusinessException(String message) {
        this(Constants.RESULT_ERROR_CODE, message);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
