<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>维修保养提醒</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="600">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="user.userName">
                用户名:
            </th>
            <th data-field="lastMaintainTime" data-formatter="formatterDate">
                上次保养时间
            </th>
            <th data-field="lastMaintainMileage">
                上次保养汽车行驶里程
            </th>
            <th data-field="remindMsg">
                保养提醒内容
            </th>
            <th data-field="remindTime" data-formatter="formatterDate">
                保养提醒时间
            </th>
            <th data-field="remindType" >
                保养提醒方式
            </th>
            <th data-field="remindCreatedTime" data-formatter="formatterDate">
                保养提醒记录创建时间
            </th>
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a><button onclick="showAddWin();" type="button" id="add" class="btn btn-default" >
                <i class="glyphicon glyphicon-plus"></i> 添加
            </button></a>
            <a><button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 修改
            </button></a>

        </div>
        </tbody>

    </table>
</div>



<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改维修保养提醒</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="reminder.remindId" name="remindId" />
                            <div class="form-group">
                                <label>用户名:</label>
                                <input type="text" readonly="readonly" attr="reminder.user.userName" class="js-example-tags form-control com_name" name="user.userName">
                                </input>
                            </div>
                            <div class="form-group">
                                <label class="control-label">上次保养时间：</label>
                                <input type="text"  readonly="readonly" attr="reminder.lastMaintainTime"  id="editLastMaintainTime" onclick="getDate()" name="lastMaintainTime" class="form-control datatimepicker"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">上次保养汽车行驶里程：</label>
                                <input type="text"  readonly="readonly"  name="lastMaintainMileage" attr="reminder.lastMaintainMileage" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">保养提醒时间：</label>
                                <input type="text" readonly="readonly" attr="reminder.remindTime" id="editRemindTime" name="remindTime" class="form-control nowDatrtime"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">保养提醒记录创建时间：</label>
                                <input type="text"  readonly="readonly"  attr="reminder.remindCreatedTime" id="editRemindCreatedTime" onclick="getDate()"  name="remindCreatedTime" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">保养提醒方式：</label>
                                <input type="text"   name="remindType" attr="reminder.remindType" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">保养提醒内容：</label>
                                <textarea class="form-control" attr="reminder.remindMsg" type="textarea" name="remindMsg" maxlength="400"
                                          rows="3"></textarea>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" onclick="edit()" class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加回复</h3>
                        <form role="form" id="addForm" >
                            <div class="form-group">
                                <label>受理人：</label>
                                <select class="js-example-tags form-control com_name" name="user.userName">
                                </select>
                            </div>
                            <div class="form-group">
                                <label>受理时间：</label>
                                <input id="complaintReplyTime" type="text" name="complaintReplyTime"
                                       class="form-control datatimepicker"/>
                            </div>
                            <div class="form-group">
                                <label>回复内容：</label>
                                <textarea class="form-control" name="complaintReply" id="complaintReply"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <span id="error" style="color: #ff0000;"></span>
                                <br/>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary" onclick="addCompaint()" value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none;" />
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
<script src="<%=path %>/js/customerRelations/message_reminder.js"></script>
</body>
</html>
