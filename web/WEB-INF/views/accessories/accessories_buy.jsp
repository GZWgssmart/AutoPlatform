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
                       class="form_datetime form-control " id="buyTimeStart" placeholder="请选择开始时间">
                <span class="add-on"><i class="icon-remove"></i></span>
            </div>
            <div class="col-sm-2">
                <input size="16" type="text" readonly
                       class="form_datetime form-control " id="buyTimeEnd" placeholder="请选择结束时间">
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

    <table class="table table-hover" id="cusTable"
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
            <%--<th data-field="accessories.company.companyName">供应商</th>--%>
            <th data-field="accBuyCount">采购数量</th>
            <th data-field="accBuyPrice">采购单价</th>
            <th data-field="accBuyDiscount">采购折扣</th>
            <th data-field="accBuyTime" data-formatter="formatterDate">采购时间</th>
            <th data-field="accBuyTotal">采购总价</th>
            <th data-field="accUnit">计量单位</th>
            <th data-field="accBuyMoney">采购最终价</th>
            <th data-field="accBuyCheck" data-formatter="fmtCheckState">审核状态</th>
            <th data-field="accBuyStatus" data-formatter="fmtBuyState">采购状态</th>
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
                <button onclick="delteleBuy();" type="button" id="remove" class="btn btn-danger">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                </button>
            </a>

            <a>
                <button onclick="onlyCheck();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-ok"></i> 只看已审核
                </button>
            </a>

            <a>
                <button onclick="onlyBuy();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-shopping-cart"></i> 只看已采购
                </button>
            </a>


            <a>
                <button onclick="showSearchForm();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-filter"></i>条件查询
                </button>
            </a>

            <a>
                <button onclick="allBuys();" type="button" class="btn btn-default">查看所有</button>
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
                        <h3 class="m-t-none m-b">修改采购信息</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="accessoriesBuy.accBuyId" name="accBuyId"/>
                            <input type="hidden" attr="accessoriesBuy.accessories.accId" name="accessories.accId"/>
                            <input type="hidden" attr="accessoriesBuy.accessories.accessoriesType.accTypeId"
                                   name="accessories.accessoriesType.accTypeId"/>
                            <div class="form-group">
                                <label>配件名称：</label>
                                <input type="text" name="accessories.accName" attr="accessoriesBuy.accessories.accName"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>计量单位：</label>
                                <input type="text" name="accUnit" attr="accessoriesBuy.accUnit" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>类别：</label>
                                <input type="text" name="accessories.accessoriesType.accTypeName"
                                       attr="accessoriesBuy.accessories.accessoriesType.accTypeName"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>数量：</label>
                                <input type="text" name="accBuyCount" attr="accessoriesBuy.accBuyCount"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>单价：</label>
                                <input type="text" name="accBuyPrice" attr="accessoriesBuy.accBuyPrice"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>折扣：</label>
                                <input type="text" name="accBuyDiscount" attr="accessoriesBuy.accBuyDiscount"
                                       class="form-control"/>
                            </div>

                            <%--<div class="form-group">--%>
                            <%--<label>供应商：</label>--%>
                            <%--<input type="text" name="accessories.company.companyName" class="form-control"/>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label>采购时间：</label>
                                <input size="16" type="text" name="accBuyTime" readonly
                                       class="form_datetime form-control " attr="accessoriesBuy.accBuyTime"
                                       id="buyTime">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accBuyTotal" attr="accessoriesBuy.accBuyTotal"
                                       onfocus="autoCalculation(this)"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accBuyMoney" attr="accessoriesBuy.accBuyMoney"
                                       onfocus="autoCalculation(this)"
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
                        <h3 class="m-t-none m-b">添加配件采购信息</h3>
                        <form role="form" id="addForm">
                            <input type="hidden" attr="acc.accId" name="accId"/>
                            <input type="hidden" attr="acc.accId" name="accessories.accId"/>
                            <div class="form-group" style="width: auto; display: inherit;">
                                <label>是否从库存中添加：</label>
                                <input type="checkbox" id="isAcc" name="isAcc">
                            </div>
                            <div class="form-group">
                                <label>配件名称：</label>
                                <input type="text" name="accessories.accName" class="form-control" id="accName"
                                       attr="acc.accName"/>
                            </div>

                            <div class="form-group">
                                <label>计量单位：</label>
                                <input type="text" name="accessories.accUnit" id="aAccUnit" attr="acc.accUnit"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>类别：</label>
                                <input type="text" name="accessories.accessoriesType.accTypeName" id="accTypeName"
                                       attr="acc.accessoriesType.accTypeName"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>数量：</label>
                                <input type="text" name="accBuyCount" id="accBuyCount" attr="acc.accIdle"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>单价：</label>
                                <input type="text" name="accBuyPrice" id="accBuyPrice" attr="acc.accPrice"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>折扣：</label>
                                <input type="tel" name="accBuyDiscount" id="accBuyDiscount" title="如：7.8折是0.78、5折是0.5"
                                       class="form-control"/>
                            </div>

                            <%--<div class="form-group">--%>
                            <%--<label>配件供应商：</label>--%>
                            <%--<input type="text" name="accessories.company.companyName" id="aCompanyName"--%>
                            <%--class="form-control"/>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label>采购时间：</label>
                                <input size="16" type="text" id="accBuyTime" name="accBuyTime" readonly
                                       class="form_datetime form-control ">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accBuyTotal" id="accBuyTotal" onfocus="autoCalculation(this)"
                                       class="form-control" placeholder="总价可自动计算"/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accBuyMoney" id="accBuyMoney" onfocus="autoCalculation(this)"
                                       class="form-control" placeholder="最终价可自动计算"/>
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
                                    <th data-field="accName" data-sortable="true">
                                        名称
                                    </th>
                                    <th data-field="accTotal">
                                        数量
                                    </th>
                                    <th data-field="accPrice">
                                        价格
                                    </th>
                                    <th data-field="accDes">
                                        描述
                                    </th>
                                    <th data-field="accCommodityCode">
                                        商品条码
                                    </th>
                                    <th data-field="accUnit">
                                        计量单位
                                    </th>
                                    <th data-field="accIdle">
                                        可用数量
                                    </th>
                                    <th data-field="accSalePrice">
                                        售价
                                    </th>
                                    <th data-field="accUsedTime" data-formatter="formatterDate">
                                        最近一次领料时间
                                    </th>
                                    <th data-field="accBuyedTime" data-formatter="formatterDate">
                                        最近一次购买时间
                                    </th>
                                    <th data-field="supply.supplyName">
                                        配件供应商
                                    </th>
                                    <th data-field="accCreatedTime" data-formatter="formatterDate">
                                        创建时间
                                    </th>
                                    <th data-field="accessoriesType.accTypeName">
                                        所属分类
                                    </th>
                                    <th data-field="company.companyName">
                                        所属公司
                                    </th>
                                    <th data-field="accStatus" data-formatter="status">
                                        状态
                                    </th>
                                </tr>
                                </thead>
                            </table>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="添加" id="addAccBtn"
                                       onclick="addAccBuy()">
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

<script src="<%=path %>/js/accessories/accessories_buy.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>

</body>
</html>
