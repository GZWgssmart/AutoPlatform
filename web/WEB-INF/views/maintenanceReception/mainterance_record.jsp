<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>维修保养记录管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="500">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="checkin.userName">
                车主姓名
            </th>
            <th data-field="checkin.carPlate">
                登记车牌号
            </th>
            <th data-field="startTime" data-formatter="formatterDate">
                开始时间
            </th>
            <th data-field="endTime" data-formatter="formatterDate">
                预估结束时间
            </th>
            <th data-field="actualEndTime" data-formatter="formatterDate">
                实际结束时间
            </th>
            <th data-field="recordCreatedTime" data-formatter="formatterDate">
                创建记录时间
            </th>
            <th data-field="pickupTime" data-formatter="formatterDate">
                提车时间
            </th>
            <th data-field="checkin.maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="trackStatus" data-formatter="formatterTrack">
                是否回访
            </th>
            <th data-field="recordDes">
                记录描述
            </th>
            <th data-field="company.companyName">
                汽修公司
            </th>
            <th data-field="recordStatus" data-formatter="status">
                记录状态
            </th>
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">

            <a>
                <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 修改
                </button>
            </a>

            <a>
                <button onclick="showAddDetailWin();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-glass"></i> 生成明细
                </button>
            </a>

            <a>
                <button onclick="showDetailWin();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看明细
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
                        <h3 class="m-t-none m-b">修改维修保养记录</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="record.recordId" name="recordId" class="form-control"/>
                            <input type="hidden" attr="record.checkin.checkinId" name="checkinId" class="form-control"/>
                            <input type="hidden" attr="record.trackStatus" name="trackStatus" class="form-control"/>
                            <div class="form-group">
                                <label>开始时间：</label>
                                <input id="editStartTime" readonly type="text" name="startTime"
                                       class="form-control datetimepicker"/>
                            </div>

                            <div class="form-group">
                                <label>预估结束时间：</label>
                                <input id="editEndTime" readonly type="text" name="endTime"
                                       class="form-control datetimepicker"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">记录描述：</label>
                                <textarea class="form-control" attr="record.recordDes" type="textarea" name="recordDes"
                                          rows="3"></textarea>
                            </div>


                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" onclick="buttonStatus('editForm', 'editButton')" id="editButton" class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="searchDetailWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">查看维修保养明细</h3>
                        <table class="table table-hover" id="detailTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="record.checkin.userName">
                                    车主姓名
                                </th>
                                <th data-field="record.checkin.userPhone">
                                    车主电话
                                </th>
                                <th data-field="record.checkin.maintainOrFix">
                                    维修&nbsp;|&nbsp;保养
                                </th>
                                <th data-field="maintain.maintainName">
                                    项目
                                </th>
                                <th data-field="maintainDiscount">
                                    打折&nbsp;|&nbsp;减价
                                </th>
                                <th data-field="detailCreatedTime" data-formatter="formatterDate">
                                    明细创建时间
                                </th>
                            </thead>
                            <tbody>
                            <div id="toolbar1" class="btn-group">

                                <a>
                                    <button onclick="showEditDetailWin();" type="button" id="editDetail" class="btn btn-default">
                                        <i class="glyphicon glyphicon-pencil"></i> 修改
                                    </button>
                                </a>

                            </div>
                            </tbody>

                        </table>
                        <div style="height: 100px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="detailWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-12 b-r">
                    <h3 class="m-t-none m-b">生成维修保养明细</h3>
                    <form role="form" id="detailForm">
                        <input type="hidden" attr="record.recordId" name="recordId" class="form-control"/>
                        <div class="form-group">
                            <label class="control-label">维修保养项目：</label>
                            <select id="detailMaintainFix" class="js-example-tags form-control maintain_fix" name="maintainId">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label">折扣&nbsp;|&nbsp;减价：</label>
                            <input type="text" maxlength="4" name="maintainDiscount" class="form-control"/>
                            <span style="font-size: 12px; color: green;">小于1大于0是折扣，大于等于1则是减价</span>
                        </div>

                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <input type="button" id="detailButton" onclick="buttonStatus('detailForm', 'detailButton')" class="btn btn-primary" value="添加">
                            </input>
                        </div>
                    </form>
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
<script src="<%=path %>/js/maintenanceReception/record.js"></script>
</body>
</html>
