<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>找回密码 -- 缘来在此交友网</title>
		<link type="text/css" rel="stylesheet" href="../css/stylereset.css" />
		<link rel="stylesheet" href="../css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<link rel="stylesheet" href="../css/media.css" />
		<link rel="stylesheet" href="../css/alert.css" />
		<style type="text/css">
			/*覆盖bootstrap原始样式*/
			.form-control-feedback {
				top: 6px;
				right: 15px;
			}
			.data_inner_container {
				min-height: calc(100vh - 200px);
				min-height: -moz-calc(100vh - 200px);
				min-height: -webkit-calc(100vh - 200px);
			}
		</style>
	</head>

	<body style="overflow-x: hidden; overflow-y: auto;">
		<!--Start top-->
		<header class="top clearfix">
			<!--Start top_logo-->
			<div class="top_logo">
				<a href="../index.html" title="缘来在此交友网">缘来在此交友网</a>
			</div>
			<!--End top_logo-->

			<!--Start top_main-->
			<div class="top_main clearfix">
				<!--Start top_main_nav-->
				<nav class="top_main_nav clearfix">
					<label class="top_main_nav_icon hidden-lg pull-right">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
					</label>
				</nav>
				<!--End top_main_nav-->
			</div>
			<!--End top_main-->
		</header>
		<!--End top-->

		<!-- Start data_outer_container -->
		<article class="data_outer_container container-fluid clearfix">
			<!-- Start data_inner_container -->
			<article class="data_inner_container container-fluid clearfix">
				<!-- Start content_main_header -->
				<div class="content_main_header col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<h3>密码找回<span class="label label-primary hidden-lg" style="margin-left: 30px;">1.短信验证</span></h3>
				</div>
				<!-- End content_main_header -->

				<!-- Start found_password_nav -->
				<div class="found_password_nav text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<ul class="clearfix col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<li class="col-xs-4 col-sm-4 col-md-4 col-lg-4 active">
							<span class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">1.短信验证</span>
						</li>
						<li class="col-xs-4 col-sm-4 col-md-4 col-lg-4 active">
							<span class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">2.重置密码</span>
						</li>
						<li class="col-xs-4 col-sm-4 col-md-4 col-lg-4 active">
							<span class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12  ">
								<span class="text_overflow">3.最终结果</span>
							</span>
						</li>
					</ul>
				</div>
				<!-- End found_password_nav -->

				<!-- Start found_password_form -->
				<div class="container-fluid col-xs-12 col-sm-12 col-md-12 col-lg-12 found_password_form">
					<!-- 表单三:显示重置结果 -->
					<form class="thumbnail col-xs-12 col-sm-12 col-md-5 col-lg-4">
						<h3 class="text-center text-muted">抱歉，重置密码失败!</h3>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<a href="../html/foundPassword1.jsp" type="button" class="btn btn-info btn-lg col-xs-12 col-sm-12 col-md-12 col-lg-12" id="register_form_submit">返回首页</a>
						</div>
					</form>
					<!-- 表单三:显示重置结果 -->	
				</div>
				<!-- End found_password_form -->
			</article>
			<!-- End data_inner_container -->
		</article>
		<!-- End data_outer_container -->

		<!--Start goToBtn-->
		<div id="goTopBtn" title="回到顶部" style="display: none; left: 1313px;">&and;</div>
		<!--End goToBtn-->

	</body>
	<script type="text/javascript" src="../js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.js"></script>
	<script type="text/javascript" src="../js/tree.js"></script>
	<script type="text/javascript" src="../js/public.js"></script>
	<script type="text/javascript" src="../js/login.js"></script>
	<script type="text/javascript">
		<!--
		$(document).ready(function() {
			/*目前仅作为测试阶段，具体逻辑内容，以后再做封装*/
			$(".btn_close").click(closeWindow);
			$(".btn_yes").click(closeWindow);
			$(".btn_no").click(closeWindow);

			//弹窗关闭函数
			function closeWindow() {
				$(".user_defined_alert_window").hide();
				$(".full_window").hide();
			}

			//弹窗显示函数
			$(".btn_show").click(function() {
				$(".full_window").show();
				$(".user_defined_alert_window").show();
			});
		});
		//-->
	</script>

</html>