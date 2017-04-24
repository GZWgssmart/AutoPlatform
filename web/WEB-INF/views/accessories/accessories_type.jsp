<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>

    <title>配件分类管理</title>

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">

</head>

<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th  data-field="accTypeName" data-sortable="true">
                配件分类名称
            </th>
            <th data-field="accTypeDes" >
                配件分类描述
            </th>
            <th data-field="company.companyName" >
                配件分类所属公司
            </th>
            <th data-field="accTypeStatus" data-formatter="thisStatus">
                状态
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
            <a><button onclick="queryAll()" type="button" class="btn btn-default" >
                查询全部
            </button></a>
            <a><button onclick="queryStatus('Y')" type="button" class="btn btn-default" >
                查可用模块
            </button></a>
            <a><button onclick="queryStatus('N')" type="button" class="btn btn-default" >
                查不可用模块
            </button></a>
        </div>
        </tbody>

    </table>
</div>

<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加配件分类</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>配件分类名称：</label>
                                <input type="text"   name="accTypeName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>配件所属公司：</label>
                                <select id="addCompany" class="js-example-tags form-control accType_company" name="companyId"></select>
                            </div>
                            <div class="form-group">
                                <label>配件分类描述：</label>
                                <input type="text"  name="accTypeDes"
                                       class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button"id="addButton"  class="btn btn-primary" onclick="add()" value="添加">
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

<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改配件分类</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="accessoriesType.accTypeId" name="accTypeId" id = "accTypeId"/>
                            <div class="form-group">
                                <label>配件分类名称：</label>
                                <input type="text" attr="accessoriesType.accTypeName" name="accTypeName" id="accTypeName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>配件所属公司：</label>
                                <select id="editCompany" class="js-example-tags form-control accType_company" name="companyId"></select>
                            </div>
                            <div class="form-group">
                                <label>配件分类描述：</label>
                                <input type="text"  name="accTypeDes" id = "accTypeDes" attr="accessoriesType.accTypeDes"
                                       class="form-control"/>
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
<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/accessories/accessories-type.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>

</body>
</html>