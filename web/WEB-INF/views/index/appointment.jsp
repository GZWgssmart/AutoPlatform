<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/18
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车主预约</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="author" content="" />
    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content="" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:url" content="" />
    <meta name="twitter:card" content="" />

    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">

    <!-- Animate.css -->
    <link rel="stylesheet" href="<%=path%>/css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="<%=path%>/css/icomoon.css">
    <!-- Themify Icons-->
    <link rel="stylesheet" href="<%=path%>/css/themify-icons.css">
    <!-- Bootstrap  -->
    <link rel="stylesheet" href="<%=path%>/css/bootstrap.css">
    <%--下拉框|提示框--%>
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <!-- Magnific Popup -->
    <link rel="stylesheet" href="<%=path%>/css/magnific-popup.css">

    <!-- Owl Carousel  -->
    <link rel="stylesheet" href="<%=path%>/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=path%>/css/owl.theme.default.min.css">

    <!-- Theme style  -->
    <link rel="stylesheet" href="<%=path%>/css/style.css">

    <!-- Modernizr JS -->
    <script src="<%=path%>/js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="<%=path%>/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="gtco-loader"></div>

<div id="page">


    <div class="page-inner">
        <nav class="gtco-nav" role="navigation">
            <div class="gtco-container">

                <div class="row">
                    <div class="col-sm-4 col-xs-12">
                        <div id="gtco-logo"><a href="<%=path%>/index">首页 <em>.</em></a></div>
                    </div>
                    <div class="col-xs-8 text-right menu-1">
                        <ul>
                            <li><a href="<%=path%>/customerClientWeb/tour">功能简介</a></li>
                            <li><a href="<%=path%>/customerClientWeb/features">平台特性</a></li>
                            <li><a href="<%=path%>/customerClientWeb/pricing">产品收费</a></li>
                            <li class="has-dropdown">
                                <a href="#">车主入口</a>
                                <ul class="dropdown">
                                    <li><a href="<%=path%>/customerClientWeb/appointment">车主预约</a></li>
                                    <li><a href="<%=path%>/customerClientWeb/customerCar">进度跟踪</a></li>
                                </ul>
                            </li>
                            <li class="btn-cta"><a href="<%=path%>/customerClientWeb/contact"><span>入驻 我们</span></a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </nav>

        <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image: url(<%=path%>/images/customerCar.jpg)">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">
                        <div class="row row-mt-15em">

                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">提供最人性化的预约方式</span>
                                <h1>车主预约</h1>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </header>


        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-6 animate-box">
                            <h3>预约信息</h3>
                            <form id="appointmentForm">
                                <div class="row form-group">
                                    <div id="customerName" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="customerName">姓名</label>
                                        姓名:<input type="text" id="name" name="userName" class="form-control" maxlength="18" placeholder="请输入预约车主姓名" onblur="checkName(this)">
                                    </div>
                                    <div id="errorName" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successPhone" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="phone">手机号</label>
                                        手机号:<input type="text" id="phone" name="userPhone" class="form-control" maxlength="11" placeholder="请输入预约车主手机号" onblur="checkPhone(this)">
                                    </div>
                                    <div id="errorPhone" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successCompany" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="addCompany">选择公司</label>
                                        选择公司:<select id="addCompany" class="js-example-tags form-control car_company" onchange="checkBrand(this)" name="companyId">
                                    </select>
                                    </div>
                                    <div id="errorCompany" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successBrand" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="addCarBrand">汽车品牌</label>
                                        汽车品牌:<select id="addCarBrand" class="js-example-tags form-control car_brand" onchange="checkBrand(this)" name="brandId">
                                        </select>
                                    </div>
                                    <div id="errorBrand" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successCarModel" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="addCarModel">汽车车型</label>
                                        汽车车型:<select id="addCarModel" class="js-example-tags form-control car_model" name="modelId">
                                        </select>
                                    </div>
                                    <div id="errorCarModel" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successCarColor" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="addCarColor">汽车颜色</label>
                                        汽车颜色:<select id="addCarColor" class="js-example-tags form-control car_color" name="colorId">
                                        </select>
                                    </div>
                                    <div id="errorCarColor" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successCarPlate" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="addCarPlate">汽车车牌</label>
                                        汽车车牌:<select id="addCarPlate" class="js-example-tags form-control car_plate" name="plateId">
                                        </select>
                                    </div>
                                    <div id="errorCarPlate" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successCarNumber" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="CarNumber">车牌号码</label>
                                        车牌号码:<input type="text" id="CarNumber" name="carPlate" class="form-control" maxlength="5" placeholder="请输入您的车牌号"  onchange="checkCarNumber(this)">
                                    </div>
                                    <div id="errorCarNumber" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successTime" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="timePicker">到店时间</label>
                                        预计到店时间:<input type="text" id="timePicker" name="arriveTime" class="form-control datetimepicker"  placeholder="请选择到店时间">
                                    </div>
                                    <div id="errorTime" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div id="successMaintainOrFix" style="color: green;padding-left: 20px;"></div>
                                    <div class="col-md-12">
                                        <label class="sr-only" for="MaintainOrFix">维修&nbsp;|&nbsp;保养</label>
                                        维修&nbsp;|&nbsp;保养:<select id="MaintainOrFix" class="js-example-tags form-control" name="maintainOrFix">
                                            <option value="保养">保养</option>
                                            <option value="维修">维修</option>
                                        </select>
                                    </div>
                                    <div id="errorMaintainOrFix" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="form-group">
                                    <input type="button" onclick="addAppiontment()" value="预约" id="addBtn" class="btn btn-primary">
                                    <input type="reset" value="重置" class="btn btn-primary">
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../common/footer.jsp" %>
    </div>

</div>

<div class="gototop js-top">
    <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
</div>

<!-- jQuery -->
<script src="<%=path%>/js/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="<%=path%>/js/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="<%=path%>/js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="<%=path%>/js/jquery.waypoints.min.js"></script>
<!-- Carousel -->
<script src="<%=path%>/js/owl.carousel.min.js"></script>
<!-- countTo -->
<script src="<%=path%>/js/jquery.countTo.js"></script>
<!-- Magnific Popup -->
<script src="<%=path%>/js/jquery.magnific-popup.min.js"></script>
<script src="<%=path%>/js/magnific-popup-options.js"></script>
<!-- Main -->
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path%>/js/webmain.js"></script>
<!--提示框|下拉框-->
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path%>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<%--appointment js--%>
<script src="<%=path%>/js/index/appointment.js"></script>

</body>
</html>
