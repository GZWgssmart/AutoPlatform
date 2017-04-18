<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>配件采购管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="container" style="width: 100%">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="500">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <%--<th data-field="accName" data-formatter=""> 配件名称</th>--%>
            <%--<th data-field=" ">配件类别</th>--%>
            <th data-field="accBuyCount">采购数量</th>
            <th data-field="accBuyPrice">采购单价</th>
            <th data-field="accBuyDiscount">采购折扣</th>
            <th data-field="accBuyTotal">采购总价</th>
            <th data-field="accBuyMoney">采购最终价</th>
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
                        <h3 class="m-t-none m-b">修改支出类型</h3>
                        <form role="form" id="updateForm">
                            <input type="hidden" attr="incomingType.inTypeId" name="inTypeId"/>
                            <div class="form-group">
                                <label>支出类型名称：</label>
                                <input type="text" attr="incomingType.inTypeName" name="inTypeName"
                                       class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="updateIncomingType()">
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
                        <h3 class="m-t-none m-b">添加配件采购信息</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>名称：</label>
                                <input type="text" name="accName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>类别：</label>
                                <input type="text" name="accTypeName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>数量：</label>
                                <input type="text" name="accBuyCount" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>单价：</label>
                                <input type="text" name="accBuyPrice" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>折扣：</label>
                                <input type="text" name="accBuyDiscount" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>配件生产商：</label>
                                <input type="text" name="companyId" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accBuyTotal" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accBuyMoney" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>采购时间：</label>
                                <input size="16" type="text" name="sDate" readonly
                                       class="form_datetime form-control " id="accBuyTime">
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="addAccessoriesBuyInfo()"
                                       value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none;"/>
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

<script src="<%=path %>/js/accessories/accessories_buy.js"></script>
<script src="<%=path %>/js/main.js"></script>


<script>


    $("#accBuyTime").datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        language:'ZH_CN',
        todayBtn: true,
        pickerPosition: "bottom-left",
    });

</script>

</body>
</html>
