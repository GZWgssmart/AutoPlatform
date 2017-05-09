<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车型管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="520"
    >
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th  data-field="brand.brandName" data-sortable="true">
                汽车品牌
            </th>
            <th  data-field="modelName" data-sortable="true">
                汽车车型名称
            </th>
            <th data-field="modelDes" >
                汽车车型描述
            </th>
            <th data-field="modelStatus" data-formatter="status">
                汽车车型状态
            </th>
            <th data-field="co" data-formatter="operating" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a><button type="button" id="add" onclick="showAddWin();" class="btn btn-default" >
                <i class="glyphicon glyphicon-plus"></i> 添加
            </button></a>
            <a><button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 修改
            </button></a>

            <a>
                <button onclick="statusUsableness();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>查看可用车型
                </button>
            </a>
            <a>
                <button onclick="statusAvailable();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>查看不可用车型
                </button>
            </a>
            <a>
                <button onclick="modelAll();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>查看所有
                </button>
            </a>
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
                        <h3 class="m-t-none m-b">修改车型信息</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="carModel.modelId" name="modelId"/>
                            <div class="form-group">
                                <label class="control-label">汽车品牌名称：</label>
                                <select class="js-example-tags form-control car_brand" name="brandId">
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">车型名称：</label>
                                <input attr="carModel.modelName" type="text" name="modelName" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">车型描述：</label>
                                <textarea attr="carModel.modelDes" name="modelDes"  type="textarea" cols="20" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="edit();">
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
                        <h3 class="m-t-none m-b">添加汽车车型</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">汽车品牌：</label>
                                <select class="js-example-tags form-control car_brand"  name="brandId">
                                </select>
                            </div>
                            <div class="form-group">
                            <label class="control-label">汽车车型名称：</label>
                            <input type="text"   name="modelName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车车型描述：</label>
                                <textarea name="modelDes" cols="20" rows="5" class="form-control" ></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="add()" value="添加">
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
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path%>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path%>/js/company/car_model.js"></script>
</body>
</html>
