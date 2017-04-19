<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>汽车颜色管理</title>
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
            <th data-field="colorName" data-sortable="true">
                汽车颜色名称
            </th>
            <th data-field="colorRGB">
                汽车颜色RGB
            </th>
            <th data-field="colorHex">
                汽车颜色Hex
            </th>

            <th data-field="colorDes">
                汽车颜色描述
            </th>
            <th data-field="colorStatus" data-formatter="operateFormatter">
                汽车颜色状态
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a href="#addWin" data-toggle="modal">
                <button type="button" id="add" class="btn btn-default">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                </button>
            </a>
            <a>
                <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 修改
                </button>
            </a>
            <a>
                <button onclick="EditStatus();" type="button" id="status" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 激活
                </button>
            </a>
            <a>
                <button onclick="StatusIncomeing();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 冻结
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
                        <h3 class="m-t-none m-b">修改汽车品牌</h3>
                        <form role="form" id="updateForm">
                            <input type="hidden" attr="carColor.colorId" name="colorId" id="id"/>
                            <input type="hidden" attr="carColor.colorStatus" name="colorStatus"/>
                            <div class="form-group">
                                <label>汽车颜色名称：</label>
                                <input type="text" name="colorName" attr="carColor.colorName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色RGB：</label>
                                <input type="text" name="colorRGB" attr="carColor.colorRGB" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色Hex：</label>
                                <input type="text" name="colorHex" attr="carColor.colorHex" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control"
                                          attr="carColor.colorDes" type="textarea"></textarea>
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
                        <h3 class="m-t-none m-b">添加汽车颜色</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>汽车颜色名称：</label>
                                <input type="text" name="colorName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色RGB：</label>
                                <input type="text" name="colorRGB" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色Hex：</label>
                                <input type="text" name="colorHex" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control"></textarea>
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
<script src="<%=path%>/js/company/car_color.js"></script>
<script>

</script>
</body>
</html>
