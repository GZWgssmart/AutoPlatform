<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>供应商信息管理</title>
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
            <th data-field="state" data-checkbox="true">编号</th>
            <th  data-field="supplyName" data-sortable="true">
                名称
            </th>
            <th data-field="suppleTel" >
                电话
            </th>
            <th data-field="supplyPricipal" >
                负责人
            </th>
            <th data-field="supplyAddress" >
                地址
            </th>
            <th data-field="supplyBank" >
                开户银行
            </th>
            <th data-field="supplyBankAccount" >
                开户人
            </th>
            <th data-field="supplyBankNo" >
                开户卡号
            </th>
            <th data-field="supplyAlipay" >
                支付宝
            </th>
            <th data-field="supplyWechat" >
                微信
            </th>
            <th data-field="sypplyCreatedTime" >
                创建时间
            </th>
            <th data-field="supplyType" >
                类型
            </th>
            <th data-field="supplyStatus" >
                状态
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
            <a><button type="button" onclick="deleteSupply();" id="delete" class="btn btn-default">
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
                        <h3 class="m-t-none m-b">修改供应商</h3>
                        <form role="form" id="updateForm" >
                            <input type="hidden" attr="product.id" name="id" id = "id"/>
                            <div class="form-group">
                                <label>名称：</label>
                                <input type="text"   name="name" class="form-control"/>
                                <label>电话：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>负责人：</label>
                                <input type="text"  name="price" class="form-control"/>
                                <label>地址：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>开户银行全称：</label>
                                <input type="text"  name="des" class="form-control"/>
                                <label>开户人姓名：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>开户卡号：</label>
                                <input type="number"  name="des" class="form-control"/>
                                <label>支付宝：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>微信：</label>
                                <input type="text"  name="des" class="form-control"/>
                                <label>供应商分类：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>创建时间：</label>
                                <input type="date"  name="des" class="form-control"/>
                                <label>所属公司：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="updateSupply()">
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
                        <h3 class="m-t-none m-b">添加供应商</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>名称：</label>
                                <input type="text"   name="name" class="form-control"/>
                                <label>电话：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>负责人：</label>
                                <input type="text"  name="price" class="form-control"/>
                                <label>地址：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>开户银行全称：</label>
                                <input type="text"  name="des" class="form-control"/>
                                <label>开户人姓名：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>开户卡号：</label>
                                <input type="number"  name="des" class="form-control"/>
                                <label>支付宝：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>微信：</label>
                                <input type="text"  name="des" class="form-control"/>
                                <label>供应商分类：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>创建时间：</label>
                                <input type="date"  name="des" class="form-control"/>
                                <label>所属公司：</label>
                                <input type="text"   name="name" class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="addSupply()" value="添加">
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
            url: "/supply/queryByPager", //获取数据的Servlet地址
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
    function updateSupply() {
        $.post("/supply/update",
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
    function addSupply() {
        $.post("/supply/add",
                $("#addForm").serialize(),
                function(data){
                    if(data.result == "success"){
                        $('#addWin').modal('hide');
                        swal(data.message, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    }else if(data.result == "fail"){
                        swal(data.message, "", "error");
                    }
                },"json");

    }

    /**
     * 批量删除数据
     */
    function deleteSupply() {
        var rows = $("#cusTable").bootstrapTable('getSelections');
        if (rows.length < 1) {
            swal('删除失败', "请选择一条或多条数据进行删除", "error");
        } else {
            var ids = "";
            for(var i = 0, len = rows.length; i < len; i++){
                if(ids == ""){
                    ids = rows[i].id;
                }else{
                    ids += ","+rows[i].id
                }
                if(ids != ""){
                    swal({title: "确定要删除所选数据?",
                                text: "删除后将无法恢复，请谨慎操作！",
                                type: "warning",
                                showCancelButton: true,
                                confirmButtonColor: "#DD6B55",
                                confirmButtonText: "是的，我要删除!",
                                cancelButtonText: "让我在考虑一下....",
                                closeOnConfirm: false },
                            function(){
                                $.get("/supply/deleteById/"+rows[0].ids,
                                        function(data){
                                            swal(data.message, "您已经永久删除了这条信息。", "success");
                                            $('#cusTable').bootstrapTable('refresh');
                                        },"json");
                            });
                }
            }

        }
    }

</script>
</body>
</html>
