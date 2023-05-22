package com.eleadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.entity.User;
import com.eleadmin.common.system.param.UserParam;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户Service
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:52
 */
public interface UserService extends IService<User>, UserDetailsService {

    /**
     * 关联分页查询用户
     *
     * @param param 查询参数
     * @return PageResult<User>
     */
    PageResult<User> pageRel(UserParam param);

    /**
     * 关联查询全部用户
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<User> listRel(UserParam param);

    /**
     * 根据id查询用户
     *
     * @param userId 用户id
     * @return User
     */
    User getByIdRel(Integer userId);

    /**
     * 根据账号查询用户
     *
     * @param username 账号
     * @return User
     */
    User getByUsername(String username);

    /**
     * 根据账号查询用户
     *
     * @param username 账号
     * @param tenantId 租户id
     * @return User
     */
    User getByUsername(String username, Integer tenantId);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean saveUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean updateUser(User user);

    /**
     * 比较用户密码
     *
     * @param dbPassword    数据库存储的密码
     * @param inputPassword 用户输入的密码
     * @return boolean
     */
    boolean comparePassword(String dbPassword, String inputPassword);

    /**
     * md5加密用户密码
     *
     * @param password 密码明文
     * @return 密文
     */
    String encodePassword(String password);

}
