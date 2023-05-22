package com.eleadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.common.system.mapper.UserFileMapper;
import com.eleadmin.common.system.service.UserFileService;
import com.eleadmin.common.system.entity.UserFile;
import org.springframework.stereotype.Service;

/**
 * 用户文件Service实现
 *
 * @author EleAdmin
 * @since 2022-07-21 14:34:40
 */
@Service
public class UserFileServiceImpl extends ServiceImpl<UserFileMapper, UserFile> implements UserFileService {

}
