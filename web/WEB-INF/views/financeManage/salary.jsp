
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>收入类型管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

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
            <th data-field="user.userName">
                名称
            </th>
            <th data-field="prizeSalary">
                奖金
            </th>
            <th data-field="minusSalary">
                罚金
            </th>
            <th data-field="totalSalary">
                总工资
            </th>
            <th data-field="salaryDes">
                工资描述
            </th>
            <th data-field="salaryTime" data-formatter="formatterDate">
                发放时间
            </th>
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
        </div>
        </tbody>

    </table>
</div>


<div id="editWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改工资</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" name="salaryId" attr="salary.salaryId"/>
                            <input type="hidden" class="userId" name="userId" attr="salary.userId"/>
                            <div class="form-group">
                                <label class="control-label">员工：</label>
                                <div class="row">
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control userName"  name="userName"  attr="salary.user.userName" />
                                    </div>
                                    <div class="col-xs-3">
                                        <input class="btn btn-primary" type="button" onclick="showUserWin()" value="选择员工">
                                        </input>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">奖金：</label>
                                <input type="number" name="prizeSalary" attr="salary.prizeSalary" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">罚金：</label>
                                <input type="number" name="minusSalary" attr="salary.minusSalary" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">发放时间：</label>
                                <input type="text" readonly onclick="getDate()" id="editTime" name="salaryTime"
                                       class="form-control datatimepicker"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">描述：</label>
                                <textarea class="form-control" type="textarea" attr="salary.salaryDes" name="salaryDes"
                                          maxlength="400"
                                          rows="3"></textarea>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" onclick="edit()" class="btn btn-primary"
                                       value="修改">
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

<div id="addWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-keyboard="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加工资</h3>
                        <form role="form" id="addForm">
                            <input type="hidden" class="userId" name="userId"/>
                            <div class="form-group">
                                <label class="control-label">员工：</label>
                                <div class="row">
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control userName" name="userName"/>
                                    </div>
                                    <div class="col-xs-3">
                                        <input class="btn btn-primary" type="button" onclick="showUserWin()" value="选择员工">
                                        </input>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">奖金：</label>
                                <input type="number" name="prizeSalary" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">罚金：</label>
                                <input type="number" name="minusSalary" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">发放时间：</label>
                                <input type="text" readonly id="datatimepicker" onclick="getDate()" name="salaryTime"
                                       class="form-control datatimepicker"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">描述：</label>
                                <textarea class="form-control" name="salaryDes" maxlength="400"
                                          rows="3"></textarea>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" onclick="add()" class="btn btn-primary" value="添加">
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

<div id="userWin" class="modal fade" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 85%">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">查询员工</h3>
                        <table class="table table-hover" id="userTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="userId" data-sortable="true" data-formatter="random">
                                    人员编号
                                </th>
                                <th data-field="userName">
                                    姓名
                                </th>
                                <th data-field="userEmail">
                                    邮箱
                                </th>
                                <th data-field="userGender" data-formatter="gender">
                                    性别
                                </th>
                                <th data-field="userPhone">
                                    手机号
                                </th>
                                <th data-field="company.companyName">
                                    所属公司
                                </th>
                                <th data-field="userSalary">
                                    基本工资
                                </th>
                                <th data-field="userStatus" data-formatter="status">
                                    当前状态
                                </th>
                                <th data-field="operate" data-formatter="userFormatter" data-events="userEvents">
                                    操作
                                </th>
                            </tr>
                            </thead>
                        </table>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <input type="button" onclick="addUserName()" class="btn btn-primary" value="选择">
                            </input>
                        </div>
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
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/financeManage/salary.js"></script>
<script src="<%=path%>/js/main.js"></script>

</body>
</html>
