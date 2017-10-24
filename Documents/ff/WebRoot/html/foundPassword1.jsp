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
						<li class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
							<span class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">2.重置密码</span>
						</li>
						<li class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
							<span class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12  ">
								<span class="text_overflow">3.最终结果</span>
							</span>
						</li>
					</ul>
				</div>
				<!-- End found_password_nav -->

				<!-- Start found_password_form -->
				<div class="container-fluid col-xs-12 col-sm-12 col-md-12 col-lg-12 found_password_form">
					<!-- 以下三张表单分别代表"找回密码"的三种状态，进行后台请求请注意先后的逻辑顺序 -->
					
					<!-- 表单一:短信验证页面 -->
					<form class="thumbnail col-xs-12 col-sm-12 col-md-5 col-lg-4" action="../user/foundAction1.action" method="post">
						<div class="form-group form-group-lg has-feedback col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<label for="phone" class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></label>
								<input type="text" class="form-control" id="phone" name="phone" autocomplete="off" placeholder="请输入手机号" />
							</div>
							<span class="glyphicon form-control-feedback"></span>
						</div>

						<div class="form-group form-group-lg has-feedback clearfix col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="input-group col-xs-6 col-sm-6 col-md-6 col-lg-6 pull-left">
								<label for="sms_cycle" class="input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></label>
								<input type="text" class="form-control" id="sms_cycle" name="sms_cycle" autocomplete="off" placeholder="请输入短信验证码" />
							</div>
							<button title="发送验证码" type="button" id="sms_btn" class="btn btn-primary btn-lg col-xs-5 col-sm-5 col-md-5 col-lg-5 pull-right text_overflow">发送验证码</button>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<input type="submit" class="btn btn-info btn-lg col-xs-12 col-sm-12 col-md-12 col-lg-12" id="register_form_submit" value="下一步" />
						</div>
					</form>
					<!-- 表单一:短信验证页面 -->
					
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
</html>	