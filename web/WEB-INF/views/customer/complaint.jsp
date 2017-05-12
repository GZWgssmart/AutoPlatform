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
    <title>投诉管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
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
            <th data-field="complaintCreatedTime">
                投诉编号:
            </th>
            <th data-field="admin.userName">
                投诉人
            </th>
            <th data-field="complaintContent">
                投诉内容
            </th>
            <th data-field="complaintCreatedTime" data-formatter="formatterDate">
                投诉时间
            </th>
            <th data-field="customer.userName">
                回复人
            </th>
            <th data-field="complaintReply">
                回复内容
            </th>
            <th data-field="complaintReplyTime" data-formatter="formatterDate">
                回复时间
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
            <a><button onclick="showAdminWin();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i>回复
            </button></a>

        </div>
        </tbody>

    </table>
</div>



<%--<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改投诉信息</h3>
                        <form role="form" id="updateForm" >
                            <input type="hidden" class="userId" name="userId"/>
                            <input type="hidden" class="complaintId" name="complaintId"/>
                            <div class="form-group">
                                <label>投诉内容：</label>
                                <textarea class="form-control" name="complaintReply" id="complaintReplyEidt"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" onclick="edit()" class="btn btn-primary"
                                       value="修改">
                                </input>
                                <input type="reset" name="reset" style="display: none;"/>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>--%>

<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加投诉</h3>
                        <form role="form" id="addForm" >
                            <input type="hidden" class="userId" name="userId"/>
                            <input type="hidden" class="complaintId" name="complaintId"/>
                            <div class="form-group">
                                <label>投诉内容：</label>
                                <textarea class="form-control" name="complaintContent" id="complaint"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" onclick="add()" class="btn btn-primary" value="添加投诉">
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


<div id="adminWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加回复</h3>
                        <form role="form" id="adminForm" >
                            <input type="hidden" class="complaintReplyUser" name="complaintReplyUser"/>
                            <input type="hidden" class="complaintId" attr="Reply.complaintId" name="complaintId"/>
                            <div class="form-group">
                                <label>回复内容：</label>
                                <textarea class="form-control" name="complaintReply" id="complaintReply"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addAdminButton" onclick="addReply()" class="btn btn-primary" value="添加回复">
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

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/customerRelations/complaint.js"></script>
</body>
</html>
