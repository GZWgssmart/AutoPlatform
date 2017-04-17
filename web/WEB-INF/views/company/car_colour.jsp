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
            <th  data-field="colorName" data-sortable="true">
                汽车颜色名称
            </th>
            <th data-field="colorRGB" >
                汽车颜色RGB
            </th>
            <th data-field="colorHex" >
                汽车颜色Hex
            </th>

            <th data-field="colorDes" >
                汽车颜色描述
            </th>
            <th data-field="colorStatus" data-formatter="operateFormatter">
                汽车颜色状态
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
                            <input type="hidden" attr="carColor.colorId" name="brandId" id = "id"/>
                            <input type="hidden" attr="carColor.colorStatus" name="brandStatus"/>
                            <div class="form-group">
                                <label>汽车颜色名称：</label>
                                <input type="text"   name="colorName" attr="carColor.colorName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色RGB：</label>
                                <input type="text"   name="colorRGB" attr="carColor.colorRGB" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色Hex：</label>
                                <input type="text"   name="colorHex" attr="carColor.colorHex" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control" attr="carColor.colorDes" ></textarea>
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
                                <input type="text"   name="colorName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色RGB：</label>
                                <input type="text"   name="colorRGB" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色Hex：</label>
                                <input type="text"   name="colorHex" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control" ></textarea>
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
<script type="text/javascript">
    function initTable() {
        //先销毁表格
        $('#cusTable').bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#cusTable").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: "/carColor/queryByPager", //获取数据的Servlet地址
            striped: false,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 5,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [5, 10, 15, 20, 25],  //记录数可选列表
            search: true,  //是否启用查询
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            strictSearch: true,
            clickToSelect: true,  //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            toolbar : "#toolbar",// 指定工具栏
            sidePagination: "server", //表示服务端请求

            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType : "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    orderNum : $("#orderNum").val()
                };
                return param;
            },
        });
    }

    $(document).ready(function () {
        //调用函数，初始化表格
        initTable();

        //当点击查询按钮的时候执行
        $("#search").bind("click", initTable);
    });

    /** 编辑数据 */
    function showEditWin() {
        var selectRow = $("#cusTable").bootstrapTable('getSelections');
        if (selectRow.length != 1) {
            swal('编辑失败', "只能选择一条数据进行编辑", "error");
            return false;
        } else {
            var product = selectRow[0];
            $("#updateForm").fill(product);
            $("#editWin").modal('show');
        }
    }


    /**提交编辑数据 */
    function updateProduct() {
        $.post("/carColor/uploadCarColor",
            $("#updateForm").serialize(),
            function(data){
                if(data.result == "success"){
                    $('#editWin').modal('hide');
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
                    swal(data.message, "", "error");
                }
            },"json");

    }

    /**提交添加数据 */
    function addProduct() {
        $.post("/carColor/insertCarColor",
            $("#addForm").serialize(),
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    }
    function operateFormatter(value, row, index) {
        if (row.colorStatus == 'Y') {
            return [
                '可用'
            ].join('');
        }else{
            return [
                '不可用'
            ].join('');
        }
    }

    function StatusIncomeing() {
        var rows = $("#cusTable").bootstrapTable('getSelections');
        if (rows.length < 1) {
            swal('冻结失败', "请选择一条或多条数据进行冻结", "error");
        } else {
            var ids = "";
            for(var i = 0, len = rows.length; i < len; i++){
                if(ids == ""){
                    ids = rows[i].id;
                }else{
                    ids += ","+rows[i].id
                }
                if(ids != ""){
                    $.get(contextPath + "/carBrand/StatusInactive"+rows[0].ids,
                        function(data){
                            swal(data.message, "", "success");
                            $('#cusTable').bootstrapTable('refresh');
                        },"json");
                }
            }

        }
    }
</script>
</body>
</html>
