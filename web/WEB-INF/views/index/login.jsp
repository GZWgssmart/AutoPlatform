<%--
  Created by IntelliJ IDEA.
  User: Xiao-Qiang
  Date: 2017/5/3
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">


    <title>登录</title>
    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=path%>/css/font-awesome.min93e3.css" rel="stylesheet"/>
    <link href="<%=path%>/css/animate.min.css" rel="stylesheet"/>
    <link href="<%=path%>/css/style.min862f.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
<c:if test="${requestScope.loginInfo eq '0'}">
    <script>
        alert("账号或密码有误!");
    </script>
</c:if>
<c:if test="${requestScope.regInfo eq '1' }">
    <script>
        alert("注册成功,点击确定跳转到登陆页!");
    </script>
</c:if>
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">H+</h1>

        </div>
        <h3>欢迎登陆</h3>

        <form class="m-t" role="form" action="<%=path%>/login/login" method="post">
            <div class="form-group">
                <input type="text" name="number" class="form-control" placeholder="账号"
                       required="">
            </div>
            <div class="form-group">
                <input type="password" name="pwd" class="form-control" placeholder="密码" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登
                录
            </button>


            <p class="text-muted text-center">
                <a href="login.html#">
                    <small>忘记密码了？</small>
                </a> | <a
                    href="<%=path %>/login/showRegister">注册一个新账号</a>
            </p>

        </form>
    </div>
</div>
<script src="<%=path%>/js/jquery.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
</body>
</html>
