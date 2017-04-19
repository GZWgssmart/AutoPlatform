<%--
  Created by IntelliJ IDEA.
  User: XiaoQiao
  Date: 2017/4/11
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>库存管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
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
            <th data-field="accBuyedTime" data-formatter="formatterDate">
                最近一次购买时间
            </th>
            <th data-field="supplyId" >
                供应商
            </th>
            <th data-field="accCreatedTime" data-formatter="formatterDate">
                创建时间
            </th>
            <th data-field="accTypeId" >
                所属分类
            </th>
            <th data-field="companyId" >
                所属公司
            </th>
            <th data-field="accStatus" >
                状态
            </th>
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
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

<!-- 添加弹窗 -->
<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加配件</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>名称：</label>
                                <input type="text"  name="accName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>数量：</label>
                                <input type="text"  name="accTotal" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>价格：</label>
                                <input type="text" name="accPrice"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>描述：</label>
                                <input type="text" name="accDes"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>商品条码：</label>
                                <input type="text" name="accCommodityCode"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>计量单位：</label>
                                <input type="text" name="accUnit"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>可用数量：</label>
                                <input type="text" name="accIdle"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>售价：</label>
                                <input type="text" name="accSalePrice"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>最近一次领料时间：</label>
                                <input size="16" type="text" name="accUsedTime" readonly class="form_datetime form-control datetimepicker" id="accUsedTime">
                            </div>
                            <div class="form-group">
                                <label>最近一次购买时间：</label>
                                <input type="text" name="accBuyedTime" readonly id="accBuyedTime"
                                       class="form_datetime form-control datetimepicker"/>
                            </div>
                            <div class="form-group">
                                <label>供应商：</label>
                                <input type="text" name="supplyId"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>所属分类：</label>
                                <input type="text" name="accTypeId"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>所属公司：</label>
                                <input type="text" name="companyId"
                                       class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="addAccessories()" value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 修改弹窗 -->
<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改供应商</h3>
                        <form role="form" id="updateForm" >
                            <input type="hidden" attr="accessories.accId" name="accId" id = "accId"/>
                            <div class="form-group">
                                <label>名称：</label>
                                <input type="text" attr="accessories.accName"  name="accName" class="form-control"/>
                                <label>数量：</label>
                                <input type="text"  attr="accessories.accTotal" name="accTotal" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>价格：</label>
                                <input type="text" attr="accessories.accPrice" name="accPrice" class="form-control"/>
                                <label>描述：</label>
                                <input type="text" attr="accessories.accDes"  name="accDes" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>商品条码：</label>
                                <input type="text" attr="accessories.accCommodityCode" name="accCommodityCode" class="form-control"/>
                                <label>计量单位：</label>
                                <input type="text" attr="accessories.accUnit" name="accUnit" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>可用数量：</label>
                                <input type="text" attr="accessories.accIdle" name="accIdle" class="form-control"/>
                                <label>售价：</label>
                                <input type="text" attr="accessories.accSalePrice"  name="accSalePrice" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>最近一次领料时间：</label>
                                <input type="text" attr="accessories.accUsedTime" name="accUsedTime" class="form_datetime form-control datetimepicker"/>

                                <label>最近一次购买：</label>
                                <input type="text" attr="accessories.accBuyedTime"  name="accBuyedTime" class="form_datetime form-control datetimepicker"/>
                            </div>
                            <div class="form-group">
                                <label>供应商：</label>
                                <input type="text" attr="accessories.supplyId" name="supplyId" class="form-control"/>
                                <label>所属分类：</label>
                                <input type="text" attr="accessories.accTypeId"  name="accTypeId" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>所属公司：</label>
                                <input type="text" attr="accessories.companyId" name="companyId" class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="updateAcc()">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- 删除弹窗 -->
<div class="modal fade" id="del" aria-hidden="true">
    <div class="modal-dialog" style="overflow:hidden;">
        <form action="/table/edit" method="post">
            <div class="modal-content">
                <input type="hidden" id="delNoticeId"/>
                <div class="modal-footer" style="text-align: center;">
                    <h2>确认删除吗?</h2>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="sumbit" class="btn btn-primary" onclick="del()">
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/accessories/accessories.js"></script>
<script>




</script>
</body>
</html>