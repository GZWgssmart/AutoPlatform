/**
t_user���������
*/
INSERT INTO `t_user` VALUES ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', 'xiaoqiang@qq.com', '18679976113', '6khXbzC+FmmXFpnAmtBclA==', 'xiaoqiang', '360734199709022413', '��Сǿ', 'M', '1997-09-02', '����ʡ/�ߺ���/𯽭��', null, null, null, 'default.png', null, null, '0', '2017-04-26 10:15:55', null, 'Y');

/**
t_user_role���������
*/
INSERT INTO `t_user_role` VALUE ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0VNM', '8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', 'ad381c0d-2497-11e7-8420-2c600cb87d10', NOW());

/**
t_role���������
*/
INSERT INTO `t_role` VALUES ('019fa931-2498-11e7-8420-2c600cb87d10', 'companyAdmin', '���޹�˾����Ա', 'Y');
INSERT INTO `t_role` VALUES ('0b937b27-2499-11e7-8420-2c600cb87d10', 'companyRepertory', '���޹�˾�����Ա', 'Y');
INSERT INTO `t_role` VALUES ('3824a636-2499-11e7-8420-2c600cb87d10', 'carOwner', '����', 'Y');
INSERT INTO `t_role` VALUES ('3a57ac4d-2498-11e7-8420-2c600cb87d10', 'companyReceive', '���޹�˾�Ӵ�Ա', 'Y');
INSERT INTO `t_role` VALUES ('4ee7f225-2498-11e7-8420-2c600cb87d10', 'companyArtificer', '���޹�˾��ʦ', 'Y');
INSERT INTO `t_role` VALUES ('854fee2f-2498-11e7-8420-2c600cb87d10', 'companySales', '���޹�˾����Ա', 'Y');
INSERT INTO `t_role` VALUES ('8d6f012f-249a-11e7-8420-2c600cb87d10', 'companyHumanManager', '���޹�˾������Ա', 'Y');
INSERT INTO `t_role` VALUES ('ad381c0d-2497-11e7-8420-2c600cb87d10', 'systemSuperAdmin', 'ϵͳ��������Ա', 'Y');
INSERT INTO `t_role` VALUES ('b6a58bdc-2498-11e7-8420-2c600cb87d10', 'companyAccounting', '���޹�˾������Ա', 'Y');
INSERT INTO `t_role` VALUES ('d549db7c-2498-11e7-8420-2c600cb87d10', 'companyBuyer', '���޹�˾�ɹ�Ա', 'Y');
INSERT INTO `t_role` VALUES ('f0f999ab-2497-11e7-8420-2c600cb87d10', 'systemOrdinaryAdmin', 'ϵͳ��ͨ����Ա', 'Y');
INSERT INTO `t_role` VALUES ('fba55f1e-265b-11e7-bfbf-2c600cb87d10', 'companyEmp', '������ͨԱ��', 'Y');
