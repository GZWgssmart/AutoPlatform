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
    <title>供应商分类管理</title>
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
            <th data-field="id" data-checkbox="true"></th>
            <th data-field="supplyTypeName" data-sortable="true">
                分类名称
            </th>
            <th data-field="supplyTypeDes" >
                分类描述
            </th>
            <th data-field="companyId" >
                公司
            </th>
            <th data-field="supplyTypeStatus" >
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
            <a><button type="button" onclick="deleteSupplyType();" id="delete" class="btn btn-default">
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
                        <h3 class="m-t-none m-b">修改供应商分类</h3>
                        <form role="form" id="updateForm" >
                            <input type="hidden" attr="supplyType.supplyTypeId" name="supplyTypeId" id = "supplyTypeId"/>
                            <div class="form-group">
                                <label>供应商分类名称：</label>
                                <input type="text" attr="supplyType.supplyTypeName" name="supplyTypeName" id="supplyTypeName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>供应商分类描述：</label>
                                <input type="text"  name="supplyTypeDes" attr="supplyType.supplyTypeDes" id ="supplyTypeDes"
                                       class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="updateSupplyType()">
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
                        <h3 class="m-t-none m-b">添加供应商分类</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>供应商分类名称：</label>
                                <input type="text" name="supplyTypeName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>供应商分类描述：</label>
                                <input type="text" name="supplyTypeDes" class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="addSupplyType()" value="添加">
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
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/supply/supply_type.js"></script>
<script type="text/javascript">

    /**
     * 批量删除数据
     */
    function deleteSupplyType() {
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
                                $.get("/supplyType/deleteById/"+rows[0].ids,
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
