package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.mapper.BgmReceiveManagementMapper;
import com.eleadmin.bgm.service.BgmReceiveManagementService;
import com.eleadmin.bgm.entity.BgmReceiveManagement;
import com.eleadmin.bgm.param.BgmReceiveManagementParam;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 收料管理表Service实现
 *
 * @author EleAdmin
 * @since 2023-05-26 09:37:03
 */
@Service
public class BgmReceiveManagementServiceImpl extends ServiceImpl<BgmReceiveManagementMapper, BgmReceiveManagement> implements BgmReceiveManagementService {

    @Override
    public PageResult<BgmReceiveManagementParam> pageRel(BgmReceiveManagementParam param) {
        PageParam<BgmReceiveManagementParam, BgmReceiveManagementParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmReceiveManagementParam> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmReceiveManagement> listRel(BgmReceiveManagementParam param) {
        List<BgmReceiveManagement> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmReceiveManagement, BgmReceiveManagementParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmReceiveManagement getByIdRel(Integer id) {
        BgmReceiveManagementParam param = new BgmReceiveManagementParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public String getNewManagementNumber() {
        BgmReceiveManagementParam param=new BgmReceiveManagementParam();
        LocalDateTime now = LocalDateTime.now();
        String dateStr ="SL"+DateTimeFormatter.ofPattern("yyMMdd").format(now);
        param.setManagementNumber(dateStr);
        List<BgmReceiveManagement> list = baseMapper.selectListRel(param);
        if(list != null && list.size() > 0) {
            BgmReceiveManagement lastData = list.get(list.size() - 1);
            String str=lastData.getManagementNumber();
            String lastThree = String.format("%03d", Integer.parseInt(str.substring(str.length() - 3))); // 获取并格式化后3位
            int result = Integer.parseInt(lastThree) + 1; // 进行加9操作
           return dateStr + String.format("%03d", result);//输出结果并格式化为三位数
            // 使用lastData进行其他操作
        } else {
            // List为空，无法获取最后一组数据
            return dateStr+"001";
        }
    }

}
