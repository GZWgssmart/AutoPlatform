<%--
  Created by IntelliJ IDEA.
  User: xiao-qiang
  Date: 2017/4/13
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>权限管理</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/style.min862f.css" rel="stylesheet" type="text/css">
    <link href="/css/font-awesome.min93e3.css" rel="stylesheet">

    <style>
        .ibox-content span {
            margin-right: 0.5em;
            font-size: 1em;
            margin-top: 0.5em;
            float: left;
            cursor: pointer;
        }

        #permissionY span {
            background-color: #388436;

        }

        #permissionN span {
            background-color: #a99e23;
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox-title">
        <h2>分配权限</h2>
        <div class="input-group">
            <div class="input-group-btn">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">角色类型
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:forEach items="${roles}" var="r">
                        <li onclick="selectRole(this, '${r.roleId}')"><a href="javascript:;">${r.roleDes}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <input id="roleType" type="text" disabled="disabled" placeholder="请选择一个角色" class="form-control">
        </div>

        </br>

        <div  class="input-group" id="selectModule" style="display: none">
            <div class="input-group-btn">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">模块类型
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:forEach items="${modules}" var="m">
                        <li onclick="selectModule(this, '${m.moduleId}')"><a href="javascript:;">${m.moduleName}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <input id="moduleType" type="text" disabled="disabled" placeholder="请选择一个模块" class="form-control">
        </div>
    </div>

    <div class="ibox-content" id="permissionDes" style="display: none">

        <div class="ibox-title">
            <h4>已有的权限</h4>
        </div>
        <div id="permissionY" class="ibox-content" style="height: 170px; OVERFLOW-Y: auto; OVERFLOW-X:hidden;"
             title="已有的权限">
        </div>

        <div class="ibox-title">
            <h4>未有的权限</h4>
        </div>
        <div id="permissionN" class="ibox-content" style="height: 170px; OVERFLOW-Y: auto; OVERFLOW-X:hidden;"
             title="未有的权限">
        </div>
        <div id="btnDiv" style="display: none;">
            <button onclick="addAll();" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                添加所有
            </button>
            <button onclick="delAll();" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                删除所有
            </button>
        </div>
    </div>
</div>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/system/permission.js"></script>
</body>
</html>
