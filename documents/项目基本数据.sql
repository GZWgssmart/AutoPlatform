/**
t_user表基本数据
*/
INSERT INTO `t_user` VALUES ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', 'xiaoqiang@qq.com', '18679976113', '6khXbzC+FmmXFpnAmtBclA==', 'xiaoqiang', '360734199709022413', '王小强', 'M', '1997-09-02', '安徽省/芜湖市/鸠江区', null, null, null, 'default.png', null, null, '0', '2017-04-26 10:15:55', null, 'Y');

/**
t_user_role表基本数据
*/
INSERT INTO `t_user_role` VALUE ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0VNM', '8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', 'ad381c0d-2497-11e7-8420-2c600cb87d10', NOW());

/**
t_role表基本数据
*/
INSERT INTO `t_role` VALUES ('019fa931-2498-11e7-8420-2c600cb87d10', 'companyAdmin', '汽修公司管理员', 'Y');
INSERT INTO `t_role` VALUES ('0b937b27-2499-11e7-8420-2c600cb87d10', 'companyRepertory', '汽修公司库管人员', 'Y');
INSERT INTO `t_role` VALUES ('3824a636-2499-11e7-8420-2c600cb87d10', 'carOwner', '车主', 'Y');
INSERT INTO `t_role` VALUES ('3a57ac4d-2498-11e7-8420-2c600cb87d10', 'companyReceive', '汽修公司接待员', 'Y');
INSERT INTO `t_role` VALUES ('4ee7f225-2498-11e7-8420-2c600cb87d10', 'companyArtificer', '汽修公司技师', 'Y');
INSERT INTO `t_role` VALUES ('854fee2f-2498-11e7-8420-2c600cb87d10', 'companySales', '汽修公司销售员', 'Y');
INSERT INTO `t_role` VALUES ('8d6f012f-249a-11e7-8420-2c600cb87d10', 'companyHumanManager', '汽修公司人事人员', 'Y');
INSERT INTO `t_role` VALUES ('ad381c0d-2497-11e7-8420-2c600cb87d10', 'systemSuperAdmin', '系统超级管理员', 'Y');
INSERT INTO `t_role` VALUES ('b6a58bdc-2498-11e7-8420-2c600cb87d10', 'companyAccounting', '汽修公司财务人员', 'Y');
INSERT INTO `t_role` VALUES ('d549db7c-2498-11e7-8420-2c600cb87d10', 'companyBuyer', '汽修公司采购员', 'Y');
INSERT INTO `t_role` VALUES ('f0f999ab-2497-11e7-8420-2c600cb87d10', 'systemOrdinaryAdmin', '系统普通管理员', 'Y');
INSERT INTO `t_role` VALUES ('fba55f1e-265b-11e7-bfbf-2c600cb87d10', 'companyEmp', '汽修普通员工', 'Y');
