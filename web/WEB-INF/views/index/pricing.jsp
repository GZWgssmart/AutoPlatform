<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/17
  Time: 20:01
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
    <title>产品收费</title>
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

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">

    <!-- Animate.css -->
    <link rel="stylesheet" href="<%=path%>/css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="<%=path%>/css/icomoon.css">
    <!-- Themify Icons-->
    <link rel="stylesheet" href="<%=path%>/css/themify-icons.css">
    <!-- Bootstrap  -->
    <link rel="stylesheet" href="<%=path%>/css/bootstrap.css">

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
                            <li class="has-dropdown">
                                <a href="#">使用说明</a>
                                <ul class="dropdown">
                                    <li><a href="#">商家入口</a></li>
                                    <li><a href="#">用户入口</a></li>
                                    <li><a href="#">API</a></li>
                                </ul>
                            </li>
                            <li><a href="<%=path%>/customerClientWeb/pricing">产品收费</a></li>
                            <li class="btn-cta"><a href="<%=path%>/customerClientWeb/contact"><span>入驻 我们</span></a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </nav>

        <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image: url(<%=path%>/images/price.jpg)">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">
                        <div class="row row-mt-15em">

                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">平台收费</span>
                                <h1>为每户入驻的商家</h1>
                                <h1>提供定制的产品</h1>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </header>



        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                        <h2>入驻套餐</h2>
                        <p>根据需求定制属于你自己最实惠的套餐</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="price-box">
                            <h2 class="pricing-plan">试用</h2>
                            <div class="price"><sup class="currency">￥</sup>15<small>/月</small></div>
                            <p>推荐小型门店商家试用了解</p>
                            <hr>
                            <ul class="pricing-info">
                                <li>数据存储 1GB</li>
                                <li>短信提醒 500条</li>
                                <li>动态员工支配</li>
                                <li>使用天数 30天</li>
                            </ul>
                            <a href="<%=path%>/customerClientWeb/contact" class="btn btn-default btn-sm">立即试用</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="price-box">
                            <h2 class="pricing-plan">入驻</h2>
                            <div class="price"><sup class="currency">￥</sup>300<small>/年</small></div>
                            <p>推荐中型商家入驻</p>
                            <hr>
                            <ul class="pricing-info">
                                <li>云数据存储 5GB</li>
                                <li>短信提醒 5000条</li>
                                <li>动态员工支配</li>
                                <li>工作日提供技术支持</li>
                                <li>使用天数 365天</li>
                                <li>免费版本更新</li>
                            </ul>
                            <a href="<%=path%>/customerClientWeb/contact" class="btn btn-default btn-sm">立即入驻</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="price-box popular">
                            <div class="popular-text">Popular</div>
                            <h2 class="pricing-plan">永久VIP</h2>
                            <div class="price"><sup class="currency">￥</sup>998<small>/~</small></div>
                            <p>推荐大型商家入驻</p>
                            <hr>
                            <ul class="pricing-info">
                                <li>云数据存储 5GB</li>
                                <li>用户数据永久备份</li>
                                <li>短信提醒 5000条</li>
                                <li>动态员工支配</li>
                                <li>24小时提供技术支持</li>
                                <li>使用天数 永久</li>
                                <li>免费版本更新</li>
                            </ul>
                            <a href="<%=path%>/customerClientWeb/contact" class="btn btn-primary btn-sm">立即抢购</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="gtco-section">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box">
                        <h2>常见问题</h2>
                        <p></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <ul class="fh5co-faq-list">
                            <li class="animate-box">
                                <h2>客户数据保密吗?</h2>
                                <p>入驻平台后我们会为所有用户信息保密并且签署相关合同是不存在泄露隐私问题的.</p>
                            </li>
                            <li class="animate-box">
                                <h2>入驻后不会使用怎么办?</h2>
                                <p>入驻后的商家我们会发一份详细的使用文档并且会安排相关的客服人员教您使用的.</p>
                            </li>
                            <li class="animate-box">
                                <h2>如果平台系统出现问题怎么办?</h2>
                                <p>平台使用过程中如果出现问题可以反馈给我们客服,客服会第一时间反馈给我们的程序员为您提供解决方案.</p>
                            </li>
                            <li class="animate-box">
                                <h2>手机号可以重复注册吗?</h2>
                                <p>我们平台的手机号是一人一号制度,手机号注册过的不能够再注册您可以通过手机号找回密码.</p>
                            </li>
                            <li class="animate-box">
                                <h2>我能够调取客户信息吗?</h2>
                                <p>我们有权利为顾客保密并且只提供基础信息给商家,做到顾客的资料不泄露.</p>
                            </li>
                            <li class="animate-box">
                                <h2>入驻以后是否有保障?</h2>
                                <p>入驻的商家我们提供一切援助,并且对所有商家顾客负责,如果有相应问题第一时间通知商家让所有用户放心使用.</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <div id="gtco-subscribe">
            <div class="gtco-container">
                <div class="row animate-box">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                        <h2>订阅我们</h2>
                        <p>了解更多汽车资讯您可以订阅我们的公众号和邮件，我们将竭诚为您服务.</p>
                    </div>
                </div>
                <div class="row animate-box">
                    <div class="col-md-8 col-md-offset-2">
                        <form class="form-inline">
                            <div class="col-md-6 col-sm-6">
                                <div class="form-group">
                                    <label for="email" class="sr-only">Email</label>
                                    <input type="email" class="form-control" id="email" placeholder="请输入您的邮箱">
                                </div>
                            </div>
                            <div class="col-md-6 col-sm-6">
                                <button type="submit" class="btn btn-default btn-block">订阅</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <footer id="gtco-footer" role="contentinfo">
            <div class="gtco-container">
                <div class="row row-p	b-md">

                    <div class="col-md-4">
                        <div class="gtco-widget">
                            <h3>关于我们 <span class="footer-logo"><span>.</span></span></h3>
                            <p>汽修店信息化水平普遍偏低，工作效率低，信息的管理混乱，没有实现自动化，没有完善的数据统计，汽修店员工对计算机软件系统缺乏了解。汽车维修保养管理系统需要提供简洁易懂的用户界面，提供简单易用的流程。</p>
                        </div>
                    </div>

                    <div class="col-md-4 col-md-push-1">
                        <div class="gtco-widget">
                            <h3>友情 链接</h3>
                            <ul class="gtco-footer-links">
                                <li><a href="#">汽车之家</a></li>
                                <li><a href="#">汽修平台</a></li>
                                <li><a href="#">宝马官网</a></li>
                                <li><a href="#">奥迪官网</a></li>
                                <li><a href="#">奔驰官网</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="gtco-widget">
                            <h3>联系我们</h3>
                            <ul class="gtco-quick-contact">
                                <li><a href="#"><i class="icon-phone"></i>18370949034(王经理)</a></li>
                                <li><a href="#"><i class="icon-mail2"></i>3234396035@qq.com</a></li>
                                <li><a href="#"><i class="icon-email"></i>12222123@qq.com</a></li>
                                <li><a href="#"><i class="icon-camera"></i>www.hodddd.com</a></li>
                                <li><a href="#"><i class="icon-chat"></i>+0797 8873847</a></li>
                            </ul>
                        </div>
                    </div>

                </div>

                <div class="row copyright">
                    <div class="col-md-12">
                        <p class="pull-left">
                            <small class="block">©2017 Jango 使用平台前必读 意见反馈 京ICP证030173号  <a target="_blank" href="www.baidu.com">大拇指官网</a></small>
                        </p>
                        <p class="pull-right">
                        <ul class="gtco-social-icons pull-right">
                            <li><a href="#"><i class="icon-twitter"></i></a></li>
                            <li><a href="#"><i class="icon-facebook"></i></a></li>
                            <li><a href="#"><i class="icon-linkedin"></i></a></li>
                            <li><a href="#"><i class="icon-dribbble"></i></a></li>
                        </ul>
                        </p>
                    </div>
                </div>

            </div>
        </footer>
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
<script src="<%=path%>/js/webmain.js"></script>

</body>
</html>


