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
    <link href="<%=path %>/css/example.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-colorpalette.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
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
            <th data-field="colorHex" data-formatter="colorFormatter">
                汽车颜色
            </th>

            <th data-field="colorDes">
                汽车颜色描述
            </th>
            <th data-field="colorStatus" data-formatter="status">
                汽车颜色状态
            </th>
            <th data-field="co" data-formatter="operating" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a data-toggle="modal">
                <button type="button" id="add" onclick="showAddWin()" class="btn btn-default">
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


<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加汽车颜色</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">汽车颜色名称：</label>
                                <br />
                                <input type="text" name="colorName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车颜色：</label>
                                <span id="span" style='display: inline-block; width: 25px; height: 25px;'></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色RGB：</label>
                                <input id="selected-colorRGB" readonly class="form-control" name="colorRGB">
                            </div>
                            <div class="form-group btn-group">
                                <label class="control-label">汽车颜色Hex：</label>
                                <input id="selected-color" readonly class="form-control" name="colorHex">
                                <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown">点击选择颜色</a>
                                <ul class="dropdown-menu">
                                    <li><div id="colorpalette"></div></li>
                                </ul>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="addProduct()" value="添加">
                                </input>
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
                        <h3 class="m-t-none m-b">添加汽车颜色</h3>
                        <form role="form" id="editForm">
                            <div class="form-group">
                                <label class="control-label">汽车颜色名称：</label>
                                <br />
                                <input type="text" name="colorName" class="form-control" attr="carColor.colorName"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车颜色：</label>
                                <span id="spans" style='display: inline-block; width: 25px; height: 25px;'></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色RGB：</label>
                                <input id="selected-colorRGB1" readonly class="form-control" name="colorRGB" attr="carColor.colorRGB">
                            </div>
                            <div class="form-group btn-group">
                                <label class="control-label">汽车颜色Hex：</label>
                                <input id="selected-color1" readonly class="form-control" name="colorHex" attr="carColor.colorHex">
                                <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown">点击选择颜色</a>
                                <ul class="dropdown-menu">
                                    <li><div id="colorpalette1"></div></li>
                                </ul>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control" attr="carColor.colorDes" type="textarea"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="updateProduct()" value="修改">
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
<script src="<%=path %>/js/bootstrap-colorpalette.js"></script>
<script src="<%=path%>/js/main.js"></script>
<script src="<%=path%>/js/company/car_color.js"></script>
<script>
    $('#colorpalette').colorPalette()
        .on('selectColor', function(e) {
            $('#selected-color').val(e.color);
            $('#selected-colorRGB').val(colorHexToRGB(e.color));
            $("#span").css("background-color", e.color);
        });
</script>
</body>
</html>
