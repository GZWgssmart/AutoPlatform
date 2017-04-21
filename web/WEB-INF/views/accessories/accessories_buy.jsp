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
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
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
            <th data-field="accTypeName">配件类别</th>
            <th data-field="accBuyCount">采购数量</th>
            <th data-field="accBuyPrice">采购单价</th>
            <th data-field="accBuyDiscount">采购折扣</th>
            <th data-field="accBuyTime" data-formatter="formatterDate">采购时间</th>
            <th data-field="accBuyTotal">采购总价</th>
            <th data-field="accUnit">计量单位</th>
            <th data-field="accBuyMoney">采购最终价</th>
            <th data-field="accBuyStatus" data-formatter="status">采购状态</th>
            <th data-formatter="operateFormatter" data-events="operateEvents">操作</th>
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
                        <h3 class="m-t-none m-b">修改采购信息</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="accBuyId" name="accBuyId"/>
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
                                <input type="text" name="accTypeName" attr="accessoriesBuy.accTypeName"
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

                            <div class="form-group">
                                <label>配件生产商：</label>
                                <input type="text" name="" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>采购时间：</label>
                                <input size="16" type="text" name="accBuyTime" readonly
                                       class="form_datetime form-control " attr="accessoriesBuy.accBuyTime" id="buyTime">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accBuyTotal" attr="accessoriesBuy.accBuyTotal"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accBuyMoney" attr="accessoriesBuy.accBuyMoney"
                                       class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改"
                                       onclick="updateAccessoriesBuyInfo()">
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
                            <input type="hidden" attr="acc.accId" name="accId"/>
                            <div class="form-group">
                                <p><label>配件名称：</label></p>
                                <input type="text" name="accessories.accName" class="form-control" id="accName" attr="acc.accName"
                                       style="width:75%;display:initial;"/>
                                <input type="button" class="btn btn-primary" value="从已有库存中选择" onclick="showAccessories()">
                            </div>

                            <div class="form-group">
                                <label>计量单位：</label>
                                <input type="text" name="accUnit" id="accUnit" attr="acc.accUnit" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>类别：</label>
                                <input type="text" name="accTypeName" id="accTypeName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>数量：</label>
                                <input type="text" name="accBuyCount" id="accBuyCount" attr="acc.accTotal" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>单价：</label>
                                <input type="text" name="accBuyPrice" id="accBuyPrice" attr="acc.accPrice" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>折扣：</label>
                                <input type="text" name="accBuyDiscount" id="accBuyDiscount" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>配件供应商：</label>
                                <input type="text" name=""  class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>采购时间：</label>
                                <input size="16" type="text" id="accBuyTime" name="accBuyTime" readonly
                                       class="form_datetime form-control ">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accBuyTotal" id="accBuyTotal" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accBuyMoney" id="accBuyMoney" class="form-control"/>
                            </div>


                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="submit" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <input type="submit" class="btn btn-primary" onclick="addAccessoriesBuyInfo()"
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


<div id="accWin" class="modal fade " data-backdrop="static" aria-hidden="true">
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
                                    <th data-field="supplyId">
                                        供应商
                                    </th>
                                    <th data-field="accCreatedTime" data-formatter="formatterDate">
                                        创建时间
                                    </th>
                                    <th data-field="accTypeId">
                                        所属分类
                                    </th>
                                    <th data-field="companyId">
                                        所属公司
                                    </th>
                                    <th data-field="accStatus">
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

<script src="<%=path %>/js/accessories/accessories_buy.js"></script>
<script src="<%=path %>/js/main.js"></script>

</body>
</html>
