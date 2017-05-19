<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/17
  Time: 11:02
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
    <title>平台特性</title>
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

        <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image: url(<%=path%>/images/tour.jpg)">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">


                        <div class="row row-mt-15em">

                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">安心 放心 省心</span>
                                <h1>让您的爱车维保变得</h1>
                                <h1>简单&nbsp;&nbsp;高效</h1>
                            </div>

                        </div>


                    </div>
                </div>
            </div>
        </header>



        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 text-left gtco-heading">
                        <h2>PC电脑端</h2>
                        <p>完美的自适应高效便捷办公</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>清晰的界面</h3>
                                <p>简洁明了的界面显示给顾客完美的体验和操作,人人都能轻松掌控.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>报表统计查询</h3>
                                <p>满足汽车服务门店的所有盈利需求！服务开单、财务管理化繁为简，削减成本
                                    客户管理 、数据分析、微信客户管理精准营销，提高效率</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>客户管理</h3>
                                <p>管家式会员管理，专治客户流失
                                    线上功能操作，订单和服务记录查询，积分礼品制，打造会员优越感
                                    会员资料库录入，便于大数据统计和精准营销，让客户产生好感和依赖</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>服务开单</h3>
                                <p>智能化服务跟进，高效满足需求
                                    24小时在线预约功能，随时跟进用户的到店情况
                                    提供线上购买套餐卡、账户充值和线下开单2种开单选择
                                    支持多项收款和挂账、结账功能，支持导出，财务智能化</p>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-7 macbook-wrap animate-box" data-animate-effect="fadeInRight">
                        <img src="<%=path%>/images/macbook.png" alt="Free HTML5 Bootstrap Template">
                    </div>
                </div>
            </div>
        </div>

        <div class="gtco-section border-bottom">
            <div class="gtco-container">

                <div class="row">
                    <div class="col-md-8 text-left gtco-heading">
                        <h2>手机端</h2>
                        <p>手机端提供客户查询爱车保养情况,对所有流程一清二楚</p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <img src="<%=path%>/images/iphone.png" class="img-responsive" alt="Free HTML5 Bootstrap Template">
                    </div>
                    <div class="col-md-6 mt-sm">
                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-layers-alt"></i>
						</span>
                            <div class="feature-copy">
                                <h3>跟踪流程</h3>
                                <p>从预约到提车所有流程都能够在手机端查询数据便捷商务</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-key"></i>
						</span>
                            <div class="feature-copy">
                                <h3>私人用户</h3>
                                <p>用户数据平台给予保密,让你不再担心信息泄露不再被骚扰.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-image"></i>
						</span>
                            <div class="feature-copy">
                                <h3>个性化</h3>
                                <p>用户个性化能够上传自己的头像对自己个人信息全面编辑.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-heart"></i>
						</span>
                            <div class="feature-copy">
                                <h3>投诉信息</h3>
                                <p>用户投诉反馈给商家提高维保质量,让消费者权益最大化不被侵害.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-infinite"></i>
						</span>
                            <div class="feature-copy">
                                <h3>报表查询</h3>
                                <p>用户可打印自己的账单查询自己的消费情况不再给坑.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-credit-card"></i>
						</span>
                            <div class="feature-copy">
                                <h3>一键支付</h3>
                                <p>用户能够通过微信支付宝一键支付,让提车不再繁琐.</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="gtco-section">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                        <h2>入驻商家&nbsp;案例</h2>
                        <p>入驻平台使得车主维保更加方便快捷</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="<%=path%>/images/img_2.jpg" class="fh5co-project-item image-popup">
                            <figure>
                                <div class="overlay"><i class="ti-plus"></i></div>
                                <img src="<%=path%>/images/img_2.jpg" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>华晨汽修</h2>
                                <p>江西省赣州市汽车精品一条街1G栋120号</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="<%=path%>/images/img_3.jpg" class="fh5co-project-item image-popup">
                            <figure>
                                <div class="overlay"><i class="ti-plus"></i></div>
                                <img src="<%=path%>/images/img_3.jpg" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>纽巴伦汽车美容</h2>
                                <p>江西赣州市章贡区文清路123号1-5</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="<%=path%>/images/img_7.jpg" class="fh5co-project-item image-popup">
                            <figure>
                                <div class="overlay"><i class="ti-plus"></i></div>
                                <img src="<%=path%>/images/img_7.jpg" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>车管家汽修</h2>
                                <p>江西省赣州市沙河区43号1—2</p>
                            </div>
                        </a>
                    </div>

                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="<%=path%>/images/img_1.jpg" class="fh5co-project-item image-popup">
                            <figure>
                                <div class="overlay"><i class="ti-plus"></i></div>
                                <img src="<%=path%>/images/img_1.jpg" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>金纽扣汽车美容</h2>
                                <p>北京玄武区文明大道15号1-3</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="<%=path%>/images/img_5.jpg" class="fh5co-project-item image-popup">
                            <figure>
                                <div class="overlay"><i class="ti-plus"></i></div>
                                <img src="<%=path%>/images/img_5.jpg" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>千斤顶汽车美容</h2>
                                <p>深圳福田区富田路15号</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="<%=path%>/images/img_6.jpg" class="fh5co-project-item image-popup">
                            <figure>
                                <div class="overlay"><i class="ti-plus"></i></div>
                                <img src="<%=path%>/images/img_6.jpg" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>樱花汽修美容</h2>
                                <p>河北陕西文东县银河大道99号</p>
                            </div>
                        </a>
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


