/**
t_user表基本数据
*/
INSERT INTO `t_user` VALUES ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', '847315251@.qq.com', '13672297775', '6khXbzC+FmmXFpnAmtBclA==', '大牛', '360735199503242117', '王根参', 'M', '1995-03-24', '江西省/赣州市/章贡区', null, null, null, '/img/default.png', null, null, '0', '2017-04-26 10:15:55', null, 'Y');

/**
t_user_role表基本数据
*/
INSERT INTO `t_user_role` VALUE ('8ad97bb9-7989-4c37-8bf5-ca7cc4bf0VNM', '8ad97bb9-7989-4c37-8bf5-ca7cc4bf0366', 'ad381c0d-2497-11e7-8420-2c600cb87d10', NOW());

/**
t_role表基本数据
*/
INSERT INTO `t_role` VALUES ('019fa931-2498-11e7-8420-2c600cb87d10', 'companyAdmin', '董事长', 'Y');
INSERT INTO `t_role` VALUES ('0b937b27-2499-11e7-8420-2c600cb87d10', 'companyRepertory', '库管', 'Y');
INSERT INTO `t_role` VALUES ('3a57ac4d-2498-11e7-8420-2c600cb87d10', 'companyReceive', '接待员', 'Y');
INSERT INTO `t_role` VALUES ('4ee7f225-2498-11e7-8420-2c600cb87d10', 'companyArtificer', '技师', 'Y');
INSERT INTO `t_role` VALUES ('854fee2f-2498-11e7-8420-2c600cb87d10', 'companySales', '销售员', 'Y');
INSERT INTO `t_role` VALUES ('8d6f012f-249a-11e7-8420-2c600cb87d10', 'companyHumanManager', '人事管理员', 'Y');
INSERT INTO `t_role` VALUES ('b6a58bdc-2498-11e7-8420-2c600cb87d10', 'companyAccounting', '财务', 'Y');
INSERT INTO `t_role` VALUES ('fba55f1e-265b-11e7-bfbf-2c600cb87d10', 'companyEmp', '普通员工', 'Y');
INSERT INTO `t_role` VALUES ('d549db7c-2498-11e7-8420-2c600cb87d10', 'companyBuyer', '采购员', 'Y');
INSERT INTO `t_role` VALUES ('3824a636-2499-11e7-8420-2c600cb87d10', 'carOwner', '车主', 'Y');
INSERT INTO `t_role` VALUES ('ad381c0d-2497-11e7-8420-2c600cb87d10', 'systemSuperAdmin', '超级管理员', 'Y');
INSERT INTO `t_role` VALUES ('f0f999ab-2497-11e7-8420-2c600cb87d10', 'systemOrdinaryAdmin', '普通管理员', 'Y');


/**
t_car_brand表基本数据
*/
INSERT INTO `t_car_brand` VALUES ('0808e5d0-31fc-11e7-8957-f8a9632663b3', '本田', '本田', 'Y');
INSERT INTO `t_car_brand` VALUES ('1a835893-31fc-11e7-8957-f8a9632663b3', '日产', '东风日产', 'Y');
INSERT INTO `t_car_brand` VALUES ('2b77dba1-31fc-11e7-8957-f8a9632663b3', '奥迪', '奥迪', 'Y');
INSERT INTO `t_car_brand` VALUES ('342b6b93-31fc-11e7-8957-f8a9632663b3', '雪佛兰', '雪佛兰', 'Y');
INSERT INTO `t_car_brand` VALUES ('3f04171b-31fc-11e7-8957-f8a9632663b3', '现代', '北京现代', 'Y');
INSERT INTO `t_car_brand` VALUES ('47bc70ac-31fc-11e7-8957-f8a9632663b3', '别克', '别克', 'Y');
INSERT INTO `t_car_brand` VALUES ('4e7ea042-31fc-11e7-8957-f8a9632663b3', '奇瑞', '奇瑞', 'Y');
INSERT INTO `t_car_brand` VALUES ('54c16e99-31fc-11e7-8957-f8a9632663b3', '比亚迪', 'BYD', 'Y');
INSERT INTO `t_car_brand` VALUES ('69cd4e21-31fc-11e7-8957-f8a9632663b3', '马自达', '马自达', 'Y');
INSERT INTO `t_car_brand` VALUES ('9e93e122-31fe-11e7-8957-f8a9632663b3', '奔驰', '奔驰', 'Y');
INSERT INTO `t_car_brand` VALUES ('cd8a43b9-31fb-11e7-8957-f8a9632663b3', '大众', '上海大众，一汽大众', 'Y');
INSERT INTO `t_car_brand` VALUES ('cdf0dde0-2a22-11e7-9093-2c600cb87d10', '宝马', '宝马', 'Y');
INSERT INTO `t_car_brand` VALUES ('fcb739a0-31fb-11e7-8957-f8a9632663b3', '丰田', '一汽丰田，广汽丰田', 'Y');

/**
t_car_color表基本数据
*/
INSERT INTO `t_car_color` VALUES ('01c358d2-31fe-11e7-8957-f8a9632663b3', '深红', '99,0,0', '#630000', '深红色', 'Y');
INSERT INTO `t_car_color` VALUES ('094b887a-2a23-11e7-9093-2c600cb87d10', '宝蓝', '0,0,255', '#0000FF', '宝蓝', 'Y');
INSERT INTO `t_car_color` VALUES ('0a50b260-3200-11e7-8957-f8a9632663b3', '紫色', '156,0,255', '#9C00FF', '紫色', 'Y');
INSERT INTO `t_car_color` VALUES ('1b16ddbd-31fd-11e7-8957-f8a9632663b3', '黑色', '0,0,0', '#000000', '黑色', 'Y');
INSERT INTO `t_car_color` VALUES ('240ed6f8-31fd-11e7-8957-f8a9632663b3', '灰色', '156,156,148', '#9C9C94', '灰色', 'Y');
INSERT INTO `t_car_color` VALUES ('3167a189-31fd-11e7-8957-f8a9632663b3', '鲜红', '255,0,0', '#FF0000', '鲜红', 'Y');
INSERT INTO `t_car_color` VALUES ('3a0393cb-31fd-11e7-8957-f8a9632663b3', '绿色', '0,255,0', '#00FF00', '绿色', 'Y');
INSERT INTO `t_car_color` VALUES ('4ef8b866-31fd-11e7-8957-f8a9632663b3', '黄色', '255,255,0', '#FFFF00', '黄色', 'Y');
INSERT INTO `t_car_color` VALUES ('f403c6c1-31fd-11e7-8957-f8a9632663b3', '粉红', '255,0,255', '#FF00FF', '粉红色', 'Y');

/**
t_car_model表基本数据
*/
INSERT INTO `t_car_model` VALUES ('0262045f-3204-11e7-8957-f8a9632663b3', '比亚迪S7', '比亚迪S7', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('02e0b3d4-3203-11e7-8957-f8a9632663b3', '宝马5系', '宝马5系', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('097d205f-3204-11e7-8957-f8a9632663b3', '比亚迪F0', '比亚迪F0', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('0aab2146-3202-11e7-8957-f8a9632663b3', '奥迪RS7', '奥迪RS7', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('0e881eb2-3203-11e7-8957-f8a9632663b3', '宝马3系', '宝马3系', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('1170afdf-3204-11e7-8957-f8a9632663b3', '比亚迪S6', '比亚迪S6', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('18c9d328-3203-11e7-8957-f8a9632663b3', '宝马X1', '宝马X1', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('191d3e12-3204-11e7-8957-f8a9632663b3', '秦EV300', '秦EV300', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('2159c5be-3203-11e7-8957-f8a9632663b3', '宝马i8', '宝马i8', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('251a2349-3204-11e7-8957-f8a9632663b3', '比亚迪e5', '比亚迪e5', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('28db544b-3203-11e7-8957-f8a9632663b3', '宝马1系(进口)', '宝马1系(进口)', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('2de9f2af-3202-11e7-8957-f8a9632663b3', '科鲁兹三厢', '科鲁兹三厢', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('2e87923f-3204-11e7-8957-f8a9632663b3', '比亚迪F6', '比亚迪F6', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3423b259-3203-11e7-8957-f8a9632663b3', '宝马6系', '宝马6系', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('35435138-3202-11e7-8957-f8a9632663b3', 'Equinox探界者', 'Equinox探界者', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3c884051-3202-11e7-8957-f8a9632663b3', '迈锐宝', '迈锐宝', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('3d2258c4-3203-11e7-8957-f8a9632663b3', '宝马M4', '宝马M4', 'cdf0dde0-2a22-11e7-9093-2c600cb87d10', 'Y');
INSERT INTO `t_car_model` VALUES ('45bf9d46-3202-11e7-8957-f8a9632663b3', '科沃兹', '科沃兹', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('47ce5ad6-31fe-11e7-8957-f8a9632663b3', 'CR-V', 'CR-V', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('4ea66fd1-3202-11e7-8957-f8a9632663b3', '赛欧3', '赛欧3', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('4ec0da3e-3203-11e7-8957-f8a9632663b3', '英朗', '英朗', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('5c64fca7-3203-11e7-8957-f8a9632663b3', '君越', '君越', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('611c098c-31fe-11e7-8957-f8a9632663b3', '思域', '思域', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('638010f2-3203-11e7-8957-f8a9632663b3', '君威', '君威', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('6bc115f0-31fe-11e7-8957-f8a9632663b3', 'XR-V', 'XR-V', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('6cc621f8-3203-11e7-8957-f8a9632663b3', '威朗', '威朗', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('6e567ec0-3202-11e7-8957-f8a9632663b3', '迈锐宝XL', '迈锐宝XL', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('76e4e50a-3203-11e7-8957-f8a9632663b3', '别克GL8', '别克GL8', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('774b54e5-31fe-11e7-8957-f8a9632663b3', 'UR-V', 'UR-V', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7c279489-3202-11e7-8957-f8a9632663b3', '赛欧SPRINGO', '赛欧SPRINGO', '342b6b93-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7d389d44-3204-11e7-8957-f8a9632663b3', '马自达CX-5', '马自达CX-5', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7dbba711-3201-11e7-8957-f8a9632663b3', '思铭', '思铭', '0808e5d0-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('7e77c707-3203-11e7-8957-f8a9632663b3', '凯越', '凯越', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8680a57a-3204-11e7-8957-f8a9632663b3', '阿特兹', '阿特兹', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8909abb3-3203-11e7-8957-f8a9632663b3', '昂科拉', '昂科拉', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8a94cd37-3202-11e7-8957-f8a9632663b3', '全新途胜', '全新途胜', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('8f93b32f-3204-11e7-8957-f8a9632663b3', '马自达5（进口）', '马自达5（进口）', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('92eae0ed-3203-11e7-8957-f8a9632663b3', '英朗XT', '英朗XT', '47bc70ac-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('9a3c8447-3201-11e7-8957-f8a9632663b3', '奇骏', '奇骏', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('9c414fb1-3202-11e7-8957-f8a9632663b3', '悦动', '悦动', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a1dff18f-3201-11e7-8957-f8a9632663b3', '轩逸', '轩逸', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a2834495-3202-11e7-8957-f8a9632663b3', 'MISTRA名图', 'MISTRA名图', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a68704cb-3203-11e7-8957-f8a9632663b3', '瑞虎3', '瑞虎3', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('a9195efb-3202-11e7-8957-f8a9632663b3', '北京现代ix35', '北京现代ix35', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('aceefa21-3201-11e7-8957-f8a9632663b3', '逍客', '逍客', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('afe2e3f6-3203-11e7-8957-f8a9632663b3', '艾瑞泽5', '艾瑞泽5', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('b3c303ce-3204-11e7-8957-f8a9632663b3', 'mazda RX-8(进口),RX-8', 'mazda RX-8', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('b7328f65-3201-11e7-8957-f8a9632663b3', '天籁', '天籁', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('b7bffd8a-3203-11e7-8957-f8a9632663b3', '瑞虎7', '瑞虎7', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('bb4dc8cc-31fc-11e7-8957-f8a9632663b3', '途昂', '途昂', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('bccb663a-3204-11e7-8957-f8a9632663b3', '马自达ATENZA', '马自达ATENZA', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('bf18a498-3203-11e7-8957-f8a9632663b3', '瑞虎3x', '瑞虎3x', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('c4871cf1-3201-11e7-8957-f8a9632663b3', '骐达TIIDA', '骐达TIIDA', '1a835893-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('c5fd13d1-3204-11e7-8957-f8a9632663b3', '马自达626', '马自达626', '69cd4e21-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('c7966358-3203-11e7-8957-f8a9632663b3', '瑞虎5', '瑞虎5', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('cbb2c77c-31fc-11e7-8957-f8a9632663b3', '途观', '途观', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('cf658754-3203-11e7-8957-f8a9632663b3', '艾瑞泽7', '艾瑞泽7', '4e7ea042-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('d3376425-31fc-11e7-8957-f8a9632663b3', '捷达', '捷达', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('dae281aa-31fc-11e7-8957-f8a9632663b3', '宝来', '宝来', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('df0d9c01-3202-11e7-8957-f8a9632663b3', '全新胜达', '全新胜达', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e022f064-3201-11e7-8957-f8a9632663b3', '奥迪Q8', '奥迪Q8', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e1feff02-3203-11e7-8957-f8a9632663b3', '宋', '宋', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e4ffd26d-31fc-11e7-8957-f8a9632663b3', '高尔夫', '高尔夫', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('e6c3071a-3202-11e7-8957-f8a9632663b3', '索纳塔八', '索纳塔八', '3f04171b-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('ea45f082-3203-11e7-8957-f8a9632663b3', '比亚迪F3', '比亚迪F3', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('ecaa5a8a-3201-11e7-8957-f8a9632663b3', '奥迪SQ5', '奥迪SQ5', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('f05250c4-3203-11e7-8957-f8a9632663b3', '元', '元', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('f3a38828-3201-11e7-8957-f8a9632663b3', '奥迪RS6', '奥迪RS6', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('f5b721de-31fc-11e7-8957-f8a9632663b3', '途安', '途安', 'cd8a43b9-31fb-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('fac87d9e-3203-11e7-8957-f8a9632663b3', '唐', '唐', '54c16e99-31fc-11e7-8957-f8a9632663b3', 'Y');
INSERT INTO `t_car_model` VALUES ('fbe6040b-3201-11e7-8957-f8a9632663b3', '奥迪S6', '奥迪S6', '2b77dba1-31fc-11e7-8957-f8a9632663b3', 'Y');

/**
t_car_plate表基本数据
*/
INSERT INTO `t_car_plate` VALUES ('ec88c11e-3208-11e7-8957-f8a9632663b3', '京Y', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec8c0f69-3208-11e7-8957-f8a9632663b3', '京A', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec8f4ea7-3208-11e7-8957-f8a9632663b3', '京B', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec93b3f0-3208-11e7-8957-f8a9632663b3', '京C', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec98e085-3208-11e7-8957-f8a9632663b3', '京D', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ec9d8195-3208-11e7-8957-f8a9632663b3', '京E', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('eca27860-3208-11e7-8957-f8a9632663b3', '京F', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('eca754cc-3208-11e7-8957-f8a9632663b3', '京M', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecac3da5-3208-11e7-8957-f8a9632663b3', '京J', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecb11dec-3208-11e7-8957-f8a9632663b3', '京K', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecb60b50-3208-11e7-8957-f8a9632663b3', '京L', '北京的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecbc3847-3208-11e7-8957-f8a9632663b3', '泸A', '上海的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecc0e4ec-3208-11e7-8957-f8a9632663b3', '泸B', '上海的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecc54e65-3208-11e7-8957-f8a9632663b3', '泸C', '上海市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('eccad0c5-3208-11e7-8957-f8a9632663b3', '泸R', '崇明县的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('eccf3e68-3208-11e7-8957-f8a9632663b3', '泸D', '上海的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecd3e543-3208-11e7-8957-f8a9632663b3', '津A', '天津的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecd877ce-3208-11e7-8957-f8a9632663b3', '津C', '天津的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecdd58d6-3208-11e7-8957-f8a9632663b3', '津D', '天津的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ece1f0a9-3208-11e7-8957-f8a9632663b3', '津E', '天津的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ece611fd-3208-11e7-8957-f8a9632663b3', '津B', '天津的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('eceb5b3c-3208-11e7-8957-f8a9632663b3', '赣A', '南昌市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecefba93-3208-11e7-8957-f8a9632663b3', '赣M', '南昌市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecf46616-3208-11e7-8957-f8a9632663b3', '赣B', '赣州市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecf93641-3208-11e7-8957-f8a9632663b3', '赣C', '宜春市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ecfe9c30-3208-11e7-8957-f8a9632663b3', '赣D', '吉安市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed04ae0c-3208-11e7-8957-f8a9632663b3', '赣F', '抚州市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed0ab0b1-3208-11e7-8957-f8a9632663b3', '赣E', '上饶市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed10ceaa-3208-11e7-8957-f8a9632663b3', '赣G', '九江市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed14ccc2-3208-11e7-8957-f8a9632663b3', '赣H', '景德镇市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed194125-3208-11e7-8957-f8a9632663b3', '赣J', '萍乡市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed1d97f9-3208-11e7-8957-f8a9632663b3', '赣K', '新余市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed21d189-3208-11e7-8957-f8a9632663b3', '赣L', '鹰潭市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed27945e-3208-11e7-8957-f8a9632663b3', '粤A', '广州市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed2ce0ab-3208-11e7-8957-f8a9632663b3', '粤B', '深圳市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed30e393-3208-11e7-8957-f8a9632663b3', '粤C', '珠海市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed364ac1-3208-11e7-8957-f8a9632663b3', '粤D', '汕头市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed3a58cc-3208-11e7-8957-f8a9632663b3', '粤E', '佛山市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed405dcd-3208-11e7-8957-f8a9632663b3', '粤F', '韶关市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed454f06-3208-11e7-8957-f8a9632663b3', '粤G', '湛江市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed4bbbe9-3208-11e7-8957-f8a9632663b3', '粤H', '肇庆市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed4fe978-3208-11e7-8957-f8a9632663b3', '粤J', '江门市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed54465b-3208-11e7-8957-f8a9632663b3', '粤K', '茂名市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed58ccb7-3208-11e7-8957-f8a9632663b3', '粤Q', '阳江市的车', 'Y');
INSERT INTO `t_car_plate` VALUES ('ed5d2a0d-3208-11e7-8957-f8a9632663b3', '粤S', '东莞市的车', 'Y');

/**
t_incoming_type表基本数据
*/
insert into t_incoming_type(inTypeId,inTypeName,inTypeStatus,inTypeCreatedTime) values('1eeeba11-3315-11e7-9707-507b9d3ffd38','维修保养收入','Y',now());
insert into t_incoming_type(inTypeId,inTypeName,inTypeStatus,inTypeCreatedTime) values('1eeeba11-3315-11e7-9707-507b9d123d38','配件收入','Y',now());


/**
t_outgoing_type表基本数据
*/
insert into t_outgoing_type(outTypeId,outTypeName,outTypeStatus,outTypeCreatedTime) values('1eeeba11-1234-11e7-9707-507b9d3ffd38','工资支出','Y',now());
insert into t_outgoing_type(outTypeId,outTypeName,outTypeStatus,outTypeCreatedTime) values('1eeeba11-1234-11e7-9707-507b9dqwed38','配件支出','Y',now());


