package com.eleadmin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.eleadmin.bgm.entity.*;
import com.eleadmin.bgm.mapper.BgmAgreementMapper;
import com.eleadmin.bgm.mapper.BgmMaterialTypeMapper;
import com.eleadmin.bgm.mapper.BgmSupplyMaterialMapper;
import com.eleadmin.bgm.param.*;
import com.eleadmin.bgm.service.*;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EleAdminApplicationTests {

    private BgmMeasurementService bgmMeasurementService;
    private BgmMaterialTypeServiceImpl bgmMaterialTypeServiceImpl;
    private BgmSupplyMaterialServiceImpl bgmSupplyMaterialService;

    private BgmAgreementService bgmAgreementService;



    private BgmAgreementMaterialService bgmAgreementMaterialService;
    private BgmReceiveManagementService bgmReceiveManagementService;

    private BgmVendorMaterialService bgmVendorMaterialService;
    @Autowired
    public void setBgmVendorMaterialService(BgmVendorMaterialService bgmVendorMaterialService) {
        this.bgmVendorMaterialService = bgmVendorMaterialService;
    }

    @Autowired
    public void setBgmAgreementMaterialService(BgmAgreementMaterialService bgmAgreementMaterialService) {
        this.bgmAgreementMaterialService = bgmAgreementMaterialService;
    }
    @Autowired
    public void setBgmReceiveManagementService(BgmReceiveManagementService bgmReceiveManagementService) {
        this.bgmReceiveManagementService = bgmReceiveManagementService;
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
    public void setBgmMeasurementService(BgmMeasurementService bgmMeasurementService) {
        this.bgmMeasurementService = bgmMeasurementService;
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
        BgmReceiveManagementParam param=new BgmReceiveManagementParam();
        List<Integer>list=new ArrayList<>();
        list.add(1);
        list.add(3);
        //param.setManagementStatusList(list);
        String dateStr = "2023-05-23 00:47:05";
        String dateStr2 = "2023-05-24 00:47:05";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        Date date2 = sdf.parse(dateStr2);
        param.setTime1(date);
        param.setTime2(date2);
        System.out.println(bgmReceiveManagementService.pageRel(param).getList());
    }
    @Test
    void bgmAgreementMaterialTest(){
        String str = "SL230530002";
        String lastThree = String.format("%03d", Integer.parseInt(str.substring(str.length() - 3))); // 获取并格式化后3位
        int result = Integer.parseInt(lastThree) + 134; // 进行加9操作
        System.out.println(lastThree);
        System.out.println(String.format("%03d", result)); // 输出结果并格式化为三位数
    }
    @Test
    void bgmVendorMaterialServiceTest(){
        BgmVendorMaterialParam param=new BgmVendorMaterialParam();
        param.setVendorId(3);
        System.out.println(bgmVendorMaterialService.listRel(param));
    }
}
