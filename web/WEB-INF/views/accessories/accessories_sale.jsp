<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>配件销售管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container" style="width: 100%;">
    <form id="formSearch" class="form-horizontal">
        <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
            <div class="col-sm-2">
                <input size="16" type="text" readonly
                       class="form_datetime form-control " id="SaleTimeStart" placeholder="请选择开始时间">
                <span class="add-on"><i class="icon-remove"></i></span>
            </div>
            <div class="col-sm-2">
                <input size="16" type="text" readonly
                       class="form_datetime form-control " id="SaleTimeEnd" placeholder="请选择结束时间">
            </div>

            <div class="col-sm-2" style="margin-left: -15px;">
                <input type="text" id="sAccName" class="form-control" placeholder="请输入配件名">
            </div>

            <div class="col-sm-2">
                <button type="button" onclick="byAccNameSearch()" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>

    <table class="table table-hover" id="saleTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="500">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="accessories.accName">配件名称</th>
            <th data-field="accessories.accessoriesType.accTypeName">配件类别</th>
            <th data-field="accSaleTotal">销售数量</th>
            <th data-field="accSalePrice">销售单价</th>
            <th data-field="accSaleDiscount">销售折扣</th>
            <th data-field="accSaledTime" data-formatter="formatterDate">销售时间</th>
            <th data-field="accSaleTotal">销售总价</th>
            <th data-field="accessories.accUnit">计量单位</th>
            <th data-field="accSaleMoney">销售最终价</th>
            <th data-field="accSaleStatus" data-formatter="fmtSaleState">状态</th>
            <th data-formatter="fmtOperate" data-events="operateEvents">操作</th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group" style="margin: 10px 0px 10px 0px;">
            <a data-toggle="modal">
                <button type="button" onclick="showAccAddWin()" id="add" class="btn btn-default">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                </button>
            </a>

            <a>
                <button onclick="delteleSale();" type="button" id="remove" class="btn btn-danger">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                </button>
            </a>

            <a>
                <button onclick="onlyCheck();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-ok"></i> 只看已审核
                </button>
            </a>

            <a>
                <button onclick="onlySale();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-shopping-cart"></i> 只看已销售
                </button>
            </a>


            <a>
                <button onclick="showSearchForm();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-filter"></i>条件查询
                </button>
            </a>

            <a>
                <button onclick="allSales();" type="button" class="btn btn-default">查看所有</button>
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
                        <h3 class="m-t-none m-b">修改销售信息</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="accessoriesSale.accSaleId" name="accSaleId"/>
                            <input type="hidden" attr="accessoriesSale.accessories.accId" name="accessories.accId"/>
                            <input type="hidden" attr="accessoriesSale.accessories.accessoriesType.accTypeId"
                                   name="accessories.accessoriesType.accTypeId"/>
                            <div class="form-group">
                                <label>配件名称：</label>
                                <input type="text" name="accessories.accName" attr="accessoriesSale.accessories.accName"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>计量单位：</label>
                                <input type="text" name="accUnit" attr="accessoriesSale.accessories.accUnit" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>类别：</label>
                                <input type="text" name="accessories.accessoriesType.accTypeName"
                                       attr="accessoriesSale.accessories.accessoriesType.accTypeName"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>数量：</label>
                                <input type="text" name="accSaleCount" attr="accessoriesSale.accSaleCount"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>单价：</label>
                                <input type="text" name="accSalePrice" attr="accessoriesSale.accSalePrice"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>折扣：</label>
                                <input type="text" name="accSaleDiscount" attr="accessoriesSale.accSaleDiscount"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>销售时间：</label>
                                <input size="16" type="text" name="accSaledTime" readonly
                                       class="form_datetime form-control " attr="accessoriesSale.accSaledTime"
                                       id="saleTime">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accSaleTotal" attr="accessoriesSale.accSaleTotal"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accSaleMoney" attr="accessoriesSale.accSaleMoney"
                                       class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" id="editButton" value="修改"
                                       onclick="edit()">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="addWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-keyboard="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加配件销售信息</h3>
                        <form role="form" id="addForm">
                            <input type="hidden" attr="acc.accId" name="accId"/>

                            <div class="form-group" style="width: auto; display: inherit;">
                                <label>从库存中添加：</label>
                                <a data-toggle="modal">
                                    <button type="button" onclick="showAccessories()" class="btn btn-primary">从库存中添加
                                    </button>
                                </a>
                            </div>

                            <div class="form-group">
                                <label>配件名称：</label>
                                <input type="text" name="accessories.accName" class="form-control" id="accName"
                                       attr="acc.accName"/>
                            </div>

                            <div class="form-group">
                                <label>计量单位：</label>
                                <input type="text" name="accUnit" id="aAccUnit" attr="acc.accUnit"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>类别：</label>
                                <input type="text" name="accessories.accessoriesType.accTypeName" id="accTypeName" attr="acc.accessoriesType.accTypeName"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>销售数量：</label>
                                <input type="text" name="accSaleCount" id="accSaleCount" attr="acc.accSaleTotal"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>配件买入单价：</label>
                                <input type="text" name="accPrice" id="accBuyPrice" attr="acc.accPrice"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>配件销售单价：</label>
                                <input type="text" name="accSalePrice" id="accSalePrice"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>折扣：</label>
                                <input type="text" name="accSaleDiscount" id="accSaleDiscount" class="form-control" attr="acc.accDiscount"/>
                            </div>

                            <div class="form-group">
                                <label>销售时间：</label>
                                <input size="16" type="text" id="accSaleTime" name="accSaledTime" attr="acc.accSaledTime" readonly
                                       class="form_datetime form-control ">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accSaleTotal" id="accSaleTotal" attr="acc.accSaleTotal" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accSaleMoney" id="accSaleMoney" attr="acc.accSaleMoney" class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary"
                                       onclick="add()"
                                       value="添加"/>
                                <input type="reset" name="reset" style="display: none;"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="accTypeWin" class="modal fade " aria-hidden="true" style="overflow:scroll"
     data-keyboard="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择配件类别</h3>
                        <form role="form" id="accTypeForm">
                            <table class="table table-hover" id="accTypeTable"
                                   data-pagination="true"
                                   data-show-refresh="true"
                                   data-show-toggle="true"
                                   data-showColumns="true">
                                <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="accTypeName" data-sortable="true">
                                        配件分类名称
                                    </th>
                                    <th data-field="accTypeDes">
                                        配件分类描述
                                    </th>
                                    <th data-field="company.companyName">
                                        配件分类所属公司
                                    </th>
                                    <th data-field="accTypeStatus" data-formatter="status">
                                        状态
                                    </th>
                                </tr>
                                </thead>
                            </table>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="添加"
                                       onclick="add()">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="accWin" class="modal fade " aria-hidden="true" style="overflow:scroll"
     data-keyboard="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择配件</h3>
                        <form role="form" id="accForm">
                            <table class="table table-hover" id="accTable"
                                   data-pagination="true"
                                   data-show-refresh="true"
                                   data-show-toggle="true"
                                   data-showColumns="true">
                                <thead>
                                <tr>
                                    <th data-field="id" data-checkbox="true"></th>
                                    <th  data-field="accName" data-sortable="true">
                                        名称
                                    </th>
                                    <th data-field="accTotal" >
                                        数量
                                    </th>
                                    <th data-field="accPrice" >
                                        价格
                                    </th>
                                    <th data-field="accDes" >
                                        描述
                                    </th>
                                    <th data-field="accCommodityCode" >
                                        商品条码
                                    </th>
                                    <th data-field="accUnit" >
                                        计量单位
                                    </th>
                                    <th data-field="accIdle" >
                                        可用数量
                                    </th>
                                    <th data-field="accSalePrice">
                                        售价
                                    </th>
                                    <th data-field="accUsedTime" data-formatter="formatterDate">
                                        最近一次领料时间
                                    </th>
                                    <th data-field="accSaledTime" data-formatter="formatterDate">
                                        最近一次购买时间
                                    </th>
                                    <th data-field="supply.supplyName" >
                                        配件供应商
                                    </th>
                                    <th data-field="accCreatedTime" data-formatter="formatterDate">
                                        创建时间
                                    </th>
                                    <th data-field="accessoriesType.accTypeName" >
                                        所属分类
                                    </th>
                                    <th data-field="company.companyName" >
                                        所属公司
                                    </th>
                                    <th data-field="accStatus" data-formatter="status" >
                                        状态
                                    </th>
                                </tr>
                                </thead>
                            </table>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="添加"
                                       onclick="addAcc()">
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
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>

<script src="<%=path %>/js/accessories/accessories_sale.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>

</body>
</html>
