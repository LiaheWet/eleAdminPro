/*
 Navicat Premium Data Transfer

 Source Server         : jdbc
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : ele

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 22/05/2023 16:46:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bgm_agreement
-- ----------------------------
DROP TABLE IF EXISTS `bgm_agreement`;
CREATE TABLE `bgm_agreement`  (
  `agreement_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `agreement_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '采购编号',
  `agreement_vendor_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `agreement_vendor_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商名字',
  `agreement_method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '往来单位运输' COMMENT '供应方式',
  `agreement_contact` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `agreement_phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `agreement_create_time` datetime(0) NULL DEFAULT NULL COMMENT '开始采购时间',
  `agreement_buy_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开始采购时间',
  `agreement_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '采购地址',
  `agreement_exception` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '异常处理',
  `agreement_breach` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违约责任',
  `agreement_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `agreement_purchaser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '采购人',
  `agreement_status` int(0) NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`agreement_id`) USING BTREE,
  UNIQUE INDEX `bgm_agreement_agreement_id_uindex`(`agreement_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购协议' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_agreement
-- ----------------------------
INSERT INTO `bgm_agreement` VALUES (2, 'XY230424019', 3, '重庆建设集团', '往来单位运输', '重建:李经理', '12323232232', '2023-04-26 19:44:50', '2023-05-14T16:00:00.000Z', '重庆市铜梁', '异常处理', '违约责任', '备注', '采购人admin', 0);
INSERT INTO `bgm_agreement` VALUES (16, 'XY230514394', 1, '巴啦啦', '往来单位运输', '巴啦啦练习人:张3', '12345678900', '2023-05-14 18:16:40', '2023-05-20T16:00:00.000Z', '交货地点:被', '异常处理:', '违约责任:', '备注:', '采购人张三', 1);
INSERT INTO `bgm_agreement` VALUES (17, 'XY230514736', 1, '巴啦啦', '往来单位运输', '巴啦啦练习人:王5', '12345678901', '2023-05-14 18:20:55', '2023-05-14T16:00:00.000Z', '交货地点:', '异常处理:', '违约责任:', '备注:', '采购人零庭', 1);
INSERT INTO `bgm_agreement` VALUES (18, 'XY230515518', 1, '巴啦啦', '往来单位运输', '巴啦啦练习人:王5', '12345678901', '2023-05-15 10:31:31', '2023-05-14T16:00:00.000Z', '交货地点:', '骚零庭', '骚零庭', '骚零庭', '采购人零庭', 1);
INSERT INTO `bgm_agreement` VALUES (22, 'XY230515836', 1, '巴啦啦', '往来单位运输', '巴啦啦练习人:王5', '12345678901', '2023-05-15 20:42:06', '2023-05-15T16:00:00.000Z', '交货地点:', '异常处理:', '备注:', '备注:', '采购人admin', 1);
INSERT INTO `bgm_agreement` VALUES (23, 'XY230517442', 2, '呢玲玲', '往来单位运输', '呢玲玲联系人：屮艸芔茻', '12345678902', '2023-05-17 23:01:02', '2023-05-16T16:00:00.000Z', '长沙', '异常处理', '违约责任', '偶系备注', '采购人零庭', 1);

-- ----------------------------
-- Table structure for bgm_agreement_material
-- ----------------------------
DROP TABLE IF EXISTS `bgm_agreement_material`;
CREATE TABLE `bgm_agreement_material`  (
  `am_id` int(0) NOT NULL AUTO_INCREMENT,
  `am_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '协议编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '制定时间',
  `supply_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应原材料',
  `material_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '平台原材料',
  `measurement` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位',
  `number` int(0) NULL DEFAULT NULL COMMENT '计划数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  PRIMARY KEY (`am_id`) USING BTREE,
  UNIQUE INDEX `bgm_agreement_material_id_uindex`(`am_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '协议订购材料具体数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_agreement_material
-- ----------------------------
INSERT INTO `bgm_agreement_material` VALUES (30, 'XY230514394', '2023-05-14 08:00:00', '供应原材料1', '平台原材料1', '吨', 1, 2.00, 2.00);
INSERT INTO `bgm_agreement_material` VALUES (31, 'XY230514394', '2023-05-14 08:00:00', '供应原材料2', '平台原材料2', '吨', 2, 3.00, 6.00);
INSERT INTO `bgm_agreement_material` VALUES (32, 'XY230514736', '2023-05-14 08:00:00', '供应原材料', '平台原材料	 计量', '吨', 12, 32.00, 32.00);
INSERT INTO `bgm_agreement_material` VALUES (33, 'XY230515518', '2023-05-15 08:00:00', '供应原材料3', '平台原材料3', '吨', 12, 1.00, 12.00);
INSERT INTO `bgm_agreement_material` VALUES (34, 'XY230515518', '2023-05-15 09:00:00', '供应原材料4', '平台原材料4', '吨', 22, 2.00, 44.00);
INSERT INTO `bgm_agreement_material` VALUES (35, 'XY230515518', '2023-05-15 09:00:00', '供应原材料5', '平台原材料5', '吨', 10, 10.00, 100.00);
INSERT INTO `bgm_agreement_material` VALUES (36, 'XY230515518', '2023-05-15 09:00:00', '供应原材料6', '平台原材料6', '吨', 80, 10.00, 800.00);
INSERT INTO `bgm_agreement_material` VALUES (43, 'XY230515836', '2023-05-15 08:00:00', '供应原材料11', '平台原材料11', '吨', 12, 5.00, 60.00);
INSERT INTO `bgm_agreement_material` VALUES (44, 'XY230515836', '2023-05-15 08:00:00', '供应原材料22', '平台原材料22', '吨', 20, 60.00, 1200.00);
INSERT INTO `bgm_agreement_material` VALUES (45, 'XY230517442', '2023-05-17 08:00:00', '天然卵石2', '天然卵石5～20', '吨', 12, 289.00, 3468.00);
INSERT INTO `bgm_agreement_material` VALUES (46, 'XY230517442', '2023-05-17 08:00:00', '烧杯庭', '粉煤灰一级', '吨', 32, 302.50, 9680.00);
INSERT INTO `bgm_agreement_material` VALUES (59, 'XY230424019', '2023-05-19 08:00:00', '天然卵石1', '天然卵石5～20', '吨', 0, 0.00, 0.00);

-- ----------------------------
-- Table structure for bgm_material
-- ----------------------------
DROP TABLE IF EXISTS `bgm_material`;
CREATE TABLE `bgm_material`  (
  `material_id` int(0) NOT NULL AUTO_INCREMENT,
  `material_type_id` int(0) NOT NULL DEFAULT 0 COMMENT '材料类别id',
  `material_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '材料名称',
  `material_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '材料类别',
  `material_specs` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '材料规格',
  `material_measurement` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '材料计量单位',
  `material_status` int(0) NULL DEFAULT NULL COMMENT '材料状态',
  `material_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`material_id`) USING BTREE,
  UNIQUE INDEX `bgm_materials_materials_id_uindex`(`material_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '材料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_material
-- ----------------------------
INSERT INTO `bgm_material` VALUES (10, 20, 'P.O42.5', '水泥 水泥1', 'P.O42.5', '千克', 1, '备注1');
INSERT INTO `bgm_material` VALUES (11, 20, '水泥2', '水泥 水泥1', '万年青', '千克', 0, '666');
INSERT INTO `bgm_material` VALUES (12, 10, '水泥P.C32.5', '水泥 复合硅酸盐水泥', 'P.C32.5', '千克', 1, 'P.C32.5');
INSERT INTO `bgm_material` VALUES (13, 12, '矿粉S75', '掺和料 矿渣粉', NULL, '千克', 1, NULL);
INSERT INTO `bgm_material` VALUES (14, 12, '矿粉S95', '掺和料 矿渣粉', NULL, '千克', 1, NULL);
INSERT INTO `bgm_material` VALUES (18, 17, '碎石1', '粗骨料 石子', '<5cm', '千克', 1, '<5cm');
INSERT INTO `bgm_material` VALUES (19, 17, '碎石2', '粗骨料 石子', '5-10cm', '千克', 1, '5-10cm');
INSERT INTO `bgm_material` VALUES (20, 11, '粉煤灰一级', '掺和料 粉煤灰', '<1mm', '千克', 1, '<1mm');
INSERT INTO `bgm_material` VALUES (21, 11, '粉煤灰二级', '掺和料 粉煤灰', '<5mm', '千克', 1, '5mm');
INSERT INTO `bgm_material` VALUES (22, 16, '河水', '水 天然水', '河水', '千克', 1, NULL);
INSERT INTO `bgm_material` VALUES (23, 17, '天然卵石5～20', '粗骨料 石子', '5～20', '千克', 1, NULL);

-- ----------------------------
-- Table structure for bgm_material_type
-- ----------------------------
DROP TABLE IF EXISTS `bgm_material_type`;
CREATE TABLE `bgm_material_type`  (
  `material_type_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `material_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '材料类别名称',
  `material_type_pid` int(0) NULL DEFAULT NULL COMMENT '父子id',
  PRIMARY KEY (`material_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '材料类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_material_type
-- ----------------------------
INSERT INTO `bgm_material_type` VALUES (1, '水泥', 0);
INSERT INTO `bgm_material_type` VALUES (2, '掺和料', 0);
INSERT INTO `bgm_material_type` VALUES (3, '细骨料', 0);
INSERT INTO `bgm_material_type` VALUES (4, '粗骨料', 0);
INSERT INTO `bgm_material_type` VALUES (5, '水', 0);
INSERT INTO `bgm_material_type` VALUES (6, '外加剂', 0);
INSERT INTO `bgm_material_type` VALUES (7, '其他', 0);
INSERT INTO `bgm_material_type` VALUES (10, '复合硅酸盐水泥', 1);
INSERT INTO `bgm_material_type` VALUES (11, '粉煤灰', 2);
INSERT INTO `bgm_material_type` VALUES (12, '矿渣粉', 2);
INSERT INTO `bgm_material_type` VALUES (15, '人工砂', 3);
INSERT INTO `bgm_material_type` VALUES (16, '天然水', 5);
INSERT INTO `bgm_material_type` VALUES (17, '石子', 4);
INSERT INTO `bgm_material_type` VALUES (18, '人工水', 5);
INSERT INTO `bgm_material_type` VALUES (20, '水泥1', 1);
INSERT INTO `bgm_material_type` VALUES (21, '其他原材料', 7);
INSERT INTO `bgm_material_type` VALUES (22, '纤维', 7);
INSERT INTO `bgm_material_type` VALUES (23, '减水剂', 6);
INSERT INTO `bgm_material_type` VALUES (24, '引气剂', 6);
INSERT INTO `bgm_material_type` VALUES (25, '缓凝剂', 6);

-- ----------------------------
-- Table structure for bgm_measurement
-- ----------------------------
DROP TABLE IF EXISTS `bgm_measurement`;
CREATE TABLE `bgm_measurement`  (
  `measurement_id` int(0) NOT NULL AUTO_INCREMENT,
  `measurement_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `measurement_symbol` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `measurement_status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`measurement_id`) USING BTREE,
  UNIQUE INDEX `bgm_measurements_measurements_id_uindex`(`measurement_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '计量单位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_measurement
-- ----------------------------
INSERT INTO `bgm_measurement` VALUES (1, '千克', 'kg', 1);
INSERT INTO `bgm_measurement` VALUES (2, '吨', 't', 1);
INSERT INTO `bgm_measurement` VALUES (13, '方', 'm3', 1);
INSERT INTO `bgm_measurement` VALUES (14, '个', '个', 1);

-- ----------------------------
-- Table structure for bgm_purchaser
-- ----------------------------
DROP TABLE IF EXISTS `bgm_purchaser`;
CREATE TABLE `bgm_purchaser`  (
  `id` int(0) NOT NULL,
  `purchaser_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '采购人名字',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `bgm_purchaser_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购人' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_purchaser
-- ----------------------------
INSERT INTO `bgm_purchaser` VALUES (1, '采购人张三');
INSERT INTO `bgm_purchaser` VALUES (2, '采购人零庭');
INSERT INTO `bgm_purchaser` VALUES (3, '采购人admin');

-- ----------------------------
-- Table structure for bgm_supply_material
-- ----------------------------
DROP TABLE IF EXISTS `bgm_supply_material`;
CREATE TABLE `bgm_supply_material`  (
  `supply_material_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '供应的原材料id',
  `sm_type_id` int(0) NULL DEFAULT NULL COMMENT '供应的原材料类别id',
  `sm_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应的原材料类别名字',
  `sm_specs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应的原材料规格',
  `sm_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应的原材料名称',
  `sm_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应的原材料备注',
  `sm_status` int(0) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`supply_material_id`) USING BTREE,
  UNIQUE INDEX `bgm_supply_material_supply_material_id_uindex`(`supply_material_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '供应的原材料(关联表;多对多)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_supply_material
-- ----------------------------
INSERT INTO `bgm_supply_material` VALUES (2, 10, 'P.O42.5', 'P.O42.5', '供应水泥2(自命名)', '备注', 1);
INSERT INTO `bgm_supply_material` VALUES (3, 20, '粉煤灰一级', '<1mm', '供应粉煤灰一级1(自命名)', '备注', 1);
INSERT INTO `bgm_supply_material` VALUES (4, 20, '粉煤灰一级', '<1mm', '供应粉煤灰一级2(自命名)', '备注1', 0);
INSERT INTO `bgm_supply_material` VALUES (5, 20, '粉煤灰一级', NULL, '供应粉煤灰一级3(自命名)', '备注', 0);
INSERT INTO `bgm_supply_material` VALUES (6, 21, '粉煤灰二级', NULL, '供应粉煤灰二级1(自命名)', '备注', 1);
INSERT INTO `bgm_supply_material` VALUES (7, 21, '粉煤灰二级', '<5mm', '供应粉煤灰二级2(自命名)', '备注', 1);
INSERT INTO `bgm_supply_material` VALUES (8, 23, '天然卵石5～20', '5～20', '天然卵石1', '老马', 1);
INSERT INTO `bgm_supply_material` VALUES (9, 23, '天然卵石5～20', '5～20', '天然卵石2', '老莫', 0);
INSERT INTO `bgm_supply_material` VALUES (10, 20, '粉煤灰一级', '<1mm', '烧杯庭', '123', 1);
INSERT INTO `bgm_supply_material` VALUES (12, 21, '粉煤灰二级', '<5mm', '烧零庭', '123', 1);
INSERT INTO `bgm_supply_material` VALUES (13, 10, 'P.O42.5', 'P.O42.5', '供应水泥3(自命名)', '备注', 1);
INSERT INTO `bgm_supply_material` VALUES (14, 10, 'P.O42.5', 'P.O42.5', '供应水泥4(自命名)', '备注', 0);

-- ----------------------------
-- Table structure for bgm_vendor
-- ----------------------------
DROP TABLE IF EXISTS `bgm_vendor`;
CREATE TABLE `bgm_vendor`  (
  `vendor_id` int(0) NOT NULL AUTO_INCREMENT,
  `vendor_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '供应商名称',
  `vendor_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商单位类别',
  `vendor_represent` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商法人代表',
  `vendor_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商电花',
  `vendor_fax` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商传真',
  `vendor_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商邮箱',
  `vendor_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商地址',
  `vendor_postal` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商邮编',
  `vendor_status` int(0) NULL DEFAULT NULL COMMENT '状态',
  `buyer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '采购员',
  `tax_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '纳税号',
  `credit` int(0) NULL DEFAULT NULL COMMENT '信誉度',
  `unqualified` int(0) NULL DEFAULT NULL COMMENT '不合格',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `focus` int(0) NULL DEFAULT NULL COMMENT '关注',
  PRIMARY KEY (`vendor_id`) USING BTREE,
  UNIQUE INDEX `bgm_vendor_vendor_id_uindex`(`vendor_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '供应商' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_vendor
-- ----------------------------
INSERT INTO `bgm_vendor` VALUES (1, '巴啦啦', '原材料供应商', '张三', '14567890111', '123456', '134@qq.com', '四川省成都市', '1233333', 1, 'admin', '555555', 5, 0, '备注', 1);
INSERT INTO `bgm_vendor` VALUES (2, '呢玲玲', '原材料供应商', '杨大拿', '11011912022', '123445', '412@163.com', '重庆大学城', '222222', 1, '采购员1', '666666', 2, 4, '备注2', 0);
INSERT INTO `bgm_vendor` VALUES (3, '重庆建设集团', '原材料供应商', '重建设', '1101123222', '23233', '6666@163.com', '重庆沙坪坝', '453535', 1, '采购人零庭', '55555', 5, 0, '偶是备注', 1);

-- ----------------------------
-- Table structure for bgm_vendor_account
-- ----------------------------
DROP TABLE IF EXISTS `bgm_vendor_account`;
CREATE TABLE `bgm_vendor_account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id\n',
  `vendor_id` int(0) NULL DEFAULT NULL,
  `account_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账户名称',
  `account_bank` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开户银行',
  `account_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '银行账号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `bgm_vendor_account_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '供应商账户信息\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_vendor_account
-- ----------------------------
INSERT INTO `bgm_vendor_account` VALUES (1, 1, '巴啦啦练习人:张3', '邮政银行', '51313123122');
INSERT INTO `bgm_vendor_account` VALUES (2, 1, '巴啦啦练习人:王五', '工商银行', '453132124554');
INSERT INTO `bgm_vendor_account` VALUES (3, 2, '呢玲玲联系人：屮艸芔茻', '中国银行', '67842334234');
INSERT INTO `bgm_vendor_account` VALUES (4, 3, '重建:熊大', '中国银行', '2222222222');
INSERT INTO `bgm_vendor_account` VALUES (5, 3, '重建:熊二', '农业银行', '333333333333');

-- ----------------------------
-- Table structure for bgm_vendor_contact
-- ----------------------------
DROP TABLE IF EXISTS `bgm_vendor_contact`;
CREATE TABLE `bgm_vendor_contact`  (
  `id` int(0) NOT NULL,
  `vendor_id` int(0) NULL DEFAULT NULL,
  `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人名字',
  `contact_post` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人职务',
  `contact_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人电画',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `bgm_vendor_ contact_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '联系人信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_vendor_contact
-- ----------------------------
INSERT INTO `bgm_vendor_contact` VALUES (1, 1, '巴啦啦练习人:张3', '经理', '12345678900');
INSERT INTO `bgm_vendor_contact` VALUES (2, 1, '巴啦啦练习人:王5', '财务', '12345678901');
INSERT INTO `bgm_vendor_contact` VALUES (3, 2, '呢玲玲联系人：屮艸芔茻', '经理3', '12345678902');
INSERT INTO `bgm_vendor_contact` VALUES (4, 3, '重建:李经理', '经理', '12323232232');
INSERT INTO `bgm_vendor_contact` VALUES (5, 3, '重建:王财务', '财务', '11321332332');

-- ----------------------------
-- Table structure for bgm_vendor_material
-- ----------------------------
DROP TABLE IF EXISTS `bgm_vendor_material`;
CREATE TABLE `bgm_vendor_material`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `vendor_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `supply_material_id` int(0) NULL DEFAULT NULL COMMENT '供应原材料id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `bgm_vendor_material_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '供应商供应材料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bgm_vendor_material
-- ----------------------------
INSERT INTO `bgm_vendor_material` VALUES (1, 1, 3);
INSERT INTO `bgm_vendor_material` VALUES (2, 1, 4);
INSERT INTO `bgm_vendor_material` VALUES (3, 2, 10);
INSERT INTO `bgm_vendor_material` VALUES (4, 2, 9);
INSERT INTO `bgm_vendor_material` VALUES (5, 3, 10);
INSERT INTO `bgm_vendor_material` VALUES (6, 3, 13);
INSERT INTO `bgm_vendor_material` VALUES (7, 3, 8);

SET FOREIGN_KEY_CHECKS = 1;
