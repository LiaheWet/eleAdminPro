package com.eleadmin.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleadmin.common.system.entity.Role;
import com.eleadmin.common.system.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色Mapper
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:02
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量添加用户角色
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     * @return int
     */
    int insertBatch(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Role> selectByUserId(@Param("userId") Integer userId);

    /**
     * 批量根据用户id查询角色
     *
     * @param userIds 用户id集合
     * @return List<RoleResult>
     */
    List<Role> selectByUserIds(@Param("userIds") List<Integer> userIds);

}
