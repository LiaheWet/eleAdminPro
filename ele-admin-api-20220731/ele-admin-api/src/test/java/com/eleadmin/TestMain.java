package com.eleadmin;

import com.eleadmin.common.core.web.ApiResult;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.system.param.UserParam;
import com.eleadmin.common.system.service.UserService;

import org.junit.jupiter.api.Test;

import com.eleadmin.common.core.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by EleAdmin on 2020-03-23 23:37
 */
public class TestMain {

    /**
     * 生成唯一的key用于jwt工具类
     */
    @Test
    public void testGenJwtKey() {
        System.out.println(JwtUtil.encodeKey(JwtUtil.randomKey()));
    }

    /**
     * 生成加密后的登录密码
     */
    @Test
    public void testEncodePassword() {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }

    /**
     * 校验密码
     */
    @Test
    public void testComparePassword() {
        System.out.println(new BCryptPasswordEncoder().matches(
                "admin",
                "$2a$10$W/218CEDADkJ1iUU3rLI6.x7F3TtScvEIFjKcII2oGb7flKWzznae"
        ));
    }

    @Test
    public void test() {

    }


}
