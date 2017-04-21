<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>接待登记管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/maintenanceReception/reception_register.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="550">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="userName" data-space="10">
                车主姓名
            </th>
            <th data-field="userPhone">
                车主电话
            </th>
            <th data-field="brand.brandName">
                汽车品牌
            </th>
            <th data-field="color.colorName">
                汽车颜色
            </th>
            <th data-field="model.modelName">
                汽车车型
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="plate.plateName">
                汽车车牌
            </th>
            <th data-field="oilCount">
                汽车油量
            </th>
            <th data-field="carWash" data-formatter="carWash">
                是否洗车
            </th>
            <th data-field="carPlate">
                车牌号码
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="arriveTime" data-formatter="formatterDate">
                到店时间
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="carMileage">
                汽车行驶里程
            </th>
            <th data-field="carThings">
                车上物品描述
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="intactDegrees">
                汽车完好度描述
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="userRequests">
                用户要求描述
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="checkinCreatedTime" data-formatter="formatterDate">
                登记时间
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="company.companyName">
                汽修公司
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
            <th data-field="checkinStatus" data-formatter="status">
                记录状态
            </th>
            <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">
                操作
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a>
                <button onclick="showAddWin();" type="button" id="add" class="btn btn-default">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                </button>
            </a>
            <a>
                <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 修改
                </button>
            </a>
        </div>
        </tbody>

    </table>
</div>


<div id="editWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改登记</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="checkin.checkinId" name="checkinId" class="form-control"/>
                            <div class="col-md-12 form-group">
                                <label>车主姓名：</label>
                                <input type="text" attr="checkin.userName" name="userName" class="form-control"/>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>车主电话：</label>
                                <input type="text" attr="checkin.userPhone" name="userPhone" class="form-control"/>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车品牌：</label>
                                <select id="editCarBrand" class="js-example-tags form-control car_brand" name="brandId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车颜色：</label>
                                <select id="editCarColor" class="js-example-tags form-control car_color" name="colorId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车车型：</label>
                                <select id="editCarModel" class="js-example-tags form-control car_model" name="modelId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车车牌：</label>
                                <select id="editCarPlate" class="js-example-tags form-control car_plate" name="plateId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>车牌号码：</label>
                                <input type="text" attr="checkin.carPlate" name="carPlate" class="form-control"/>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>到店时间：</label>
                                <input id="editDatetimepicker" readonly type="text" name="arriveTime"
                                       class="form-control datetimepicker"/>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>是否需要洗车：</label>
                                <select class="js-example-tags form-control" attr="checkin.carWash" type="select-one" name="carWash">
                                    <option value="N">否</option>
                                    <option value="Y">是</option>
                                </select>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>汽车油量：</label>
                                <input type="text" attr="checkin.oilCount" name="oilCount" class="form-control"/>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>汽车行驶里程：</label>
                                <input type="text" attr="checkin.carMileage" name="carMileage" class="form-control"/>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>车上物品描述：</label>
                                <textarea class="form-control" attr="checkin.carThings" type="textarea" name="carThings"
                                          rows="3"></textarea>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车完好度描述：</label>
                                <textarea class="form-control" attr="checkin.intactDegrees" type="textarea" name="intactDegrees"
                                          rows="3"></textarea>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>用户要求描述：</label>
                                <textarea class="form-control" attr="checkin.userRequests" type="textarea" name="userRequests"
                                          rows="3"></textarea>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>保养&nbsp;|&nbsp;维修：</label>
                                <select id="editMaintainOrFix" attr="checkin.maintainOrFix" type="select-one" class="js-example-tags form-control" name="maintainOrFix">
                                    <option value="保养">保养</option>
                                    <option value="维修">维修</option>
                                </select>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" onclick="edit()" id="editButton" class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="addWin" class="modal fade" style="overflow:scroll" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加记录</h3>
                        <form role="form" id="addForm">
                            <div class="col-md-12 form-group">
                                <label>是否预约：</label>
                                <select class="js-example-tags form-control" id="app"
                                        onchange="checkAppointment(this)">
                                    <option value="N">否</option>
                                    <option value="Y">是</option>
                                </select>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>车主姓名：</label>
                                <input type="text" id="addUserName" name="userName" class="form-control"/>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>车主电话：</label>
                                <input type="text" id="addUserPhone" name="userPhone" class="form-control"/>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车品牌：</label>
                                <select id="addCarBrand" class="js-example-tags form-control car_brand" name="brandId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车颜色：</label>
                                <select id="addCarColor" class="js-example-tags form-control car_color" name="colorId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车车型：</label>
                                <select id="addCarModel" class="js-example-tags form-control car_model" name="modelId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车车牌：</label>
                                <select id="addCarPlate" class="js-example-tags form-control car_plate" name="plateId">
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>车牌号码：</label>
                                <input type="text" name="carPlate" class="form-control"/>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>到店时间：</label>
                                <input id="addDatetimepicker" readonly onclick="getDate()" type="text" name="arriveTime"
                                       class="form-control datetimepicker"/>

                            </div>
                            <div class="col-md-12 form-group">
                                <label>是否需要洗车：</label>
                                <select class="js-example-tags form-control" name="carWash">
                                    <option value="N">否</option>
                                    <option value="Y">是</option>
                                </select>

                            </div>
                            <div class="col-md-12 form-group">
                                <label>汽车油量：</label>
                                <input type="text" name="oilCount" class="form-control"/>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>汽车行驶里程：</label>
                                <input type="text" name="carMileage" class="form-control"/>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>车上物品描述：</label>
                                <textarea class="form-control" name="carThings"
                                          rows="3"></textarea>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>汽车完好度描述：</label>
                                <textarea class="form-control" name="intactDegrees"
                                          rows="3"></textarea>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>用户要求描述：</label>
                                <textarea class="form-control" name="userRequests"
                                          rows="3"></textarea>
                            </div>

                            <div class="col-md-12 form-group">
                                <label>保养&nbsp;|&nbsp;维修：</label>
                                <select id="addMaintainOrFix" class="js-example-tags form-control" name="maintainOrFix">
                                    <option value="保养">保养</option>
                                    <option value="维修">维修</option>
                                </select>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" onclick="add()" class="btn btn-primary" value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none;"/>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="appWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择预约记录</h3>
                        <table class="table table-hover" id="appTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-field="appointmentId" data-checkbox="true"></th>
                                <th data-field="userName">
                                    车主姓名
                                </th>
                                <th data-field="userPhone">
                                    车主电话
                                </th>
                                <th data-field="brand.brandName">
                                    汽车品牌
                                </th>
                                <th data-field="color.colorName">
                                    汽车颜色
                                </th>
                                <th data-field="model.modelName">
                                    汽车车型
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </th>
                                <th data-field="plate.plateName">
                                    汽车车牌
                                </th>
                                <th data-field="carPlate">
                                    车牌号码
                                </th>
                                <th data-field="arriveTime" data-formatter="formatterDate">
                                    预计到店时间
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </th>
                                <th data-field="maintainOrFix">
                                    维修&nbsp;|&nbsp;保养
                                </th>
                                <th data-field="appCreatedTime" data-formatter="formatterDate">
                                    预约创建时间
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </th>
                                <th data-field="company.companyName">
                                    汽修公司
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </th>
                                <th data-field="appoitmentStatus" data-formatter="status">
                                    预约状态
                                </th>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div style="height: 100px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default" onclick="closeAppWin()">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="checkApp()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/maintenanceReception/reception_register.js"></script>

</body>
</html>
