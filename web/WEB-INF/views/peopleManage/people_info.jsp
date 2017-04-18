<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>人员基本信息管理</title>


    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">

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
            <th  data-field="userId" data-sortable="true" data-formatter="random">
                人员编号
            </th>
            <th data-field="userName" >
                姓名
            </th>
            <th data-field="userEmail" >
                邮箱
            </th>
            <th data-field="userPwd" >
                密码
            </th>
            <th data-field="userGender" data-formatter="gender" >
                性别
            </th>
            <th data-field="userPhone" >
                手机号
            </th>
            <th data-field="companyId" >
                所属公司
            </th>
            <th data-field="userAddress" >
                住址
            </th>
            <th data-field="userSalary" >
                基本工资
            </th>
            <th data-field="userStatus" data-formatter="operating" data-events="operateEvents">
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
            <a><button type="button" onclick="deleteProduct();" id="delete" class="btn btn-default">
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
                        <h3 class="m-t-none m-b">修改信息</h3>
                        <form role="form" id="updateForm" >
                            <input type="hidden" attr="user.userId" name="userId" />
                            <div class="form-group">
                                <label>姓名：</label>
                                <input type="text" attr="user.userName" name="userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>密码：</label>
                                <input type="text"  name="userPwd" attr="user.userPwd"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>性别：</label>
                                <input type="text"  name="userGender" attr="user.userGender"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>手机号：</label>
                                <input type="text"  name="userPhone" attr="user.userPhone"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>住址：</label>
                                <input type="text"  name="userAddress" attr="user.userAddress"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>身份证：</label>
                                <input type="text"  name="userIdentity" attr="user.userIdentity"
                                       class="form-control"/>
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
                        <h3 class="m-t-none m-b">添加员工</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>姓名：</label>
                                <input type="text"  name="userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>邮箱：</label>
                                <input type="email" name="userEmail"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>密码：</label>
                                <input type="password" name="userPwd"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>性别：</label>
                                <select class="form-control" name="userGender">
                                    <option value="N" selected = "selected"></option>
                                    <option value="M">男</option>
                                    <option value="F">女</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>手机号：</label>
                                <input type="text" name="userPhone"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>住址：</label>
                                <input type="text" name="userAddress"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>身份证：</label>
                                <input type="text" name="userIdentity"
                                       class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="addProduct()" value="添加">
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
<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/peopleManage/peopleInfo.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
</body>
</html>
