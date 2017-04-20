<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公司基本信息管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
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
            <th data-field="companyName" >
                公司名称
            </th>
            <th data-field="companyAddress" >
                公司地址
            </th>
            <th data-field="companyTel" >
                公司联系方式
            </th>
            <th data-field="companyPricipal" >
                公司负责人
            </th>
            <th data-field="companyWebsite" >
                公司官网URL
            </th>
            <th data-field="companyLogo" >
                公司logo
            </th>
            <th data-field="companyOpenDate" >
                公司创建时间
            </th>
            <th data-field="companySize" >
                公司规模
            </th>
            <th data-field="companyLongitude" >
                公司经度
            </th>
            <th data-field="companyLatitude" >
                公司纬度
            </th>
            <th data-field="companyDes">
                公司描述
            </th>
            <th data-field="companyStatus" data-formatter="status">
                公司状态
            </th>
            <th data-field="co" data-formatter="operating" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a href="#addWin" data-toggle="modal"><button type="button" id="add" class="btn btn-default" >
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
                        <h3 class="m-t-none m-b">添加公司</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>公司名称：</label>
                                <input type="text"   name="companyName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>公司地址：</label>
                                <input type="text"  name="companyAddress"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>公司联系方式：</label>
                                <input type="text"  name="companyTel"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>公司负责人：</label>
                                <input type="text"  name="companyPricipal"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>公司官网</label>
                                <input type="text"  name="companyWebsite"
                                       class="form-control"/>
                            </div>
                            <div class="form-group" >
                                <label>公司规模</label>
                                <select class="form-control" name="companySize">
                                    <option value ="请选择公司规模">请选择公司规模</option>
                                    <option value ="5~10">5~10</option>
                                    <option value="10~50">10~50</option>
                                    <option value="50~100">50~100</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>公司创建时间</label>
                                <input size="16" type="text" name="companyOpenDate" readonly
                                       class="form_datetime form-control " id="establishTime">
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>
                            <div class="form-group">
                                <label>公司纬度</label>
                                <input type="text"  name="companyLongitude"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>公司经度</label>
                                <input type="text"  name="companyLatitude"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>公司描述：</label>
                                <textarea  name="companyDes" type="textarea" cols="20" rows="5" class="form-control"></textarea>
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
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>


<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/company/company.js"></script>
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
