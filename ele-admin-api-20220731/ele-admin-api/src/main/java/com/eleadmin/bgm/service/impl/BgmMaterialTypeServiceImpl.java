package com.eleadmin.bgm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleadmin.bgm.entity.BgmMaterialTypeNode;
import com.eleadmin.bgm.mapper.BgmMaterialTypeMapper;
import com.eleadmin.bgm.service.BgmMaterialTypeService;
import com.eleadmin.bgm.entity.BgmMaterialType;
import com.eleadmin.bgm.param.BgmMaterialTypeParam;
import com.eleadmin.bgm.service.BgmMeasurementService;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 材料类别Service实现
 *
 * @author EleAdmin
 * @since 2023-04-12 10:41:31
 */
@Service
public class BgmMaterialTypeServiceImpl extends ServiceImpl<BgmMaterialTypeMapper, BgmMaterialType> implements BgmMaterialTypeService {

    @Override
    public PageResult<BgmMaterialType> pageRel(BgmMaterialTypeParam param) {
        PageParam<BgmMaterialType, BgmMaterialTypeParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<BgmMaterialType> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<BgmMaterialType> listRel(BgmMaterialTypeParam param) {
        List<BgmMaterialType> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<BgmMaterialType, BgmMaterialTypeParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public BgmMaterialType getByIdRel(Integer materialTypeId) {
        BgmMaterialTypeParam param = new BgmMaterialTypeParam();
        param.setMaterialTypeId(materialTypeId);
        return param.getOne(baseMapper.selectListRel(param));
    }




    @Override
    public List<BgmMaterialTypeNode> integrate(List<BgmMaterialType> allBgmMaterialType) {

        Map<Integer, BgmMaterialTypeNode> nodeMap = new HashMap<>();
        for (BgmMaterialType node : allBgmMaterialType) {
            BgmMaterialTypeNode nodeVO = new BgmMaterialTypeNode();
            BeanUtils.copyProperties(node, nodeVO);
            nodeMap.put(node.getMaterialTypeId(), nodeVO);
        }

        // 遍历所有节点，将每个节点添加到其父节点中
        List<BgmMaterialTypeNode> roots = new ArrayList<>();
        for (BgmMaterialType node : allBgmMaterialType) {
            int parentId = node.getMaterialTypePid();
            if (parentId == 0) { // 如果当前节点没有父节点，则将其作为根节点
                roots.add(nodeMap.get(node.getMaterialTypeId()));
            } else { // 如果当前节点有父节点，则将其添加到父节点的子节点列表中
                BgmMaterialTypeNode parentNode = nodeMap.get(parentId);
                if (parentNode != null) {
                    parentNode.addChild(nodeMap.get(node.getMaterialTypeId()));
                }
            }
        }

        return roots;

    }

}
