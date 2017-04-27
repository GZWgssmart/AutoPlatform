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

    <link href="<%=path %>/css/maintenanceReception/record.css" rel="stylesheet" type="text/css">
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
                维修保养开始时间
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
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchUserName" name="userName" class="form-control" placeholder="请输入车主姓名" >
                </div>
                <div class="col-sm-2">
                    <input type="text" id="searchCarPlate" name="carPlate" class="form-control" placeholder="请输入车牌号码">
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control" id="searchMaintainOrFix" name="maintainOrFix">
                        <option value="all">维修&nbsp;&&nbsp;保养</option>
                        <option value="维修">维修</option>
                        <option value="保养">保养</option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control company" id="searchCompanyId" name="comanyId">
                    </select>
                </div>
                <div class="col-sm-2">
                </div>
                <div class="col-sm-2">
                    <button type="button" onclick="searchCondition()" class="btn btn-primary">
                        查询
                    </button>
                    <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </div>
        </form>
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

            <a>
                <button onclick="searchStatus('/record/pager?status=Y');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/record/pager?status=N');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/record/pager?status=ALL');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看全部
                </button>
            </a>
            <a>
                <button onclick="showSearchForm()" id="showButton" type="button" class="btn btn-primary">
                    <i class="glyphicon glyphicon-search"></i> 查询
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
                                <label class="control-label">维修&nbsp;|&nbsp;保养：</label>
                                <input type="text" id="maintainOrFix" attr="record.checkin.maintainOrFix" readonly class="form-control" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">维修保养项目：</label>
                                <input type="hidden" id="detailMaintainId" name="maintainId" />
                                <input type="text" onclick="choiseMaintain();" id="detailMaintainName" name="maintainName" readonly class="form-control" />
                                <br />
                                <a>
                                    <button onclick="choiseMaintain();" type="button" class="btn btn-primary">
                                        <i class="glyphicon glyphicon-plus"></i> 选择维修保养项目
                                    </button>
                                </a>
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
                                <input type="reset" name="reset" style="display: none;"/>
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
                               data-height="500">
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
                                 <th data-field="maintain.maintainMoney" data-formatter="formatterMoney">
                                    原价
                                </th>
                                <th data-field="maintainDiscount" data-formatter="formatterDiscount">
                                    打折&nbsp;|&nbsp;减价
                                </th>
                                 <th data-field="price" data-formatter="formatterMoney">
                                    现价
                                </th>
                                <th data-field="detailCreatedTime" data-formatter="formatterDate">
                                    明细创建时间
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <div id="toolbar1" class="btn-group">

                                <a>
                                    <button onclick="showEditDetailWin();" type="button" id="editDetail" class="btn btn-default">
                                        <i class="glyphicon glyphicon-pencil"></i> 修改
                                    </button>
                                </a>
                                <a>
                                    <button onclick="generateDetail();" type="button" id="generateDetail" class="btn btn-default">
                                        <i class="glyphicon glyphicon-list-alt"></i> 生成明细清单
                                    </button>
                                </a>

                            </div>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
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

<div id="editDetailWin" style="overflow:scroll" class="modal fade" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改维修保养记录</h3>
                        <form role="form" id="editDetailForm">
                            <input type="hidden" attr="detail.detailId" name="detailId" class="form-control"/>
                            <input type="hidden" attr="detail.record.recordId" name="recordId" class="form-control"/>
                            <div class="form-group">
                                <label>车主姓名：</label>
                                <input readonly type="text" attr="detail.record.checkin.userName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>车主电话：</label>
                                <input readonly type="text" attr="detail.record.checkin.userPhone" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">维修保养项目：</label>
                                <select id="editDetailMaintain" class="js-example-tags form-control maintain_fix" name="maintainId">
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">折扣&nbsp;|&nbsp;减价：</label>
                                <input type="text" maxlength="4" attr="detail.maintainDiscount" name="maintainDiscount" class="form-control"/>
                                <span style="font-size: 12px; color: green;">小于1大于0是折扣，大于等于1则是减价</span>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default" onclick="closeEditDetailWin()">关闭
                                </button>
                                <input type="button" onclick="buttonStatus('editDetailForm', 'editDetailButton')" id="editDetailButton" class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="maintainWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择保养项目</h3>
                        <table class="table table-hover" id="maintainTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="maintainName" >
                                    保养项目名称
                                </th>
                                <th data-field="maintainHour" >
                                    保养所需工时
                                </th>
                                <th data-field="maintainMoney" >
                                    保养基础费用
                                </th>
                                <th data-field="maintainManHourFee" >
                                    保养工时费
                                </th>
                                <th data-field="maintainDes" >
                                    保养描述
                                </th>
                                <th data-field="company.companyName">
                                    公司名称
                                </th>
                                <th data-field="maintainStatus" data-formatter="status">
                                    保养项目状态
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="determineMaintain()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="fixWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择维修项目</h3>
                        <table class="table table-hover" id="fixTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="maintainName" >
                                    维修项目名称
                                </th>
                                <th data-field="maintainHour" >
                                    维修所需工时
                                </th>
                                <th data-field="maintainMoney" >
                                    维修基础费用
                                </th>
                                <th data-field="maintainManHourFee" >
                                    维修工时费
                                </th>
                                <th data-field="maintainDes" >
                                    维修描述
                                </th>
                                <th data-field="company.companyName">
                                    公司名称
                                </th>
                                <th data-field="maintainStatus" data-formatter="status">
                                    维修项目状态
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="determineFix()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="maintainFixWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">
                            <button type="button" class="btn btn-info">
                                <i class="glyphicon glyphicon-floppy-disk"></i> 打印
                            </button>
                        </h3>
                        <div class="content col-sm-12">
                            <div class="title">
                                公司车辆维修、保养清单
                            </div>
                            <div class="bootom_border float_left">
                                <div class="right_border col-sm-3 bootom_border" style="font-weight: bold;">车牌号及车型</div>
                                <div id="carPlate" class="right_border col-sm-3 bootom_border">&nbsp;</div>
                                <div class="right_border col-sm-2 bootom_border" style="font-weight: bold;">首保里程</div>
                                <div class="col-sm-4 bootom_border">&nbsp;</div>
                            </div>
                            <div class="bootom_border float_left">
                                <div class="right_border col-sm-3 bootom_border" style="font-weight: bold;">保养里程（km）</div>
                                <div class="right_border col-sm-3 bootom_border" style="font-weight: bold;">保养时间</div>
                                <div class="col-sm-6 bootom_border" style="font-weight: bold;">保养项目</div>
                            </div>
                            <div style="padding: 0;">
                                <%--汽车保养里程--%>
                                <div id="maintainCarMileage" class="right_border bootom_border col-sm-3" style="float:left;height:252px;line-height:250px;">
                                    &nbsp;
                                </div>
                                <%--汽车保养时间--%>
                                <div id="startTime" class="right_border bootom_border col-sm-3" style="float:left;height:252px;line-height:250px;">
                                    &nbsp;
                                </div>
                                <div class="text_left col-sm-6" style="float:left;padding:0;">
                                    <div style="font-weight: bold;">
                                        <div class="bootom_border right_border col-sm-6">项目名</div>
                                        <div class="bootom_border col-sm-6">价格</div>
                                    </div>
                                    <div>
                                        <div id="maintainName0" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice0" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName1" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice1" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName2" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice2" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName3" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice3" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName4" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice4" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName5" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice5" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName6" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice6" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName7" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice7" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName8" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice8" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                    <div>
                                        <div id="maintainName9" class="bootom_border right_border col-sm-6">&nbsp;</div>
                                        <div id="maintainPrice9" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>

                                    <div>
                                        <div class="bootom_border right_border col-sm-6">&nbsp;合计：</div>
                                        <div id="count" class="bootom_border col-sm-6">&nbsp;</div>
                                    </div>
                                </div>
                                <p class="clear"></p>
                            </div>
                            <div class="bootom_border" style="font-weight: bold;">
                                维修记录
                            </div>
                            <div class="float_left" style="font-weight: bold;">
                                <div class="bootom_border right_border col-sm-3">维修项目</div>
                                <div class="bootom_border right_border col-sm-3">更换配件名称</div>
                                <div class="bootom_border right_border col-sm-2">里程（km）</div>
                                <div class="bootom_border right_border col-sm-2">经手人</div>
                                <div class="bootom_border col-sm-2">日期</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName0" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc0" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage0" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser0" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime0" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName1" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc1" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage1" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser1" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime1" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName2" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc2" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage2" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser2" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime2" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName3" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc3" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage3" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser3" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime3" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName4" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc4" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage4" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser4" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime4" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName5" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc5" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage5" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser5" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime5" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName6" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc6" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage6" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser6" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime6" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName7" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc7" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage7" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser7" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime7" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>
                            <div class="float_left">
                                <div id="fixName8" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixAcc8" class="bootom_border right_border col-sm-3">&nbsp;</div>
                                <div id="fixCarMileage8" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixUser8" class="bootom_border right_border col-sm-2">&nbsp;</div>
                                <div id="fixTime8" class="bootom_border col-sm-2">&nbsp;</div>
                            </div>


                            <div class="col-sm-12 bootom_border" style="height: 40px;">
                                备注：
                            </div>
                            <div class="col-sm-12" style="text-align: right;">
                                <div style="margin-right: 100px;">车主签字：</div>
                            </div>
                        </div>
                        <div style="height: 40px;"></div>
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
