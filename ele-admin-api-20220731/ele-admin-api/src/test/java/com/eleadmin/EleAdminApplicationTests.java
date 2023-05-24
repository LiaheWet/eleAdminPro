package com.eleadmin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.eleadmin.bgm.entity.*;
import com.eleadmin.bgm.mapper.BgmAgreementMapper;
import com.eleadmin.bgm.mapper.BgmMaterialTypeMapper;
import com.eleadmin.bgm.mapper.BgmSupplyMaterialMapper;
import com.eleadmin.bgm.param.BgmAgreementParam;
import com.eleadmin.bgm.param.BgmSupplyMaterialParam;
import com.eleadmin.bgm.param.BgmVendorParam;
import com.eleadmin.bgm.service.*;
import com.eleadmin.bgm.service.impl.BgmAgreementServiceImpl;
import com.eleadmin.bgm.service.impl.BgmMaterialTypeServiceImpl;
import com.eleadmin.bgm.service.impl.BgmSupplyMaterialServiceImpl;
import com.eleadmin.common.core.web.PageParam;
import com.eleadmin.common.core.web.PageResult;
import com.eleadmin.demo.service.StudentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EleAdminApplicationTests {

    private BgmMeasurementService bgmMeasurementService;
    private BgmMaterialTypeService bgmMaterialTypeService;
    private BgmMaterialTypeServiceImpl bgmMaterialTypeServiceImpl;
    private BgmSupplyMaterialServiceImpl bgmSupplyMaterialService;

    private BgmAgreementService bgmAgreementService;
    private BgmAgreementMaterialService bgmAgreementMaterialService;

    @Autowired
    public void setBgmAgreementMaterialService(BgmAgreementMaterialService bgmAgreementMaterialService) {
        this.bgmAgreementMaterialService = bgmAgreementMaterialService;
    }

    @Autowired
    public void setBgmMaterialTypeServiceImpl(BgmMaterialTypeServiceImpl bgmMaterialTypeServiceImpl) {
        this.bgmMaterialTypeServiceImpl = bgmMaterialTypeServiceImpl;
    }

    private BgmVendorService bgmVendorService;

    @Autowired
    public void setBgmVendorService(BgmVendorService bgmVendorService) {
        this.bgmVendorService = bgmVendorService;
    }

    @Autowired
    public void setBgmAgreementService(BgmAgreementService bgmAgreementService) {
        this.bgmAgreementService = bgmAgreementService;
    }

    @Autowired
    public void setBgmSupplyMaterialService(BgmSupplyMaterialServiceImpl bgmSupplyMaterialService) {
        this.bgmSupplyMaterialService = bgmSupplyMaterialService;
    }

    @Autowired
    public void setBgmMaterialTypeService(BgmMaterialTypeServiceImpl bgmMaterialTypeService) {
        this.bgmMaterialTypeService = bgmMaterialTypeService;
    }

    @Autowired
    public void setBgmMeasurementService(BgmMeasurementService bgmMeasurementService) {
        this.bgmMeasurementService = bgmMeasurementService;
    }

    @Autowired
    public void setBgmMaterialTypeService(BgmMaterialTypeService bgmMaterialTypeService) {
        this.bgmMaterialTypeService = bgmMaterialTypeService;
    }

    @Test
    public void contextLoads() {
/*              //查询供应呀啊
        BgmSupplyMaterialParam param=new BgmSupplyMaterialParam();
        //param.setSmTypeId(20);
        System.out.println(bgmSupplyMaterialService.pageList(param).getList());*/

    }

    @Test
    void agreementTest() {
        //查询协议和协议的供应材料信息（多表数据合成一条数据返回）
        /*BgmAgreementParam bgmAgreementParam=new BgmAgreementParam();
        bgmAgreementParam.setAgreementId(18);
        System.out.println(bgmAgreementService.selectPro(bgmAgreementParam));*/
        //查询供应商、联系人、账户、供应材料信息（多表数据合成一条数据返回）
        /*BgmVendorParam bgmVendorParam=new BgmVendorParam();
        bgmVendorParam.setVendorId(1);
        System.out.println(bgmVendorService.selectPro(bgmVendorParam));*/
/*        BgmAgreement bgmAgreement = bgmAgreementService.getByIdRel(24);
        System.out.println(bgmAgreement.getAgreementNumber());
        if (bgmAgreementMaterialService.removeAll(bgmAgreement.getAgreementNumber())) {
            System.out.println("666");
        }*/

    }
    @Test
    void timeTest() throws ParseException {
        BgmAgreementParam param=new BgmAgreementParam();
        List<Integer>list=new ArrayList<>();
        //list.add(1);
        list.add(3);
        list.add(4);
        param.setAgreementStatusList(list);
        System.out.println(bgmAgreementService.pageRel(param).getList());
    }
}
