<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/1
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>管理员主页</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/footable/footable.core.css" rel="stylesheet">

    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
<%-- 公司管理员主页 --%>
<div class="container">
    <h3 style="text-align: center;">赣州汽修公司</h3>

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title panel-primary">
                    <h5>最新预约信息</h5>

                    <div class="ibox-tools">
                        <a href="javascript:;" onclick="searchApp()" title="查看更多预约信息">
                            查看更多
                        </a>
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="appTable" class="footable table table-stripped toggle-arrow-tiny"
                           data-page-size="8">
                        <thead>
                        <tr>
                            <th data-toggle="true">车主姓名</th>
                            <th>车主电话</th>
                            <th>汽车品牌</th>
                            <th>汽车颜色</th>
                            <th>汽车车型</th>
                            <th>汽车车牌</th>
                            <th>车牌号码</th>
                            <th data-hide="all">预计到店时间</th>
                            <th data-hide="all">预约状态</th>
                            <th data-hide="all">当前进度</th>
                            <th data-hide="all">维修|保养</th>
                            <th data-hide="all">预约创建时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.apps}" var="app">
                            <tr>
                                <td>${app.userName}</td>
                                <td>${app.userPhone}</td>
                                <td>${app.brand.brandName}</td>
                                <td>${app.color.colorName}</td>
                                <td>${app.model.modelName}</td>
                                <td>${app.plate.plateName}</td>
                                <td>${app.carPlate}</td>
                                <td>
                                    <fmt:formatDate value="${app.arriveTime}" pattern="yyyy-MM-dd HH:mm" />
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${app.appoitmentStatus == 'Y'}">
                                            可用
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">不可用</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${app.speedStatus}</td>
                                <td>${app.maintainOrFix}</td>
                                <td>
                                    <fmt:formatDate value="${app.appCreatedTime}" pattern="yyyy-MM-dd HH:mm" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title panel-success">
                    <h5>最新的登记记录信息</h5>

                    <div class="ibox-tools">
                        <a href="javascript:;" onclick="searchCheckin()" title="查看更多预约信息">
                            查看更多
                        </a>
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="checkinTable" class="footable table table-stripped toggle-arrow-tiny"
                           data-page-size="8">
                        <thead>
                        <tr>

                            <th data-toggle="true">车主姓名</th>
                            <th>车主电话</th>
                            <th>汽车品牌</th>
                            <th>汽车颜色</th>
                            <th>汽车车型</th>
                            <th>汽车车牌</th>
                            <th>车牌号码</th>
                            <th data-hide="all">汽车油量（L）</th>
                            <th data-hide="all">行驶里程（KM）</th>
                            <th data-hide="all">是否洗车</th>
                            <th data-hide="all">到店时间</th>
                            <th data-hide="all">车上物品描述</th>
                            <th data-hide="all">汽车完好度描述</th>
                            <th data-hide="all">用户要求描述</th>
                            <th data-hide="all">维修|保养</th>
                            <th data-hide="all">记录状态</th>
                            <th data-hide="all">登记时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.checkins}" var="checkin">
                            <tr>
                                <td>${checkin.userName}</td>
                                <td>${checkin.userPhone}</td>
                                <td>${checkin.brand.brandName}</td>
                                <td>${checkin.color.colorName}</td>
                                <td>${checkin.model.modelName}</td>
                                <td>${checkin.plate.plateName}</td>
                                <td>${checkin.carPlate}</td>
                                <td>${checkin.oilCount}</td>
                                <td>${checkin.carMileage}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${checkin.carWash == 'Y'}">
                                            是
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">否</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate value="${checkin.arriveTime}" pattern="yyyy-MM-dd HH:mm" />
                                </td>
                                <td>${checkin.carThings}</td>
                                <td>${checkin.intactDegrees}</td>
                                <td>${checkin.userRequests}</td>
                                <td>${checkin.maintainOrFix}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${checkin.checkinStatus == 'Y'}">
                                            可用
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">不可用</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate value="${checkin.checkinCreatedTime}" pattern="yyyy-MM-dd HH:mm" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title panel-warning">
                    <h5>待提醒的车主</h5>

                    <div class="ibox-tools">
                        <a href="javascript:;" onclick="searchRemind()" title="查看更多预约信息">
                            查看更多
                        </a>
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="remindTable" class="footable table table-stripped toggle-arrow-tiny"
                           data-page-size="8">
                        <thead>
                        <tr>

                            <th data-toggle="true">车主姓名</th>
                            <th>车主电话</th>
                            <th>汽车品牌</th>
                            <th>汽车颜色</th>
                            <th>汽车车型</th>
                            <th>汽车车牌</th>
                            <th>车牌号码</th>
                            <th data-hide="all">汽车油量（L）</th>
                            <th data-hide="all">行驶里程（KM）</th>
                            <th data-hide="all">是否洗车</th>
                            <th data-hide="all">到店时间</th>
                            <th data-hide="all">车上物品描述</th>
                            <th data-hide="all">汽车完好度描述</th>
                            <th data-hide="all">用户要求描述</th>
                            <th data-hide="all">维修|保养</th>
                            <th data-hide="all">登记时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>邱康</td>
                            <td>18279799343</td>
                            <td>宝马</td>
                            <td>黑色</td>
                            <td>奇骏</td>
                            <td>赣A</td>
                            <td>23442</td>
                            <td>22</td>
                            <td>123</td>
                            <td>是</td>
                            <td>2017-06-13</td>
                            <td>没有物品</td>
                            <td>特别完好</td>
                            <td>没有要求</td>
                            <td>保养</td>
                            <td>2017-12-13 80:23</td>
                        </tr>
                        <tr>
                            <td>邱康</td>
                            <td>18279799343</td>
                            <td>宝马</td>
                            <td>黑色</td>
                            <td>奇骏</td>
                            <td>赣A</td>
                            <td>23442</td>
                            <td>22</td>
                            <td>123</td>
                            <td>是</td>
                            <td>2017-06-13</td>
                            <td>没有物品</td>
                            <td>特别完好</td>
                            <td>没有要求</td>
                            <td>保养</td>
                            <td>2017-12-13 80:23</td>
                        </tr>
                        </tbody>
                        <tfoot>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title panel-danger">
                    <h5>车主投诉</h5>

                    <div class="ibox-tools">
                        <a href="javascript:;" onclick="searchComplaint()" title="查看更多预约信息">
                            查看更多
                        </a>
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="complaintTable" class="footable table table-stripped toggle-arrow-tiny"
                           data-page-size="8">
                        <thead>
                        <tr>

                            <th data-toggle="true">车主姓名</th>
                            <th>车主电话</th>
                            <th>汽车品牌</th>
                            <th>汽车颜色</th>
                            <th>汽车车型</th>
                            <th>汽车车牌</th>
                            <th>车牌号码</th>
                            <th data-hide="all">汽车油量（L）</th>
                            <th data-hide="all">行驶里程（KM）</th>
                            <th data-hide="all">是否洗车</th>
                            <th data-hide="all">到店时间</th>
                            <th data-hide="all">车上物品描述</th>
                            <th data-hide="all">汽车完好度描述</th>
                            <th data-hide="all">用户要求描述</th>
                            <th data-hide="all">维修|保养</th>
                            <th data-hide="all">登记时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>邱康</td>
                            <td>18279799343</td>
                            <td>宝马</td>
                            <td>黑色</td>
                            <td>奇骏</td>
                            <td>赣A</td>
                            <td>23442</td>
                            <td>22</td>
                            <td>123</td>
                            <td>是</td>
                            <td>2017-06-13</td>
                            <td>没有物品</td>
                            <td>特别完好</td>
                            <td>没有要求</td>
                            <td>保养</td>
                            <td>2017-12-13 80:23</td>
                        </tr>
                        <tr>
                            <td>邱康</td>
                            <td>18279799343</td>
                            <td>宝马</td>
                            <td>黑色</td>
                            <td>奇骏</td>
                            <td>赣A</td>
                            <td>23442</td>
                            <td>22</td>
                            <td>123</td>
                            <td>是</td>
                            <td>2017-06-13</td>
                            <td>没有物品</td>
                            <td>特别完好</td>
                            <td>没有要求</td>
                            <td>保养</td>
                            <td>2017-12-13 80:23</td>
                        </tr>
                        </tbody>
                        <tfoot>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>

<%-- 员工主页 --%>
<%--<div class="container">
    <h3 style="text-align: center;">赣州汽修公司</h3>
    <div class="row" style="text-align: center; padding-top: 20px;">
        <img src="<%=path %>/img/company.jpg" style="width: 90%; height: 78%"/>
    </div>
    <div class="row">
        <div class="col-sm-9"></div>
        <div class="col-sm-3">
            <b>联系方式：</b><span style="color: red;">0808-2342342</span>
            <br/>
            <b>公司官网：</b><a href="#" title="赣州汽修公司">www.baidu.com</a>
        </div>
    </div>

</div>--%>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/plugins/footable/footable.all.min.js"></script>
<script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/company/home.js"></script>
</body>
</html>
