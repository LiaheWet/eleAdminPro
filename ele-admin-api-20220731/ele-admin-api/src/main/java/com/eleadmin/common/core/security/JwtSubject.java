package com.eleadmin.common.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Jwt载体
 *
 * @author EleAdmin
 * @since 2021-09-03 00:11:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtSubject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String username;

    /**
     * 租户id
     */
    private Integer tenantId;

}
