package com.eleadmin.common.core.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.eleadmin.common.system.entity.User;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

/**
 * MybatisPlus配置
 *
 * @author EleAdmin
 * @since 2018-02-22 11:29:28
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 多租户插件配置
        TenantLineHandler tenantLineHandler = new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                return getLoginUserTenantId();
            }

            @Override
            public boolean ignoreTable(String tableName) {
                return Arrays.asList(
                        "sys_tenant",
                        "sys_dictionary",
                        "sys_dictionary_data",
                        "students",

                        "bgm_material",//原材料表
                        "bgm_measurement",//计量单位
                        "bgm_material_type",//原材料类别
                        "bgm_supply_material",//供应原材料
                        "bgm_vendor",//供应商
                        "bgm_agreement",//采购协议
                        "bgm_agreement_material",//采购协议（具体材料：数量，价格）
                        "bgm_purchaser",//采购员
                        "bgm_vendor_contact",//供应商联系人
                        "bgm_vendor_account",//供应商账户
                        "bgm_vendor_material",//供应商供应材料
                        "bgm_receive_management"//收料管理表

                ).contains(tableName);
            }
        };
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor(tenantLineHandler);
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);

        // 分页插件配置
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return interceptor;
    }

    /**
     * 获取当前登录用户的租户id
     *
     * @return Integer
     */
    public Expression getLoginUserTenantId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object object = authentication.getPrincipal();
                if (object instanceof User) {
                    return new LongValue(((User) object).getTenantId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new NullValue();
    }

}
