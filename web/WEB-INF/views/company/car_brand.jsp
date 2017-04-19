<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>品牌管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">

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
            <th  data-field="brandName" data-sortable="true">
                汽车品牌名称
            </th>
            <th data-field="brandDes" >
                汽车品牌描述
            </th>
            <th data-field="brandStatus" data-formatter="operateFormatter">
                汽车品牌状态
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
            <a><button onclick="EditStatus();" type="button" id="status" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 激活
            </button></a>
            <a><button onclick="StatusIncomeing();" type="button"  class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 冻结
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
                        <h3 class="m-t-none m-b">修改汽车品牌</h3>
                        <form role="form" id="updateForm" >
                            <input type="hidden" attr="carBrand.brandId" name="brandId" id = "id"/>
                            <input type="hidden" attr="carBrand.brandStatus" name="brandStatus"/>
                            <div class="form-group">
                                <label>汽车品牌名称：</label>
                                <input type="text"  name="brandName"  attr="carBrand.brandName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车品牌描述：</label>
                                <textarea attr="carBrand.brandDes" name="brandDes" type="textarea" cols="20" rows="5" class="form-control"></textarea>
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
                        <h3 class="m-t-none m-b">添加汽车品牌</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>汽车品牌名称：</label>
                                <input type="text"   name="brandName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车品牌描述：</label>
                                <textarea name="brandDes" cols="20" rows="5" class="form-control" ></textarea>
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
<script src="<%=path%>/js/main.js"></script>
<script src="<%=path%>/js/company/car_brand.js"></script>
</body>
</html>
