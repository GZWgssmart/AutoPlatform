<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>收支管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="450">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="incomingType.inTypeName" >
                收入类型
            </th>
            <th data-field="outgoingType.outTypeName" >
                支出类型
            </th>
            <th data-field="inOutMoney" >
                收支金额
            </th>
            <th data-field="user.userName">
                创建人
            </th>
            <th data-field="inOutCreatedTime" data-formatter="formatterDate">
                创建时间
            </th>
            <th data-field="inOutStatus" data-formatter="status">
                当前状态
            </th>
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>

        <tbody>

        <div id="toolbar" class="btn-group">

            <a><button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 修改
            </button></a>

            <a><button onclick="queryByInOutType(1);" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看所有支出
            </button></a>

            <a><button onclick="queryByInOutType(2);" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看所有收入
            </button></a>

            <a><button onclick="queryByInOutType(3);" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看收支
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
                        <h3 class="m-t-none m-b">修改收支记录</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="incomingOutgoing.inOutId" name="inOutId" />
                            <div class="form-group">
                                <label class="control-label">收支记录金额：</label>
                                <input type="text"   attr="incomingOutgoing.inOutMoney" name="inOutMoney"  class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">

                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton1" class="btn btn-primary" value="修改" onclick="edit()">
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
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/financeManage/incoming-outgoing.js"></script>
<script src="<%=path%>/js/main.js"></script>

</body>
</html>
