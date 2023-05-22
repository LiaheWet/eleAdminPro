package com.eleadmin.common.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.exception.BusinessException;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.common.system.mapper.UserMapper;
import com.eleadmin.common.system.entity.Role;
import com.eleadmin.common.system.entity.User;
import com.eleadmin.common.system.entity.UserRole;
import com.eleadmin.common.system.param.UserParam;
import com.eleadmin.common.system.service.RoleMenuService;
import com.eleadmin.common.system.service.UserRoleService;
import com.eleadmin.common.system.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户Service实现
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public PageResult<User> pageRel(UserParam param) {
        PageParam<User, UserParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<User> list = baseMapper.selectPageRel(page, param);
        // 查询用户的角色
        selectUserRoles(list);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<User> listRel(UserParam param) {
        List<User> list = baseMapper.selectListRel(param);
        // 查询用户的角色
        selectUserRoles(list);
        // 排序
        PageParam<User, UserParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public User getByIdRel(Integer userId) {
        UserParam param = new UserParam();
        param.setUserId(userId);
        User user = param.getOne(baseMapper.selectListRel(param));
        if (user != null) {
            user.setRoles(userRoleService.listByUserId(user.getUserId()));
            user.setAuthorities(roleMenuService.listMenuByUserId(user.getUserId(), null));
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return getByUsername(username, null);
    }

    @Override
    public User getByUsername(String username, Integer tenantId) {
        if (StrUtil.isBlank(username)) {
            return null;
        }
        User user = baseMapper.selectByUsername(username, tenantId);
        if (user != null) {
            user.setRoles(userRoleService.listByUserId(user.getUserId()));
            user.setAuthorities(roleMenuService.listMenuByUserId(user.getUserId(), null));
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }

    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
    @Override
    public boolean saveUser(User user) {
        if (StrUtil.isNotEmpty(user.getUsername()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())) > 0) {
            throw new BusinessException("账号已存在");
        }
        if (StrUtil.isNotEmpty(user.getPhone()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, user.getPhone())) > 0) {
            throw new BusinessException("手机号已存在");
        }
        if (StrUtil.isNotEmpty(user.getEmail()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, user.getEmail())) > 0) {
            throw new BusinessException("邮箱已存在");
        }
        boolean result = baseMapper.insert(user) > 0;
        if (result && user.getRoles() != null && user.getRoles().size() > 0) {
            List<Integer> roleIds = user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toList());
            if (userRoleService.saveBatch(user.getUserId(), roleIds) < roleIds.size()) {
                throw new BusinessException("用户角色添加失败");
            }
        }
        return result;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateUser(User user) {
        if (StrUtil.isNotEmpty(user.getUsername()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .ne(User::getUserId, user.getUserId())) > 0) {
            throw new BusinessException("账号已存在");
        }
        if (StrUtil.isNotEmpty(user.getPhone()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, user.getPhone())
                .ne(User::getUserId, user.getUserId())) > 0) {
            throw new BusinessException("手机号已存在");
        }
        if (StrUtil.isNotEmpty(user.getEmail()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, user.getEmail())
                .ne(User::getUserId, user.getUserId())) > 0) {
            throw new BusinessException("邮箱已存在");
        }
        boolean result = baseMapper.updateById(user) > 0;
        if (result && user.getRoles() != null && user.getRoles().size() > 0) {
            userRoleService.remove(new LambdaUpdateWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
            List<Integer> roleIds = user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toList());
            if (userRoleService.saveBatch(user.getUserId(), roleIds) < roleIds.size()) {
                throw new BusinessException("用户角色添加失败");
            }
        }
        return result;
    }

    @Override
    public boolean comparePassword(String dbPassword, String inputPassword) {
        return bCryptPasswordEncoder.matches(inputPassword, dbPassword);
    }

    @Override
    public String encodePassword(String password) {
        return password == null ? null : bCryptPasswordEncoder.encode(password);
    }

    /**
     * 批量查询用户的角色
     *
     * @param users 用户集合
     */
    private void selectUserRoles(List<User> users) {
        if (users != null && users.size() > 0) {
            List<Integer> userIds = users.stream().map(User::getUserId).collect(Collectors.toList());
            List<Role> userRoles = userRoleService.listByUserIds(userIds);
            for (User user : users) {
                List<Role> roles = userRoles.stream().filter(d -> user.getUserId().equals(d.getUserId()))
                        .collect(Collectors.toList());
                user.setRoles(roles);
            }
        }
    }

}
