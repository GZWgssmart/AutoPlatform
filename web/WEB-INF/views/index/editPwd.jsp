<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人资料</title>


    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/message.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/style.min862f.css" rel="stylesheet" type="text/css">


</head>
<body>

<div class="wrapper">
    <div class="modal-content">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="selfPwd">
                        <br/>
                        <br/>
                        <form role="form" method="get">
                            <div class="row">
                                <div class="form-group has-feedback">
                                    <label class="control-label">旧密码：</label>
                                    <input class="form-control" style="display: initial;" id="oldPwd" placeholder="请输入旧密码" />
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="form-group has-feedback">
                                    <label class="control-label">新密码：</label>
                                    <input class="form-control" style="display: initial;" id="pwd" placeholder="请输入新密码"/>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="form-group has-feedback">
                                    <label class="control-label">确认密码：</label>
                                    <input class="form-control" style="display: initial;" id="rePwd" placeholder="请输入确认密码"/>
                                </div>
                            </div>
                            <br/>
                            <button type="button" class="btn btn-danger btn-block btn-flat" onclick="editPwd();">提交</button>
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
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
<script src="<%=path %>/js/index/editPwd.js"></script>

</body>
</html>
