<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>维修项目管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
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
            <th data-field="maintainStatus" >
                维修项目状态
            </th>
            <th data-field="coi" data-formatter="operating" data-events="operateEvents">
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
            <a><button type="button" onclick="deleteProduct();" id="delete" class="btn btn-default">
                <i class="glyphicon glyphicon-trash"></i> 删除
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
                        <h3 class="m-t-none m-b">修改商品</h3>
                        <form role="form" id="updateForm" >
                            <input type="hidden" attr="product.id" name="id" id = "id"/>
                            <div class="form-group">
                                <label>商品名称：</label>
                                <input type="text" attr="product.name" name="name" id="name" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>商品价格：</label>
                                <input type="text"  name="price" attr="product.price" id ="price"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>商品描述：</label>
                                <input type="text"  name="des" id = "des" attr="product.des"
                                       class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="updateProduct()">
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
                        <h3 class="m-t-none m-b">添加维修项目</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>维修项目名称：</label>
                                <input type="text"   name="maintainName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>维修所需工时：</label>
                                <input type="text"  name="maintainHour"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>项目基础费用：</label>
                                <input type="text"  name="maintainMoney"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>维修工时费用：</label>
                                <input type="text"  name="maintainManHourFee"
                                       class="form-control"/>
                            </div>
                            <div class="form-group" >
                                <label>公司名称</label>
                                <select class="js-example-tags form-control company" id="" name="companyId">
                                </select>
                            </div>
                            <div class="form-group">
                                <label>维修项目描述:</label>
                                <textarea  name="maintainDes" type="textarea" cols="20" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="addProduct()" value="添加">
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
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>


<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/company/maintainItem.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script>
    $("#establishTime").datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        language:'ZH_CN',
        todayBtn: true,
        pickerPosition: "bottom-left",
    });
</script>
</body>
</html>
