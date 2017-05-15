﻿CREATE DATABASE IF NOT EXISTS auto_platform DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `auto_platform`;

/*配件表*/

DROP TABLE IF EXISTS `t_accessories`;

CREATE TABLE `t_accessories` (
`accId` varchar(36) NOT NULL,
`accName` varchar(100) NOT NULL COMMENT '配件名称',
`accCommodityCode` varchar(100) NOT NULL COMMENT '配件商品条码',
`accDes` varchar(500) DEFAULT NULL COMMENT '配件描述',
`accPrice` double(255,2) NOT NULL COMMENT '配件价格',
`accSalePrice` double(255,2) NOT NULL COMMENT '配件售价',
`accUnit` varchar(10) DEFAULT NULL COMMENT '配件计量单位',
`accTotal` int(11) NOT NULL COMMENT '配件数量',
`accIdle` int(11) NOT NULL COMMENT '配件可用数量',
`accUsedTime` datetime DEFAULT NULL COMMENT '配件的最近一次领料时间',
`accBuyedTime` datetime DEFAULT NULL COMMENT '配件的最近一次购买时间',
`supplyId` varchar(36) DEFAULT NULL COMMENT '配件供应商编号，来源于t_supply表',
`accCreatedTime` datetime DEFAULT NULL COMMENT '配件创建时间',
`accTypeId` varchar(36) DEFAULT NULL COMMENT '配件所属分类编号，来源于t_accessories_type表',
`companyId` varchar(36) DEFAULT NULL COMMENT '配件所属公司，来源于t_company表',
`accStatus` varchar(2) DEFAULT NULL COMMENT '配件状态，Y表示可用，N表示不可用',
PRIMARY KEY (`accId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*配件采购表*/

DROP TABLE IF EXISTS `t_accessories_buy`;

CREATE TABLE `t_accessories_buy` (
`accBuyId` varchar(36) NOT NULL COMMENT '配件采购编号',
`accId` varchar(36) DEFAULT NULL COMMENT '配件编号',
`accUnit` varchar(10) DEFAULT NULL COMMENT '配件计量单位',
`accBuyCount` int(255) NOT NULL COMMENT '配件购买数量',
`accBuyPrice` double(255,2) NOT NULL COMMENT '配件购买单价',
`accBuyTotal` double(255,2) NOT NULL COMMENT '配件购买总价',
`accBuyDiscount` double(255,2) NOT NULL COMMENT '配件购买折扣',
`accBuyMoney` double(255,2) NOT NULL COMMENT '配件购买最终价',
`accBuyTime` datetime NOT NULL COMMENT '配件购买时间',
`accBuyCreatedTime` datetime DEFAULT NULL COMMENT '配件购买记录创建时间',
`companyId` varchar(36) DEFAULT NULL COMMENT '配件购买记录所属公司',
`accBuyStatus` varchar(2) DEFAULT NULL COMMENT '配件购买记录状态',
`accBuyCheck` varchar(2) DEFAULT 'N' COMMENT '配件购买审核状态',
PRIMARY KEY (`accBuyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*配件销售表*/

DROP TABLE IF EXISTS `t_accessories_sale`;

CREATE TABLE `t_accessories_sale` (
`accSaleId` varchar(36) NOT NULL COMMENT '配件销售编号',
`accId` varchar(36) DEFAULT NULL COMMENT '配件编号',
`userId` varchar(36) DEFAULT NULL COMMENT '来源于t_user表',
`userName` varchar(100) DEFAULT NULL COMMENT '用户姓名',
`userPhone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
`accSaledTime` datetime NOT NULL COMMENT '配件销售时间',
`accSaleCount` int(255) NOT NULL COMMENT '配件销售数量',
`accSalePrice` double(255,2) NOT NULL COMMENT '配件销售单价',
`accSaleTotal` double(255,2) NOT NULL COMMENT '配件销售总价',
`accSaleDiscount` double(255,2) NOT NULL COMMENT '配件销售折扣',
`accSaleMoney` double(255,2) NOT NULL COMMENT '配件销售最终价',
`accSaleCreatedTime` datetime DEFAULT NULL COMMENT '配件销售记录创建时间',
`companyId` varchar(36) DEFAULT NULL COMMENT '配件销售记录所属公司',
`accSaleStatus` varchar(2) DEFAULT NULL COMMENT '配件销售记录状态',
PRIMARY KEY (`accSaleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*配件分类表*/

DROP TABLE IF EXISTS `t_accessories_type`;

CREATE TABLE `t_accessories_type` (
`accTypeId` varchar(36) NOT NULL COMMENT '配件分类编号，UUID,主键',
`accTypeName` varchar(50) DEFAULT NULL COMMENT '配件分类名称',
`accTypeDes` varchar(500) DEFAULT NULL COMMENT '配件分类描述',
`companyId` varchar(36) DEFAULT NULL COMMENT '配件分类所属公司，来源于t_company表',
`accTypeStatus` varchar(2) DEFAULT NULL COMMENT '配件分类状态，Y表示可用，N表示不可用',
PRIMARY KEY (`accTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*预约表*/

DROP TABLE IF EXISTS `t_appointment`;
CREATE TABLE `t_appointment` (
`appointmentId` varchar(36) NOT NULL DEFAULT '主键',
`userId` varchar(36) DEFAULT NULL COMMENT '用户编号',
`userName` varchar(100) NOT NULL COMMENT '车主姓名',
`userPhone` varchar(11) NOT NULL COMMENT '车主电话',
`brandId` varchar(36) DEFAULT NULL COMMENT '汽车品牌编号',
`colorId` varchar(36) DEFAULT NULL COMMENT '汽车颜色编号',
`modelId` varchar(36) DEFAULT NULL COMMENT '汽车车型编号',
`plateId` varchar(36) NOT NULL COMMENT '汽车车牌编号',
`carPlate` varchar(20) NOT NULL COMMENT '汽车车牌',
`arriveTime` datetime NOT NULL COMMENT '预估到店时间',
`maintainOrFix` varchar(20) NOT NULL COMMENT '标识是保养还是维修',
`appCreatedTime` datetime NOT NULL COMMENT '预约记录创建时间',
`companyId` varchar(36) NOT NULL COMMENT '汽修公司编号',
`appoitmentStatus` varchar(2) DEFAULT NULL COMMENT '预约状态',
`speedStatus` varchar(10) NOT NULL COMMENT '标识这条记录当前的进度，如预约中，登记完',
PRIMARY KEY (`appointmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*汽车品牌表*/

DROP TABLE IF EXISTS `t_car_brand`;

CREATE TABLE `t_car_brand` (
`brandId` varchar(36) NOT NULL COMMENT '汽车品牌编号',
`brandName` varchar(20) NOT NULL COMMENT '汽车品牌名称',
`brandDes` varchar(500) DEFAULT NULL COMMENT '汽车品牌描述',
`brandStatus` varchar(2) DEFAULT NULL COMMENT '汽车品牌状态',
PRIMARY KEY (`brandId`),
UNIQUE KEY `brandName` (`brandName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*汽车颜色表*/

DROP TABLE IF EXISTS `t_car_color`;

CREATE TABLE `t_car_color` (
`colorId` varchar(36) NOT NULL COMMENT '颜色编号',
`colorName` varchar(20) NOT NULL COMMENT '颜色名称',
`colorRGB` varchar(20) DEFAULT NULL COMMENT '颜色RGB',
`colorHex` varchar(10) DEFAULT NULL COMMENT '颜色16进制',
`colorDes` varchar(500) DEFAULT NULL COMMENT '颜色描述',
`colorStatus` varchar(2) DEFAULT NULL COMMENT '颜色状态',
PRIMARY KEY (`colorId`),
UNIQUE KEY `colorName` (`colorName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*汽车车型表*/

DROP TABLE IF EXISTS `t_car_model`;

CREATE TABLE `t_car_model` (
`modelId` varchar(36) NOT NULL COMMENT '车型ID',
`modelName` varchar(50) NOT NULL COMMENT '车型名称',
`modelDes` varchar(500) DEFAULT NULL COMMENT '车型描述',
`brandId` varchar(36) DEFAULT NULL COMMENT '车型品牌',
`modelStatus` varchar(2) DEFAULT NULL COMMENT '车型状态',
PRIMARY KEY (`modelId`),
UNIQUE KEY `modelName` (`modelName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*汽车车牌表*/

DROP TABLE IF EXISTS `t_car_plate`;

CREATE TABLE `t_car_plate` (
`plateId` varchar(36) NOT NULL COMMENT '车牌编号',
`plateName` varchar(10) NOT NULL COMMENT '车牌名称',
`plateDes` varchar(500) DEFAULT NULL COMMENT '车牌描述',
`plateStatus` varchar(2) DEFAULT NULL COMMENT '车牌状态',
PRIMARY KEY (`plateId`),
UNIQUE KEY `plateName` (`plateName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*收费单据表*/
DROP TABLE IF EXISTS `t_charge_bill`;
CREATE TABLE `t_charge_bill` (
  `chargeBillId` varchar(36) NOT NULL COMMENT '收费单据编号，UUID,主键',
  `recordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
  `chargeBillMoney` double(255,2) DEFAULT NULL COMMENT '收费总金额',
  `paymentMethod` varchar(20) DEFAULT NULL COMMENT '付款方式',
  `actualPayment` double(255,2) DEFAULT NULL COMMENT '实付款',
  `chargeTime` datetime DEFAULT NULL COMMENT '收费时间',
  `chargeCreatedTime` datetime DEFAULT NULL COMMENT '收费单据创建时间',
  `companyId` varchar(36) DEFAULT NULL COMMENT '公司的id，来源于t_company表',
  `chargeBillDes` varchar(500) DEFAULT NULL COMMENT '收费单据描述',
  `chargeBillStatus` varchar(2) DEFAULT NULL COMMENT '收费状态,Y表示可用，N表示不可用',
  PRIMARY KEY (`chargeBillId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*登记表*/

DROP TABLE IF EXISTS `t_checkin`;

CREATE TABLE `t_checkin` (
`checkinId` varchar(36) NOT NULL COMMENT '登记编号，UUID,主键',
`userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表，可为空，当为空时，表示非注册车主用户登记',
`appointmentId` varchar(36) DEFAULT NULL COMMENT '预约编号，来源于t_appointment表，可为空，当为空时，表示未预约过',
`userName` varchar(100) DEFAULT NULL COMMENT '车主姓名',
`userPhone` varchar(11) DEFAULT NULL COMMENT '车主电话',
`brandId` varchar(36) DEFAULT NULL COMMENT '汽车品牌编号',
`colorId` varchar(36) DEFAULT NULL COMMENT '汽车颜色编号',
`modelId` varchar(36) DEFAULT NULL COMMENT '汽车车型编号',
`plateId` varchar(36) DEFAULT NULL COMMENT '汽车车牌编号',
`carPlate` varchar(20) NOT NULL COMMENT '汽车车牌，not null',
`arriveTime` datetime NOT NULL COMMENT '到店时间，not null',
`oilCount` double(255,2) DEFAULT NULL COMMENT '油量',
`carWash` varchar(2) DEFAULT NULL COMMENT '用于标识是否需要洗车，默认是N，需要则是Y',
`carMileage` double(255,2) DEFAULT NULL COMMENT '汽车行驶里程',
`carThings` varchar(500) DEFAULT NULL COMMENT '车上物品描述',
`intactDegrees` varchar(500) DEFAULT NULL COMMENT '汽车完好度描述',
`userRequests` varchar(500) DEFAULT NULL COMMENT '用户要求描述',
`maintainOrFix` varchar(20) DEFAULT NULL COMMENT '标识是保养还是维修',
`checkinCreatedTime` datetime DEFAULT NULL COMMENT '登记记录创建的时间',
`companyId` varchar(36) DEFAULT NULL COMMENT '汽修公司编号，来源于t_company表',
`checkinStatus` varchar(255) DEFAULT NULL COMMENT '登记记录状态，Y表示可用，N表示不可用',
PRIMARY KEY (`checkinId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*公司表*/

DROP TABLE IF EXISTS `t_company`;

CREATE TABLE `t_company` (
`companyId` varchar(36) NOT NULL COMMENT '公司id',
`companyName` varchar(100) NOT NULL COMMENT '公司名称',
`companyAddress` varchar(150) NOT NULL COMMENT '公司地址',
`companyTel` varchar(13) NOT NULL COMMENT '公司联系方式',
`companyPricipal` varchar(20) DEFAULT NULL COMMENT '公司负责人',
`companyWebsite` varchar(100) DEFAULT NULL COMMENT '公司官网URL',
`companyLogo` varchar(200) DEFAULT '/img/company_logo.png' COMMENT '公司logo图片',
`companyOpenDate` date DEFAULT NULL COMMENT '公司成立时间',
`companySize` varchar(50) DEFAULT NULL COMMENT '公司规模',
`companyLongitude` double(255,2) DEFAULT NULL COMMENT '公司经度',
`companyLatitude` double(255,2) DEFAULT NULL COMMENT '公司纬度',
`companyDes` varchar(500) DEFAULT NULL COMMENT '公司描述',
`companyStatus` varchar(2) DEFAULT NULL COMMENT '公司状态',
PRIMARY KEY (`companyId`),
UNIQUE KEY `companyName` (`companyName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*投诉表*/

DROP TABLE IF EXISTS `t_complaint`;

CREATE TABLE `t_complaint` (
`complaintId` varchar(36) NOT NULL COMMENT '投诉编号，UUID,主键',
`userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
`complaintContent` varchar(500) DEFAULT NULL COMMENT '投诉内容',
`complaintCreatedTime` datetime DEFAULT NULL COMMENT '投诉时间',
`complaintReply` varchar(500) DEFAULT NULL COMMENT '投诉回复内容',
`complaintReplyTime` datetime DEFAULT NULL COMMENT '投诉回复时间',
`companyId` varchar(36) DEFAULT NULL COMMENT '所属公司，来源于t_company表',
`complaintReplyUser` varchar(36) DEFAULT NULL COMMENT '投诉回复人，来源于t_user表',
PRIMARY KEY (`complaintId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*收支记录表*/

DROP TABLE IF EXISTS `t_incoming_outgoing`;

CREATE TABLE `t_incoming_outgoing` (
`inOutId` varchar(36) NOT NULL COMMENT '收支记录编号',
`inTypeId` varchar(36) DEFAULT NULL COMMENT '收入类型编号，来源于t_incoming_type表',
`outTypeId` varchar(36) DEFAULT NULL COMMENT '支出类型编号，来源于t_outgoing_type表',
`inOutMoney` double(255,2) DEFAULT NULL COMMENT '收支金额',
`inOutCreatedUser` varchar(36) DEFAULT NULL COMMENT '收支记录创建人，来源于t_user表',
`inOutCreatedTime` datetime DEFAULT NULL COMMENT '收支记录创建时间',
`companyId` varchar(36) DEFAULT NULL COMMENT '所属公司，来源于t_company表',
`inOutStatus` varchar(2) DEFAULT NULL COMMENT '收支记录状态，Y表示可用，N表示不可用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*收入类型表*/

DROP TABLE IF EXISTS `t_incoming_type`;

CREATE TABLE `t_incoming_type` (
`inTypeId` varchar(36) NOT NULL COMMENT '收入类型编号',
`inTypeName` varchar(20) DEFAULT NULL COMMENT '收人类型名称',
`companyId` varchar(36) DEFAULT NULL COMMENT '所属公司，来源于t_company表',
`inTypeStatus` varchar(2) DEFAULT NULL COMMENT '收人类型状态，Y表示可用，N表示不可用',
`inTypeCreatedTime` datetime DEFAULT NULL COMMENT '收人类型创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*维修保养明细表*/

DROP TABLE IF EXISTS `t_maintain_detail`;
CREATE TABLE `t_maintain_detail` (
`detailId` varchar(36) NOT NULL COMMENT '维修保养明细编号，UUID,主键',
`recordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
`maintainId` varchar(36) DEFAULT NULL COMMENT '维修保养项目编号，来源于t_maintain_fix表，可为空',
`maintainDiscount` double(255,2) DEFAULT NULL COMMENT '维修保养项目折扣，default 0,可选择折扣，也可选择减价',
`detailCreatedTime` datetime DEFAULT NULL COMMENT '维修保养明细创建时间',
PRIMARY KEY (`detailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*维修保养项目表*/

DROP TABLE IF EXISTS `t_maintain_fix`;
CREATE TABLE `t_maintain_fix` (
`maintainId` varchar(36) NOT NULL COMMENT '维修保养项目编号',
`maintainName` varchar(50) NOT NULL COMMENT '维修保养项目名称',
`maintainHour` double(255,2) NOT NULL COMMENT '维修保养项目工时',
`maintainMoney` double(255,2) NOT NULL COMMENT '维修保养项目基础费用',
`maintainManHourFee` double(255,2) NOT NULL COMMENT '维修保养项目工时费',
`maintainOrFix` varchar(20) DEFAULT NULL COMMENT '标识是保养还是维修',
`maintainDes` varchar(500) DEFAULT NULL COMMENT '维修保养项目描述',
`companyId` varchar(36) DEFAULT NULL COMMENT '维修保养项目所属公司，来源于t_company表',
`maintainStatus` varchar(2) DEFAULT NULL COMMENT '维修保养项目状态',
PRIMARY KEY (`maintainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*维修保养项目配件关联表*/

DROP TABLE IF EXISTS `t_maintain_fix_acc`;

CREATE TABLE `t_maintain_fix_acc` (
`mainAccId` varchar(36) NOT NULL COMMENT '保养项目配件编号',
`maintainId` varchar(36) DEFAULT NULL COMMENT '保养项目编号',
`accId` varchar(36) DEFAULT NULL COMMENT '配件编号',
`accCount` int(11) DEFAULT NULL COMMENT '配件个数',
PRIMARY KEY (`mainAccId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*维修保养记录表*/

DROP TABLE IF EXISTS `t_maintain_record`;
CREATE TABLE `t_maintain_record` (
`recordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，UUID,主键',
`checkinId` varchar(36) DEFAULT NULL COMMENT '维修保养登记编号，来源于t_checkin表',
`startTime` datetime DEFAULT NULL COMMENT '维修保养开始时间',
`endTime` datetime DEFAULT NULL COMMENT '维修保养预估结束时间',
`actualEndTime` datetime DEFAULT NULL COMMENT '维修保养实际结束时间',
`recordCreatedTime` datetime DEFAULT NULL COMMENT '维修保养记录创建时间',
`trackStatus` varchar(2) DEFAULT NULL COMMENT '是否回访，默认是N，Y表示已经回访',
`pickupTime` datetime DEFAULT NULL COMMENT '维修保养结束车主提车时间',
`companyId` varchar(36) DEFAULT NULL COMMENT '来源于t_company表公司Id',
`recordDes` varchar(500) DEFAULT NULL COMMENT '维修保养记录描述',
`recordStatus` varchar(2) DEFAULT NULL COMMENT '维修保养记录状态，Y表示可用，N表示不可用',
`speedStatus` varchar(10) NOT NULL COMMENT '标识这条记录的当前进度，如登记完，维修中或保养中，待结算，已完成'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*维修保养提醒记录表*/

DROP TABLE IF EXISTS `t_maintain_remind`;

CREATE TABLE `t_maintain_remind` (
`remindId` varchar(36) NOT NULL COMMENT '保养提醒记录编号，UUID,主键',
`userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
`lastMaintainTime` datetime DEFAULT NULL COMMENT '上次保养时间',
`lastMaintainMileage` double(255,2) DEFAULT NULL COMMENT '上次保养汽车行驶里程',
`remindMsg` varchar(255) DEFAULT NULL COMMENT '保养提醒消息',
`remindTime` datetime DEFAULT NULL COMMENT '保养提醒时间',
`remindType` varchar(20) DEFAULT NULL COMMENT '保养提醒方式，如邮箱，手机短信。默认使用手机短信方式发送提醒',
`companyId` varchar(36) DEFAULT NULL COMMENT '所属公司，来源于t_company表',
`remindCreatedTime` datetime DEFAULT NULL COMMENT '保养提醒记录创建时间',
PRIMARY KEY (`remindId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*物料清单表*/

DROP TABLE IF EXISTS `t_material_list`;

CREATE TABLE `t_material_list` (
`materialId` varchar(36) NOT NULL COMMENT '物料清单记录编号，UUID,主键',
`recordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
`accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
`materialCount` int(11) DEFAULT NULL COMMENT '物料数量',
`materialCreatedTime` datetime DEFAULT NULL COMMENT '物料清单记录创建时间',
`materialStatus` varchar(2) DEFAULT NULL COMMENT '物料清单状态，Y表示可用，N表示不可用',
PRIMARY KEY (`materialId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*退料信息表*/

DROP TABLE IF EXISTS `t_material_return`;

CREATE TABLE `t_material_return` (
`materialReturnId` varchar(36) NOT NULL COMMENT '退料记录编号，UUID,主键',
`recordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
`accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
`accCount` int(11) DEFAULT NULL COMMENT '退料数量',
`mrCreatedDate` datetime DEFAULT NULL COMMENT '退料记录创建时间',
`mrReturnDate` datetime DEFAULT NULL COMMENT '退料时间',
PRIMARY KEY (`materialReturnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*领料信息表*/

DROP TABLE IF EXISTS `t_material_use`;

CREATE TABLE `t_material_use` (
`materialUseId` varchar(36) NOT NULL COMMENT '领料记录编号，UUID,主键',
`recordId` varchar(36) DEFAULT NULL COMMENT '维修保养记录编号，来源于t_maintain_record表',
`accId` varchar(36) DEFAULT NULL COMMENT '配件编号，来源于t_accessories表',
`accCount` int(11) DEFAULT NULL COMMENT '领料数量',
`muCreatedTime` datetime DEFAULT NULL COMMENT '领料记录创建时间',
`muUseDate` datetime DEFAULT NULL COMMENT '领料时间',
PRIMARY KEY (`materialUseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*短信发送记录表*/

DROP TABLE IF EXISTS `t_message_send`;

CREATE TABLE `t_message_send` (
`messageId` varchar(36) NOT NULL COMMENT '短信发送记录编号，UUID,主键',
`userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
`sendMsg` varchar(500) DEFAULT NULL COMMENT '短信发送内容',
`sendTime` datetime DEFAULT NULL COMMENT '短信发送时间',
`companyId` varchar(36) DEFAULT NULL COMMENT '所属公司，来源于t_company表',
`sendCreatedTime` datetime DEFAULT NULL COMMENT '短信发送记录创建时间',
PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*模块表*/

DROP TABLE IF EXISTS `t_module`;

CREATE TABLE `t_module` (
`moduleId` varchar(36) NOT NULL COMMENT '模块id',
`moduleName` varchar(20) NOT NULL COMMENT '模块名称',
`moduleDes` varchar(500) DEFAULT NULL COMMENT '模块描述',
`moduleStatus` varchar(2) DEFAULT 'Y' COMMENT '模块状态',
PRIMARY KEY (`moduleId`),
UNIQUE KEY `moduleName` (`moduleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*支出类型表*/

DROP TABLE IF EXISTS `t_outgoing_type`;

CREATE TABLE `t_outgoing_type` (
`outTypeId` varchar(36) NOT NULL COMMENT '支出类型编号',
`outTypeName` varchar(20) DEFAULT NULL COMMENT '支出类型名称',
`outTypeStatus` varchar(2) DEFAULT NULL COMMENT '支出状态，Y表示可用，N表示不可用',
`companyId` varchar(36) DEFAULT NULL COMMENT '所属公司，来源于t_company表',
`outTypeCreatedTime` datetime DEFAULT NULL COMMENT '支出类型创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*权限表*/

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
`permissionId` varchar(36) NOT NULL COMMENT '权限id',
`permissionName` varchar(30) NOT NULL COMMENT '权限名称',
`permissionZHName` varchar(50) DEFAULT NULL COMMENT '权限中文名称',
`permissionDes` varchar(500) DEFAULT NULL COMMENT '权限描述',
`moduleId` varchar(36) NOT NULL COMMENT '模块id',
`permissionStatus` varchar(2) DEFAULT 'Y' COMMENT '权限状态',
PRIMARY KEY (`permissionId`),
UNIQUE KEY `permissionName` (`permissionName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*角色表*/

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
`roleId` varchar(36) NOT NULL COMMENT '角色id',
`roleName` varchar(20) NOT NULL COMMENT '角色名称',
`roleDes` varchar(500) DEFAULT NULL COMMENT '角色描述',
`roleStatus` varchar(2) DEFAULT 'Y' COMMENT '角色状态',
PRIMARY KEY (`roleId`),
UNIQUE KEY `roleName` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*权限角色表*/

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
`rpId` varchar(36) NOT NULL COMMENT '角色权限id',
`roleId` varchar(36) DEFAULT NULL COMMENT '角色id',
`permissionId` varchar(36) DEFAULT NULL COMMENT '权限id',
`rpCreatedTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '分配时间',
PRIMARY KEY (`rpId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*工资发放表*/

DROP TABLE IF EXISTS `t_salary`;

CREATE TABLE `t_salary` (
`salaryId` varchar(36) NOT NULL COMMENT '工资发放编号',
`userId` varchar(36) NOT NULL COMMENT '用户编号，来源于t_user表',
`prizeSalary` double(255,2) DEFAULT NULL COMMENT '奖金',
`minusSalary` double(255,2) DEFAULT NULL COMMENT '罚款',
`totalSalary` double(255,2) DEFAULT NULL COMMENT '总工资',
`salaryDes` varchar(500) DEFAULT NULL COMMENT '工资发放描述',
`salaryTime` datetime DEFAULT NULL COMMENT '工资发放时间',
`salaryCreatedTime` datetime DEFAULT NULL COMMENT '工资发放创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*供应商表*/

DROP TABLE IF EXISTS `t_supply`;

CREATE TABLE `t_supply` (
`supplyId` varchar(36) NOT NULL COMMENT '供应商编号',
`supplyName` varchar(100) NOT NULL COMMENT '供应商名称',
`supplyTel` varchar(11) NOT NULL COMMENT '供应商联系电话',
`supplyPricipal` varchar(20) DEFAULT NULL COMMENT '供应商负责人',
`supplyAddress` varchar(150) NOT NULL COMMENT '供应商地址',
`supplyBank` varchar(30) DEFAULT NULL COMMENT '供应商开户银行全称',
`supplyBankAccount` varchar(20) DEFAULT NULL COMMENT '供应商开户人姓名',
`supplyBankNo` varchar(50) DEFAULT NULL COMMENT '供应商开户卡号',
`supplyAlipay` varchar(100) DEFAULT NULL COMMENT '供应商支付宝',
`supplyWechat` varchar(50) DEFAULT NULL COMMENT '供应商微信号',
`supplyCreatedTime` datetime DEFAULT NULL COMMENT '供应商创建时间',
`supplyTypeId` varchar(36) NOT NULL COMMENT '供应商分类编号',
`companyId` varchar(36) NOT NULL COMMENT '供应商所属公司',
`supplyStatus` varchar(2) DEFAULT NULL COMMENT '供应商状态',
PRIMARY KEY (`supplyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*供应商分类表*/

DROP TABLE IF EXISTS `t_supply_type`;

CREATE TABLE `t_supply_type` (
`supplyTypeId` varchar(36) NOT NULL COMMENT '供应商分类编号',
`supplyTypeName` varchar(100) NOT NULL COMMENT '供应商分类名称',
`supplyTypeDes` varchar(500) DEFAULT NULL COMMENT '供应商分类描述',
`companyId` varchar(36) NOT NULL COMMENT '供应商分类所属公司',
`supplyTypeStatus` varchar(2) DEFAULT NULL COMMENT '供应商分类状态',
PRIMARY KEY (`supplyTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*跟踪回访表*/


DROP TABLE IF EXISTS `t_track_list`;
CREATE TABLE `t_track_list` (
`trackId` varchar(36) NOT NULL COMMENT '跟踪回访编号，UUID,主键',
`userId` varchar(36) DEFAULT NULL COMMENT '用户编号，来源于t_user表',
`trackContent` varchar(500) DEFAULT NULL COMMENT '回访的问题',
`serviceEvaluate` int(255) DEFAULT NULL COMMENT '本次服务评价,1-10分',
`trackUser` varchar(36) DEFAULT NULL COMMENT '跟踪回访用户，来源于t_user表',
`companyId` varchar(36) DEFAULT NULL COMMENT '所属公司，来源于t_company表',
`trackCreatedTime` datetime DEFAULT NULL COMMENT '跟踪回访创建时间',
PRIMARY KEY (`trackId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*用户表*/

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
`userId` varchar(36) NOT NULL COMMENT '用户id',
`userEmail` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
`userPhone` varchar(11) NOT NULL COMMENT '用户手机号',
`userPwd` varchar(100) NOT NULL COMMENT '用户登录密码',
`userNickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
`userIdentity` varchar(18) DEFAULT NULL COMMENT '用户身份证号',
`userName` varchar(20) DEFAULT NULL COMMENT '用户真实姓名',
`userGender` varchar(2) DEFAULT NULL COMMENT '用户性别',
`userBirthday` date DEFAULT NULL COMMENT '用户生日',
`userAddress` varchar(150) DEFAULT NULL COMMENT '用户地址',
`qqOpenId` varchar(100) DEFAULT NULL COMMENT 'QQ open id',
`weiboOpenId` varchar(100) DEFAULT NULL COMMENT '微博open id',
`wechatOpenId` varchar(100) DEFAULT NULL COMMENT '微信open id',
`userIcon` varchar(200) DEFAULT '/img/default.png' COMMENT '用户头像',
`userDes` varchar(500) DEFAULT NULL COMMENT '用户描述',
`companyId` varchar(36) DEFAULT NULL COMMENT '用户所属公司',
`userSalary` double(255,2) DEFAULT NULL COMMENT '用户基本工资',
`userCreatedTime` datetime DEFAULT NULL COMMENT '用户创建时间',
`userLoginedTime` datetime DEFAULT NULL COMMENT '用户最近一次登录时间',
`userStatus` varchar(2) DEFAULT 'Y' COMMENT '用户状态',
PRIMARY KEY (`userId`),
UNIQUE KEY `userPhone` (`userPhone`),
UNIQUE KEY `userEmail` (`userEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*用户角色表*/

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
`userRoleId` varchar(36) NOT NULL COMMENT '用户角色id',
`userId` varchar(36) DEFAULT NULL COMMENT '用户id',
`roleId` varchar(36) DEFAULT NULL COMMENT '角色id',
`urCreatedTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户角色分配时间',
PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*工单信息表*/

DROP TABLE IF EXISTS `t_work_info`;

CREATE TABLE `t_work_info` (
`workId` varchar(36) NOT NULL COMMENT '工单编号，UUID,主键',
`recordId` varchar(36) NOT NULL COMMENT '维修保养记录编号',
`userId` varchar(36) DEFAULT NULL COMMENT '工单指派的用户编号，来源于t_user表',
`workAssignTime` datetime DEFAULT NULL COMMENT '工单指派时间',
`workCreatedTime` datetime DEFAULT NULL COMMENT '工单创建时间',
`workStatus` varchar(2) DEFAULT 'Y' COMMENT '当前状态,Y表示可用，N表示不可用',
PRIMARY KEY (`workId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
