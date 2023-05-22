package com.eleadmin.common.core.security;

import com.eleadmin.common.core.Constants;
import com.eleadmin.common.core.utils.CommonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有访问权限异常处理
 *
 * @author EleAdmin
 * @since 2020-03-25 00:35:03
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {
        CommonUtil.responseError(response, Constants.UNAUTHORIZED_CODE, Constants.UNAUTHORIZED_MSG, e.getMessage());
    }

}
