/**
t_user���������
*/
INSERT INTO `t_user` VALUES ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', '847315251@.qq.com', '13672297775', '6khXbzC+FmmXFpnAmtBclA==', '��ţ', '360735199503242117', '������', 'M', '1995-03-24', '����ʡ/������/�¹���', null, null, null, '/img/default.png', null, null, '0', '2017-04-26 10:15:55', null, 'Y');

/**
t_user_role���������
*/
INSERT INTO `t_user_role` VALUE ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0VNM', '8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', 'ad381c0d-2497-11e7-8420-2c600cb87d10', NOW());

/**
t_role���������
*/
INSERT INTO `t_role` VALUES ('019fa931-2498-11e7-8420-2c600cb87d10', 'companyAdmin', '���³�', 'Y');
INSERT INTO `t_role` VALUES ('0b937b27-2499-11e7-8420-2c600cb87d10', 'companyRepertory', '���', 'Y');
INSERT INTO `t_role` VALUES ('3a57ac4d-2498-11e7-8420-2c600cb87d10', 'companyReceive', '�Ӵ�Ա', 'Y');
INSERT INTO `t_role` VALUES ('4ee7f225-2498-11e7-8420-2c600cb87d10', 'companyArtificer', '��ʦ', 'Y');
INSERT INTO `t_role` VALUES ('854fee2f-2498-11e7-8420-2c600cb87d10', 'companySales', '����Ա', 'Y');
INSERT INTO `t_role` VALUES ('8d6f012f-249a-11e7-8420-2c600cb87d10', 'companyHumanManager', '���¹���Ա', 'Y');
INSERT INTO `t_role` VALUES ('b6a58bdc-2498-11e7-8420-2c600cb87d10', 'companyAccounting', '����', 'Y');
INSERT INTO `t_role` VALUES ('fba55f1e-265b-11e7-bfbf-2c600cb87d10', 'companyEmp', '��ͨԱ��', 'Y');
INSERT INTO `t_role` VALUES ('d549db7c-2498-11e7-8420-2c600cb87d10', 'companyBuyer', '�ɹ�Ա', 'Y');
INSERT INTO `t_role` VALUES ('3824a636-2499-11e7-8420-2c600cb87d10', 'carOwner', '����', 'Y');
INSERT INTO `t_role` VALUES ('ad381c0d-2497-11e7-8420-2c600cb87d10', 'systemSuperAdmin', '��������Ա', 'Y');
INSERT INTO `t_role` VALUES ('f0f999ab-2497-11e7-8420-2c600cb87d10', 'systemOrdinaryAdmin', '��ͨ����Ա', 'Y');


/**
t_car_brand���������
*/
INSERT INTO `t_car_brand` VALUES ('0808e5d0-31fc-11e7-8957-f8a9632663b3', '����', '����', 'Y');
INSERT INTO `t_car_brand` VALUES ('1a835893-31fc-11e7-8957-f8a9632663b3', '�ղ�', '�����ղ�', 'Y');
INSERT INTO `t_car_brand` VALUES ('2b77dba1-31fc-11e7-8957-f8a9632663b3', '�µ�', '�µ�', 'Y');
INSERT INTO `t_car_brand` VALUES ('342b6b93-31fc-11e7-8957-f8a9632663b3', 'ѩ����', 'ѩ����', 'Y');
INSERT INTO `t_car_brand` VALUES ('3f04171b-31fc-11e7-8957-f8a9632663b3', '�ִ�', '�����ִ�', 'Y');
INSERT INTO `t_car_brand` VALUES ('47bc70ac-31fc-11e7-8957-f8a9632663b3', '���', '���', 'Y');
INSERT INTO `t_car_brand` VALUES ('4e7ea042-31fc-11e7-8957-f8a9632663b3', '����', '����', 'Y');
INSERT INTO `t_car_brand` VALUES ('54c16e99-31fc-11e7-8957-f8a9632663b3', '���ǵ�', 'BYD', 'Y');
INSERT INTO `t_car_brand` VALUES ('69cd4e21-31fc-11e7-8957-f8a9632663b3', '���Դ�', '���Դ�', 'Y');
INSERT INTO `t_car_brand` VALUES ('9e93e122-31fe-11e7-8957-f8a9632663b3', '����', '����', 'Y');
INSERT INTO `t_car_brand` VALUES ('cd8a43b9-31fb-11e7-8957-f8a9632663b3', '����', '�Ϻ����ڣ�һ������', 'Y');
INSERT INTO `t_car_brand` VALUES ('cdf0dde0-2a22-11e7-9093-2c600cb87d10', '����', '����', 'Y');
INSERT INTO `t_car_brand` VALUES ('fcb739a0-31fb-11e7-8957-f8a9632663b3', '����', 'һ�������������', 'Y');

/**
t_car_color���������
*/
INSERT INTO `t_car_color` VALUES ('01c358d2-31fe-11e7-8957-f8a9632663b3', '���', '99,0,0', '#630000', '���ɫ', 'Y');
INSERT INTO `t_car_color` VALUES ('094b887a-2a23-11e7-9093-2c600cb87d10', '����', '0,0,255', '#0000FF', '����', 'Y');
INSERT INTO `t_car_color` VALUES ('0a50b260-3200-11e7-8957-f8a9632663b3', '��ɫ', '156,0,255', '#9C00FF', '��ɫ', 'Y');
INSERT INTO `t_car_color` VALUES ('1b16ddbd-31fd-11e7-8957-f8a9632663b3', '��ɫ', '0,0,0', '#000000', '��ɫ', 'Y');
INSERT INTO `t_car_color` VALUES ('240ed6f8-31fd-11e7-8957-f8a9632663b3', '��ɫ', '156,156,148', '#9C9C94', '��ɫ', 'Y');
INSERT INTO `t_car_color` VALUES ('3167a189-31fd-11e7-8957-f8a9632663b3', '�ʺ�', '255,0,0', '#FF0000', '�ʺ�', 'Y');
INSERT INTO `t_car_color` VALUES ('3a0393cb-31fd-11e7-8957-f8a9632663b3', '��ɫ', '0,255,0', '#00FF00', '��ɫ', 'Y');
INSERT INTO `t_car_color` VALUES ('4ef8b866-31fd-11e7-8957-f8a9632663b3', '��ɫ', '255,255,0', '#FFFF00', '��ɫ', 'Y');
INSERT INTO `t_car_color` VALUES ('f403c6c1-31fd-11e7-8957-f8a9632663b3', '�ۺ�', '255,0,255', '#FF00FF', '�ۺ�ɫ', 'Y');

/**
t_car_model���������
*/
INSERT INTO `t_car_model` VALUES ('0262045f-3204-11e7-8957-f8a9632663b3', '���ǵ�S7', '���ǵ�S7', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('02e0b3d4-3203-11e7-8957-f8a9632663b3', '����5ϵ', '����5ϵ', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('097d205f-3204-11e7-8957-f8a9632663b3', '���ǵ�F0', '���ǵ�F0', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('0aab2146-3202-11e7-8957-f8a9632663b3', '�µ�RS7', '�µ�RS7', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('0e881eb2-3203-11e7-8957-f8a9632663b3', '����3ϵ', '����3ϵ', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('1170afdf-3204-11e7-8957-f8a9632663b3', '���ǵ�S6', '���ǵ�S6', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('18c9d328-3203-11e7-8957-f8a9632663b3', '����X1', '����X1', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('191d3e12-3204-11e7-8957-f8a9632663b3', '��EV300', '��EV300', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('2159c5be-3203-11e7-8957-f8a9632663b3', '����i8', '����i8', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('251a2349-3204-11e7-8957-f8a9632663b3', '���ǵ�e5', '���ǵ�e5', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('28db544b-3203-11e7-8957-f8a9632663b3', '����1ϵ(����)', '����1ϵ(����)', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('2de9f2af-3202-11e7-8957-f8a9632663b3', '��³������', '��³������', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('2e87923f-3204-11e7-8957-f8a9632663b3', '���ǵ�F6', '���ǵ�F6', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3423b259-3203-11e7-8957-f8a9632663b3', '����6ϵ', '����6ϵ', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('35435138-3202-11e7-8957-f8a9632663b3', 'Equinox̽����', 'Equinox̽����', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3c884051-3202-11e7-8957-f8a9632663b3', '����', '����', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3d2258c4-3203-11e7-8957-f8a9632663b3', '����M4', '����M4', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('45bf9d46-3202-11e7-8957-f8a9632663b3', '������', '������', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('47ce5ad6-31fe-11e7-8957-f8a9632663b3', 'CR-V', 'CR-V', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('4ea66fd1-3202-11e7-8957-f8a9632663b3', '��ŷ3', '��ŷ3', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('4ec0da3e-3203-11e7-8957-f8a9632663b3', 'Ӣ��', 'Ӣ��', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('5c64fca7-3203-11e7-8957-f8a9632663b3', '��Խ', '��Խ', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('611c098c-31fe-11e7-8957-f8a9632663b3', '˼��', '˼��', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('638010f2-3203-11e7-8957-f8a9632663b3', '����', '����', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('6bc115f0-31fe-11e7-8957-f8a9632663b3', 'XR-V', 'XR-V', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('6cc621f8-3203-11e7-8957-f8a9632663b3', '����', '����', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('6e567ec0-3202-11e7-8957-f8a9632663b3', '����XL', '����XL', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('76e4e50a-3203-11e7-8957-f8a9632663b3', '���GL8', '���GL8', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('774b54e5-31fe-11e7-8957-f8a9632663b3', 'UR-V', 'UR-V', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7c279489-3202-11e7-8957-f8a9632663b3', '��ŷSPRINGO', '��ŷSPRINGO', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7d389d44-3204-11e7-8957-f8a9632663b3', '���Դ�CX-5', '���Դ�CX-5', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7dbba711-3201-11e7-8957-f8a9632663b3', '˼��', '˼��', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7e77c707-3203-11e7-8957-f8a9632663b3', '��Խ', '��Խ', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8680a57a-3204-11e7-8957-f8a9632663b3', '������', '������', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8909abb3-3203-11e7-8957-f8a9632663b3', '������', '������', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8a94cd37-3202-11e7-8957-f8a9632663b3', 'ȫ��;ʤ', 'ȫ��;ʤ', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8f93b32f-3204-11e7-8957-f8a9632663b3', '���Դ�5�����ڣ�', '���Դ�5�����ڣ�', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('92eae0ed-3203-11e7-8957-f8a9632663b3', 'Ӣ��XT', 'Ӣ��XT', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('9a3c8447-3201-11e7-8957-f8a9632663b3', '�濥', '�濥', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('9c414fb1-3202-11e7-8957-f8a9632663b3', '�ö�', '�ö�', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a1dff18f-3201-11e7-8957-f8a9632663b3', '����', '����', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a2834495-3202-11e7-8957-f8a9632663b3', 'MISTRA��ͼ', 'MISTRA��ͼ', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a68704cb-3203-11e7-8957-f8a9632663b3', '��3', '��3', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a9195efb-3202-11e7-8957-f8a9632663b3', '�����ִ�ix35', '�����ִ�ix35', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('aceefa21-3201-11e7-8957-f8a9632663b3', '�п�', '�п�', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('afe2e3f6-3203-11e7-8957-f8a9632663b3', '������5', '������5', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('b3c303ce-3204-11e7-8957-f8a9632663b3', 'mazda RX-8(����),RX-8', 'mazda RX-8', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('b7328f65-3201-11e7-8957-f8a9632663b3', '����', '����', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('b7bffd8a-3203-11e7-8957-f8a9632663b3', '��7', '��7', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('bb4dc8cc-31fc-11e7-8957-f8a9632663b3', ';��', ';��', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('bccb663a-3204-11e7-8957-f8a9632663b3', '���Դ�ATENZA', '���Դ�ATENZA', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('bf18a498-3203-11e7-8957-f8a9632663b3', '��3x', '��3x', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('c4871cf1-3201-11e7-8957-f8a9632663b3', '���TIIDA', '���TIIDA', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('c5fd13d1-3204-11e7-8957-f8a9632663b3', '���Դ�626', '���Դ�626', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('c7966358-3203-11e7-8957-f8a9632663b3', '��5', '��5', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('cbb2c77c-31fc-11e7-8957-f8a9632663b3', ';��', ';��', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('cf658754-3203-11e7-8957-f8a9632663b3', '������7', '������7', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('d3376425-31fc-11e7-8957-f8a9632663b3', '�ݴ�', '�ݴ�', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('dae281aa-31fc-11e7-8957-f8a9632663b3', '����', '����', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('df0d9c01-3202-11e7-8957-f8a9632663b3', 'ȫ��ʤ��', 'ȫ��ʤ��', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e022f064-3201-11e7-8957-f8a9632663b3', '�µ�Q8', '�µ�Q8', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e1feff02-3203-11e7-8957-f8a9632663b3', '��', '��', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e4ffd26d-31fc-11e7-8957-f8a9632663b3', '�߶���', '�߶���', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e6c3071a-3202-11e7-8957-f8a9632663b3', '��������', '��������', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('ea45f082-3203-11e7-8957-f8a9632663b3', '���ǵ�F3', '���ǵ�F3', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('ecaa5a8a-3201-11e7-8957-f8a9632663b3', '�µ�SQ5', '�µ�SQ5', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('f05250c4-3203-11e7-8957-f8a9632663b3', 'Ԫ', 'Ԫ', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('f3a38828-3201-11e7-8957-f8a9632663b3', '�µ�RS6', '�µ�RS6', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('f5b721de-31fc-11e7-8957-f8a9632663b3', ';��', ';��', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('fac87d9e-3203-11e7-8957-f8a9632663b3', '��', '��', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('fbe6040b-3201-11e7-8957-f8a9632663b3', '�µ�S6', '�µ�S6', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');

/**
t_car_plate���������
*/
INSERT INTO `t_car_plate` VALUES ('ec88c11e-3208-11e7-8957-f8a9632663b3', '��Y', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec8c0f69-3208-11e7-8957-f8a9632663b3', '��A', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec8f4ea7-3208-11e7-8957-f8a9632663b3', '��B', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec93b3f0-3208-11e7-8957-f8a9632663b3', '��C', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec98e085-3208-11e7-8957-f8a9632663b3', '��D', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec9d8195-3208-11e7-8957-f8a9632663b3', '��E', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('eca27860-3208-11e7-8957-f8a9632663b3', '��F', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('eca754cc-3208-11e7-8957-f8a9632663b3', '��M', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecac3da5-3208-11e7-8957-f8a9632663b3', '��J', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecb11dec-3208-11e7-8957-f8a9632663b3', '��K', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecb60b50-3208-11e7-8957-f8a9632663b3', '��L', '�����ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecbc3847-3208-11e7-8957-f8a9632663b3', '��A', '�Ϻ��ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecc0e4ec-3208-11e7-8957-f8a9632663b3', '��B', '�Ϻ��ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecc54e65-3208-11e7-8957-f8a9632663b3', '��C', '�Ϻ��еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('eccad0c5-3208-11e7-8957-f8a9632663b3', '��R', '�����صĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('eccf3e68-3208-11e7-8957-f8a9632663b3', '��D', '�Ϻ��ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecd3e543-3208-11e7-8957-f8a9632663b3', '��A', '���ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecd877ce-3208-11e7-8957-f8a9632663b3', '��C', '���ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecdd58d6-3208-11e7-8957-f8a9632663b3', '��D', '���ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ece1f0a9-3208-11e7-8957-f8a9632663b3', '��E', '���ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ece611fd-3208-11e7-8957-f8a9632663b3', '��B', '���ĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('eceb5b3c-3208-11e7-8957-f8a9632663b3', '��A', '�ϲ��еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecefba93-3208-11e7-8957-f8a9632663b3', '��M', '�ϲ��еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecf46616-3208-11e7-8957-f8a9632663b3', '��B', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecf93641-3208-11e7-8957-f8a9632663b3', '��C', '�˴��еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecfe9c30-3208-11e7-8957-f8a9632663b3', '��D', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed04ae0c-3208-11e7-8957-f8a9632663b3', '��F', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed0ab0b1-3208-11e7-8957-f8a9632663b3', '��E', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed10ceaa-3208-11e7-8957-f8a9632663b3', '��G', '�Ž��еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed14ccc2-3208-11e7-8957-f8a9632663b3', '��H', '�������еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed194125-3208-11e7-8957-f8a9632663b3', '��J', 'Ƽ���еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed1d97f9-3208-11e7-8957-f8a9632663b3', '��K', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed21d189-3208-11e7-8957-f8a9632663b3', '��L', 'ӥ̶�еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed27945e-3208-11e7-8957-f8a9632663b3', '��A', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed2ce0ab-3208-11e7-8957-f8a9632663b3', '��B', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed30e393-3208-11e7-8957-f8a9632663b3', '��C', '�麣�еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed364ac1-3208-11e7-8957-f8a9632663b3', '��D', '��ͷ�еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed3a58cc-3208-11e7-8957-f8a9632663b3', '��E', '��ɽ�еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed405dcd-3208-11e7-8957-f8a9632663b3', '��F', '�ع��еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed454f06-3208-11e7-8957-f8a9632663b3', '��G', 'տ���еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed4bbbe9-3208-11e7-8957-f8a9632663b3', '��H', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed4fe978-3208-11e7-8957-f8a9632663b3', '��J', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed54465b-3208-11e7-8957-f8a9632663b3', '��K', 'ï���еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed58ccb7-3208-11e7-8957-f8a9632663b3', '��Q', '�����еĳ�', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed5d2a0d-3208-11e7-8957-f8a9632663b3', '��S', '��ݸ�еĳ�', 'Y');

/**
t_incoming_type���������
*/
insert into t_incoming_type(inTypeId,inTypeName,inTypeStatus,inTypeCreatedTime) values('1eeeba11-3315-11e7-9707-507b9d3ffd38','ά�ޱ�������','Y',now());
insert into t_incoming_type(inTypeId,inTypeName,inTypeStatus,inTypeCreatedTime) values('1eeeba11-3315-11e7-9707-507b9d123d38','�������','Y',now());


/**
t_outgoing_type���������
*/
insert into t_outgoing_type(outTypeId,outTypeName,outTypeStatus,outTypeCreatedTime) values('1eeeba11-1234-11e7-9707-507b9d3ffd38','����֧��','Y',now());
insert into t_outgoing_type(outTypeId,outTypeName,outTypeStatus,outTypeCreatedTime) values('1eeeba11-1234-11e7-9707-507b9dqwed38','���֧��','Y',now());


