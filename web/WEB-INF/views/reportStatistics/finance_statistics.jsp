<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>财务统计</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
    <div class="btn-group" data-toggle="buttons" style="margin-top:20px;">
        <label class="btn btn-primary" id="showButton1" onclick="showSearchForm(1)">
            <input type="checkbox" autocomplete="off"> 按年查询
        </label>
        <label class="btn btn-primary" onclick="showSearchForm(2)" id="showButton2">
            <input type="checkbox" autocomplete="off"> 按季度查询
        </label>
        <label class="btn btn-primary" onclick="showSearchForm(3)" id="showButton3">
            <input type="checkbox" autocomplete="off"> 按月查询
        </label>
        <label class="btn btn-primary" onclick="showSearchForm(4)" id="showButton4">
            <input type="checkbox" autocomplete="off"> 按周查询
        </label>
        <label class="btn btn-primary" onclick="showSearchForm(5)" id="showButton5">
            <input type="checkbox" autocomplete="off"> 按日查询
        </label>
    </div>
</div>
<div class="container">
    <form id="formSearch" class="form-horizontal">
        <div class="form-group" id="searchDiv1" style="margin-top:15px; display: none;">
            <div class="col-sm-4">
                <input type="text" readonly placeholder="开始 : 可按 年 " id="start1"  name="startTime"
                       class="form-control datatimepicker"/>
            </div>
            <div class="col-md-1">
                <hr style="color:black"/>
            </div>
            <div class="col-sm-4">
                <input type="text" readonly placeholder="结束 : 可按 年 " id="end1" name="endTime"
                       class="form-control datatimepicker"/>
            </div>

            <div class="col-sm-3">
                <button type="button" onclick="searchCheckin(1)" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeSearchForm(1)" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>

    <form  class="form-horizontal">
        <div class="form-group" id="searchDiv2" style="margin-top:15px; display: none;">
            <div class="col-sm-4">
                <input type="text" readonly placeholder="开始 : 可按 季度" id="start2" name="startTime"
                       class="form-control datatimepicker"/>
            </div>
            <div class="col-md-1">
                <hr style="color:black"/>
            </div>
            <div class="col-sm-4">
                <input type="text" readonly placeholder="结束 : 可按 季度" id="end2" name="endTime"
                       class="form-control datatimepicker"/>
            </div>

            <div class="col-sm-3">
                <button type="button" onclick="searchCheckin(2)" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeSearchForm(2)" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>

    <form  class="form-horizontal">
        <div class="form-group" id="searchDiv3" style="margin-top:15px; display: none;">
            <div class="col-sm-4">
                <input type="text" readonly placeholder="开始 : 可按 月 " id="start3" name="startTime"
                       class="form-control datatimepicker"/>
            </div>
            <div class="col-md-1">
                <hr style="color:black"/>
            </div>
            <div class="col-sm-4">
                <input type="text" readonly placeholder="结束 : 可按 月 " id="end3" name="endTime"
                       class="form-control datatimepicker"/>
            </div>

            <div class="col-sm-3">
                <button type="button" onclick="searchCheckin(3)" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeSearchForm(3)" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>

    <form class="form-horizontal">
    <div class="form-group" id="searchDiv4" style="margin-top:15px; display: none;">
        <div class="col-sm-4">
            <input type="text" readonly placeholder="开始 : 可按 周 " id="start4" name="startTime"
                   class="form-control datatimepicker"/>
        </div>
        <div class="col-md-1">
            <hr style="color:black"/>
        </div>
        <div class="col-sm-4">
            <input type="text" readonly placeholder="结束 : 可按 周 " id="end4"  name="endTime"
                   class="form-control datatimepicker"/>
        </div>

        <div class="col-sm-3">
            <button type="button" onclick="searchCheckin(4)" class="btn btn-primary">
                查询
            </button>
            <button type="button" onclick="closeSearchForm(4)" class="btn btn-default">
                关闭
            </button>
        </div>
    </div>
</form>
    <form class="form-horizontal">
        <div class="form-group" id="searchDiv5" style="margin-top:15px; display: none;">
            <div class="col-sm-4">
                <input type="text" readonly placeholder="开始 : 可按 日 " id="start5" name="startTime"
                       class="form-control datatimepicker"/>
            </div>
            <div class="col-md-1">
                <hr style="color:black"/>
            </div>
            <div class="col-sm-4">
                <input type="text" readonly placeholder="结束 : 可按 日 " id="end5"  name="endTime"
                       class="form-control datatimepicker"/>
            </div>

            <div class="col-sm-3">
                <button type="button" onclick="searchCheckin(5)" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeSearchForm(5)" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>
</div>
    <div id="columnar" style="min-width:400px;height:400px"></div>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/highcharts.js"></script>
<script src="<%=path %>/js/reportStatistics/my-charts.js"></script>
<script src="<%=path %>/js/reportStatistics/finance-statistics.js"></script>
</body>
</html>
